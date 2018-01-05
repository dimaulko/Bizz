/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dbmodels.Enterprise;
import java.util.ArrayList;

/**
 *
 * @author Dima
 */
public class AccountGeneral {

    AccountDB account = new AccountDB();
    ArrayList<Enterprise> enterprises = new ArrayList<>();

    public AccountGeneral() {
    }

    public AccountDB getAccount() {
        return account;
    }

    public void setAccount(AccountDB account) {
        this.account = account;
    }

    public ArrayList<Enterprise> getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(ArrayList<Enterprise> enterprises) {
        this.enterprises = enterprises;
    }

    @Override
    public String toString() {
        return "AccountGeneral{" + "account=" + account.toString() + ", enterprises=" + enterprises + '}';
    }

}
