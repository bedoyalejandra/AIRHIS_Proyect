package com.example.airhis_proyect;

public class PointCartesian {
    String id;
    int x;
    int y;
    double dat;

    public PointCartesian(int x, int y, double dat) {
        this.x = x;
        this.y = y;
        this.dat = dat;
    }

    public PointCartesian(String id, int x, int y, double dat) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.dat = dat;
    }

    public static int straightEquationX(int x){
        double minX = 428710;
        double maxX = 463464;
        double mx = (99)/(maxX - minX);

        return (int)(mx * (x - minX));
    }

    public static int straightEquationY(int y){
        double minY = 673535;
        double maxY = 711519;
        double mx = (99)/(maxY - minY);

        return (int)(mx * (y - minY));
    }

    public int getXRedim(){
        return straightEquationX(x);
    }

    public int getYRedim(){
        return straightEquationY(y);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDat() {
        return dat;
    }

    public void setDat(double dat) {
        this.dat = dat;
    }
}
