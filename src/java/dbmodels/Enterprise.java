/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmodels;

/**
 *
 * @author Dima
 */
public class Enterprise {

    private String id;
    private Location location = new Location();
    private String name;
    private int bought;
    private String holder;
    private long createdAt;
    private long incasation;

    public Enterprise() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    @Override
    public String toString() {
        return "Enterprise{" + "id=" + id + ", location=" + location + ", name=" + name + ", bought=" + bought + ", holder=" + holder + ", createdAt=" + createdAt + ", incasation=" + incasation + '}';
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getIncasation() {
        return incasation;
    }

    public void setIncasation(long incasation) {
        this.incasation = incasation;
    }
}
