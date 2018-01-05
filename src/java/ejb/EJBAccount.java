/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.DAOAccount;
import DAO.DAOEnterprise;
import DAO.DAOProfile;
import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AccountDB;
import model.AccountGeneral;
import model.Constants;
import model.Constants.ServletConstant;
import model.ProfileDB;

/**
 *
 * @author Dima
 */
@Stateless
public class EJBAccount {

    private static final int START_BALANCE = 50_000;

    @EJB
    private DAOProfile daoProfile;
    @EJB
    private DAOAccount daoAccount;
    @EJB
    private ValidateRequest validator;
    @EJB
    private DAOEnterprise daoEnterprise;

    public String enter(HttpServletRequest request, HttpServletResponse response) {

        AccountDB data = daoAccount.getAccountByIMEI(request.getHeader(ServletConstant.IMEI));
        if (data == null) {
            String body = validator.readBody(request);
            AccountDB accountDB = new AccountDB();
            accountDB.setIMEI(request.getHeader(ServletConstant.IMEI));
            accountDB.setBalance(START_BALANCE);
            daoAccount.create(accountDB);
            ProfileDB profileDB = new ProfileDB();
            profileDB.setIMEI(accountDB.getIMEI());

            if (!body.isEmpty()) {
                ProfileDB incomeProfile = new Gson().fromJson(body, ProfileDB.class);
                profileDB.setFacebookLink(incomeProfile.getFacebookLink());
                profileDB.setUserFirstName(incomeProfile.getUserFirstName());
                profileDB.setUserLastName(incomeProfile.getUserLastName());
            } else {

            }
            daoProfile.create(profileDB);
        } else {
            System.out.println(new Gson().toJson(data));
        }
        return new Gson().toJson(getAccountWithProfile(request.getHeader(ServletConstant.IMEI)));

    }

    public String editUserName(String IMEI, String userName) {
        AccountDB data = daoAccount.getAccountByIMEI(IMEI);
        if (data != null) {
            daoAccount.editUserName(IMEI, userName);
            return new Gson().toJson(getAccountWithProfile(IMEI));
        } else {
            return Constants.ParameterRequestConstant.GLOBAL;
        }
    }

    public AccountDB getAccountWithProfile(String IMEI) {
        AccountDB accountDB = daoAccount.getAccountByIMEI(IMEI);
        ProfileDB DB = daoProfile.getProfileByIMEI(IMEI);
        if (DB != null) {
            accountDB.setProfile(DB);
        } else {
            System.out.println("PROFILE IS NULL");
        }
        return accountDB;
    }

    public AccountGeneral getGeneralAccount(String IMEI) {
        AccountGeneral accountGeneral = new AccountGeneral();
        accountGeneral.setAccount(getAccountWithProfile(IMEI));
        accountGeneral.setEnterprises(daoEnterprise.getEnterpriseList(accountGeneral.getAccount().getIMEI()));
        System.out.println("accountGeneral: " + accountGeneral.toString());
        return accountGeneral;
    }

}
