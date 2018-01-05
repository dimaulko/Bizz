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
public class AccountDB {

    private String IMEI;
    private ProfileDB profile;
    private int level;
    private long balance;
    private int enterpriseCount;
    private long createdAt;
    private long lastEnter;
    private String userName;
    private boolean inFlight;
    private LatLong latLong;
    private int investmentInBusiness;
    // ---- improvements
    private int improvementBalance;
    private int improvementEnterpriseCount;
    private int improvementTimeIncasation;
    private int profitOnLevel;

    public AccountDB() {
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public ProfileDB getProfile() {
        return profile;
    }

    public void setProfile(ProfileDB profile) {
        this.profile = profile;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getEnterpriseCount() {
        return enterpriseCount;
    }

    public void setEnterpriseCount(int enterpriseCount) {
        this.enterpriseCount = enterpriseCount;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getLastEnter() {
        return lastEnter;
    }

    public void setLastEnter(long lastEnter) {
        this.lastEnter = lastEnter;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isInFlight() {
        return inFlight;
    }

    public void setInFlight(boolean inFlight) {
        this.inFlight = inFlight;
    }

    public LatLong getLatLong() {
        return latLong;
    }

    public void setLatLong(LatLong latLong) {
        this.latLong = latLong;
    }

    public int getInvestmentInBusiness() {
        return investmentInBusiness;
    }

    public void setInvestmentInBusiness(int investmentInBusiness) {
        this.investmentInBusiness = investmentInBusiness;
    }

    public int getImprovementBalance() {
        return improvementBalance;
    }

    public void setImprovementBalance(int improvementBalance) {
        this.improvementBalance = improvementBalance;
    }

    public int getImprovementEnterpriseCount() {
        return improvementEnterpriseCount;
    }

    public void setImprovementEnterpriseCount(int improvementEnterpriseCount) {
        this.improvementEnterpriseCount = improvementEnterpriseCount;
    }

    public int getProfitOnLevel() {
        return profitOnLevel;
    }

    public void setProfitOnLevel(int profitOnLevel) {
        this.profitOnLevel = profitOnLevel;
    }

    @Override
    public String toString() {
        return "AccountDB{" + "IMEI=" + IMEI + ", profile=" + profile + ", level=" + level + ", balance=" + balance + ", enterpriseCount=" + enterpriseCount + ", createdAt=" + createdAt + ", lastEnter=" + lastEnter + ", userName=" + userName + ", inFlight=" + inFlight + ", latLong=" + latLong + ", investmentInBusiness=" + investmentInBusiness + ", improvementBalance=" + improvementBalance + ", improvementEnterpriseCount=" + improvementEnterpriseCount + ", improvementTimeIncasation=" + improvementTimeIncasation + ", profitOnLevel=" + profitOnLevel + '}';
    }

    public int getImprovementTimeIncasation() {
        return improvementTimeIncasation;
    }

    public void setImprovementTimeIncasation(int improvementTimeIncasation) {
        this.improvementTimeIncasation = improvementTimeIncasation;
    }

}
