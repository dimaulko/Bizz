/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.enterprise;

/**
 *
 * @author Dima
 */
public class Likes {
    private int count;
    private String summary;

    public Likes() {
    }

    public Likes(int count, String summary) {
        this.count = count;
        this.summary = summary;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Likes{" + "count=" + count + ", summary=" + summary + '}';
    }
    
}
