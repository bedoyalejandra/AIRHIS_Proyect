package com.example.airhis_proyect;

public class Lon {
    private double lonMin;
    private double lonMax;

    public Lon(double lonMin, double lonMax) {
        this.lonMin = lonMin;
        this.lonMax = lonMax;
    }

    public boolean inside(double longitude){
        if(longitude >= lonMin && longitude <= lonMax) return true;
        else return false;
    }

    public double getLonMin() {
        return lonMin;
    }

    public void setLonMin(double lonMin) {
        this.lonMin = lonMin;
    }

    public double getLonMax() {
        return lonMax;
    }

    public void setLonMax(double lonMax) {
        this.lonMax = lonMax;
    }
}
