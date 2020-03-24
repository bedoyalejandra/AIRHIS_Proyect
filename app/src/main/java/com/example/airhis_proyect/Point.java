package com.example.airhis_proyect;

public class Point {

    double lat;
    double lon;
    int x;
    int y;
    double dat;

    public Point(double lat, double lon, double dat) {
        this.lat = lat;
        this.lon = lon;
        this.dat = dat;
        Coordinates coordinates = new Coordinates(lat, lon);
        this.x = coordinates.getX();
        this.y = coordinates.getY();
    }


    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDat() {
        return dat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDat(double dat) {
        this.dat = dat;
    }

    @Override
    public String toString() {
        return "\n Latitud: " + lat + "\n Longitud: " + lon + "\n Dato: " + dat + "\n X: " + x + "\n Y: " + y;
    }
}
