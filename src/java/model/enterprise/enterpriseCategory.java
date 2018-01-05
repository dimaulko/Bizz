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
public class enterpriseCategory {
    private String id;
    private String name;
    private String shortName;

    public enterpriseCategory(String id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
    }

    public enterpriseCategory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "enterpriseCategory{" + "id=" + id + ", name=" + name + ", shortName=" + shortName + '}';
    }
    
    
}
