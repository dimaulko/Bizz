/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Dima
 */
public class MODELImprovementBalance {

    @Expose(deserialize = false)
    private int id;
    private int level;
    private int subLevel;
    private int limit;
    private int price;

    public MODELImprovementBalance() {
    }

    public MODELImprovementBalance(int id, int level, int subLevel, int limit, int price) {
        this.id = id;
        this.level = level;
        this.subLevel = subLevel;
        this.limit = limit;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSubLevel() {
        return subLevel;
    }

    public void setSubLevel(int subLevel) {
        this.subLevel = subLevel;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MODELImprovementBalance{" + "id=" + id + ", level=" + level + ", subLevel=" + subLevel + ", limit=" + limit + ", price=" + price + '}';
    }

}
