/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dima
 */
public class ProfileDB {
    private String IMEI;
    private String facebookLink;
    private String userFirstName;
    private String userLastName;

    public ProfileDB(String IMEI, String facebookLink, String userFirstName, String userLastName) {
        this.IMEI = IMEI;
        this.facebookLink = facebookLink;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public ProfileDB() {
        this.IMEI="";
        this.facebookLink="";
        this.userFirstName="";
        this.userLastName="";
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    @Override
    public String toString() {
        return "Profile{" + "IMEI=" + IMEI + ", facebookLink=" + facebookLink + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + '}';
    }
    
}
