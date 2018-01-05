/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Dima
 */
public class Improvement {

    ArrayList<MODELImprovementBalance> balance = new ArrayList<>();
    ArrayList<MODELImprovementEnterprise> enterpriseCount = new ArrayList<>();
    ArrayList<MODELImprovementIncasation> incasationTime = new ArrayList<>();

    public Improvement() {
    }

    public ArrayList<MODELImprovementBalance> getBalance() {
        return balance;
    }

    public void setBalance(ArrayList<MODELImprovementBalance> balance) {
        this.balance = balance;
    }

    public ArrayList<MODELImprovementEnterprise> getEnterpriseCount() {
        return enterpriseCount;
    }

    public void setEnterpriseCount(ArrayList<MODELImprovementEnterprise> enterpriseCount) {
        this.enterpriseCount = enterpriseCount;
    }

    public ArrayList<MODELImprovementIncasation> getIncasationTime() {
        return incasationTime;
    }

    public void setIncasationTime(ArrayList<MODELImprovementIncasation> incasationTime) {
        this.incasationTime = incasationTime;
    }

}
