/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.enterprise;

import java.util.ArrayList;
import java.util.List;
import model.LatLong;

/**
 *
 * @author Dima
 */
public class EnterpriseIncome {
    
    
    private String id;
    private String forsqareId;
    private LatLong location;
    private String name;
    private enterpriseCategory categories;
    private Likes likes = new Likes();
    private double rating;
    private long createdAt;
    private int accountId; // holder;
    private long incasationAt;
    private int bought;

    public EnterpriseIncome() {
    }

    public EnterpriseIncome(String id, String forsqareId, LatLong location, String name, enterpriseCategory categories, double rating, long createdAt, int accountId, long incasationAt, int bought) {
        this.id = id;
        this.forsqareId = forsqareId;
        this.location = location;
        this.name = name;
        this.categories = categories;
        this.rating = rating;
        this.createdAt = createdAt;
        this.accountId = accountId;
        this.incasationAt = incasationAt;
        this.bought = bought;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForsqareId() {
        return forsqareId;
    }

    public void setForsqareId(String forsqareId) {
        this.forsqareId = forsqareId;
    }

    public LatLong getLocation() {
        return location;
    }

    public void setLocation(LatLong location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enterpriseCategory getCategories() {
        return categories;
    }

    public void setCategories(enterpriseCategory categories) {
        this.categories = categories;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public long getIncasationAt() {
        return incasationAt;
    }

    public void setIncasationAt(long incasationAt) {
        this.incasationAt = incasationAt;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    @Override
    public String toString() {
        return "EnterpriseIncome{" + "id=" + id + ", forsqareId=" + forsqareId + ", location=" + location + ", name=" + name + ", categories=" + categories + ", likes=" + likes + ", rating=" + rating + ", createdAt=" + createdAt + ", accountId=" + accountId + ", incasationAt=" + incasationAt + ", bought=" + bought + '}';
    }

}
