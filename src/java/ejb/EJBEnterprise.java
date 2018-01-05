/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.DAOAccount;
import DAO.DAOEnterprise;
import DAO.DAOImprovementBalance;
import DAO.DAOImprovementEnterprise;
import DAO.DAOImprovementIncasation;
import dbmodels.Enterprise;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.AccountDB;
import model.AccountGeneral;
import model.MODELImprovementBalance;
import model.MODELImprovementEnterprise;
import model.MODELImprovementIncasation;
import model.response.ErrorResponse;
import util.UtilTime;

/**
 *
 * @author Dima
 */
@Stateless
public class EJBEnterprise {

    @EJB
    private DAOImprovementBalance dAOImprovementBalance;

    public static final double ENTERPRISE_PROFIT_COEF = 0.2;
    @EJB
    private DAOImprovementIncasation dAOImprovementIncasation;

    @EJB
    private DAOImprovementEnterprise dAOImprovementEnterprise;

    @EJB
    private EJBAccount eJBAccount;

    @EJB
    private DAOAccount dAOAccount;

    @EJB
    private DAOEnterprise dAOEnterprise;

    public Pair<Boolean, ArrayList<Enterprise>> getInfo(ArrayList<Enterprise> list) {

        Pair<Boolean, ArrayList<Enterprise>> response;

        if (list == null || list.isEmpty()) {
            response = new Pair<>(false, list);
            System.out.println("List is null : 33");
        } else {
            Enterprise temp = null;
            for (Enterprise enterpriseIncome : list) {
                temp = dAOEnterprise.getByFoursqaureId(enterpriseIncome.getId());
                if (temp == null) {
                    System.out.println("temp is null");
                    enterpriseIncome.setBought(0); // todo: NPE
                } else {
                    enterpriseIncome.setBought(temp.getBought());
                    enterpriseIncome.setHolder(temp.getHolder());
                }
                System.out.println("Enterprise: " + enterpriseIncome.toString());
            }
            response = new Pair<>(true, list);
        }
        return response;
    }

    public Pair<AccountGeneral, ErrorResponse> bayEnterprise(Enterprise enterprise, String IMEI) {

        Enterprise temp = dAOEnterprise.getByFoursqaureId(enterprise.getId());
        if (temp != null) {
            return new Pair<>(null, new ErrorResponse("Enterprise is used"));
        }
        AccountDB accountDB = dAOAccount.getAccountByIMEI(IMEI);
        if (accountDB == null) {
            return new Pair<>(null, new ErrorResponse("User not found"));
        }

        MODELImprovementEnterprise improve = dAOImprovementEnterprise.getImprovmentEnterprise(accountDB.getImprovementEnterpriseCount(), accountDB.getLevel() + 1);
        if (improve == null) {
            System.out.println("improve null");
        }
        if (accountDB == null) {
            System.out.println("accountDB null");
        }
        if (improve.getLimit() <= dAOEnterprise.getEnterpriseList(accountDB.getIMEI()).size()) {
            System.out.println(String.valueOf(improve.getLimit()) + " : " + String.valueOf(dAOEnterprise.getEnterpriseList(accountDB.getIMEI()).size()));
            return new Pair<>(null, new ErrorResponse("You can't bay more enterprise"));
        }
        if (accountDB.getBalance() < enterprise.getBought()) {
            return new Pair<>(null, new ErrorResponse("Youre balance is less then Bought of enterprise"));
        }
        long time = UtilTime.getTimeStamp();
        System.out.println("TIME: " + time);
        enterprise.setHolder(accountDB.getIMEI());
        enterprise.setCreatedAt(time);
        enterprise.setIncasation(time);

        dAOEnterprise.create(enterprise);
        dAOAccount.editEnterpriseCount(IMEI, accountDB.getEnterpriseCount() + 1);
        dAOAccount.editBalance(IMEI, accountDB.getBalance() - enterprise.getBought());
        AccountGeneral acc = eJBAccount.getGeneralAccount(IMEI);
        return new Pair<>(acc, null);
    }

    public Pair<String, ErrorResponse> incasationChek(String IMEI, String enterpriseId) {
        ArrayList<Enterprise> listEnterprises = new ArrayList<>();
        if (!enterpriseId.isEmpty()) { // single version
            Enterprise temp = dAOEnterprise.getByFoursqaureId(enterpriseId);
            if (temp != null) {
                listEnterprises.add(temp);
            }
        } else {
            listEnterprises.addAll(dAOEnterprise.getEnterpriseList(IMEI));
        }
        long now = UtilTime.getTimeStamp();
        System.out.println("NOW: " + String.valueOf(now));
        AccountDB accountDB = dAOAccount.getAccountByIMEI(IMEI);
        System.out.println("NOW: " + accountDB.toString());
        MODELImprovementIncasation incasation = dAOImprovementIncasation.getImprovmentIncasation(accountDB.getImprovementTimeIncasation(), accountDB.getLevel() + 1);
        System.out.println("NOW: " + incasation.toString());
        long maxIncasationTime = UtilTime.getSecondsFromHours(incasation.getLimit());
        long userCanIncasate = 0;
        long timeNow = UtilTime.getTimeStamp();
        for (Enterprise enterprise : listEnterprises) {
            if (timeNow - enterprise.getIncasation() > maxIncasationTime) {
                userCanIncasate += (long) (((enterprise.getBought() * ENTERPRISE_PROFIT_COEF) / UtilTime.SECONDS_IN_DAY) * maxIncasationTime);
            } else {
                userCanIncasate += (long) ((((enterprise.getBought() * ENTERPRISE_PROFIT_COEF) / UtilTime.SECONDS_IN_DAY) * (timeNow - enterprise.getIncasation())));
            }
        }
        // chek if enter in balance range
        MODELImprovementBalance balance = dAOImprovementBalance.getImprovmentBalance(accountDB.getImprovementBalance(), accountDB.getImprovementBalance());
        long subtract = balance.getLimit() - accountDB.getBalance() - userCanIncasate;
        if (subtract < 0) {
            return new Pair<>(null, new ErrorResponse("You well lost" + subtract));
        }

        return new Pair<>("You can incasate: " + userCanIncasate, null);
    }
}
