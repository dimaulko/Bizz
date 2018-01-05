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
public class LatLong {

    private Double lat;
    private Double lng;

    public LatLong() {
    }

    public LatLong(Double latituge, Double langitude) {
        this.lat = latituge;
        this.lng = langitude;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "LatLong{" + "latituge=" + lat + ", langitude=" + lng + '}';
    }

}
