package com.example.airhis_proyect;

public class Coordinates {

    private double lat;
    private double lon;
    private int x;
    private int y;

    public Coordinates(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        calculate();
    }

    public int getXScaling() {
        return x;
    }

    public int getYScaling() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void calculate(){
        //Semieje mayor
        double a = 6378388.0;

        //Semieje menor
        double b = 6356911.946130;

        //Radio polar de la curvatura
        double c = 6399936.608;

        //Excentricidad
        double e = 0.08199189;

        //Segunda excentricidad
        double e2 = 0.08226889;

        double e22 = Math.pow(e2, 2);

        int huso = huso();
        double radLat = rad(lat);
        double delthaAlpha = alpha();
        double A = Math.cos(radLat)* Math.sin(delthaAlpha);
        double xi = 0.5 * Math.log((1 + A)/(1 - A));
        double n = Math.atan(Math.tan(radLat) / Math.cos(delthaAlpha)) - radLat;
        double v = (c / Math.pow((1 + (Math.pow(e2, 2) * Math.pow(Math.cos(radLat), 2))), 0.5)) * 0.9996;
        double zeta = (Math.pow(e2, 2) / 2) * Math.pow(xi, 2) * Math.pow(Math.cos(radLat), 2);
        double A1 = Math.sin(2 * radLat);
        double A2 = A1 * Math.pow(Math.cos(radLat) ,2);
        double J2 = radLat + (A1 / 2);
        double J4 = (3 * J2 + A2) / 4;
        double J0 = ((5 * J4) + (A2 * Math.pow(Math.cos(radLat) ,2))) / 3;
        double alpha = e22 * 0.75;
        double alpha2 = Math.pow(alpha, 2);
        double beta = alpha2 * 1.6667;
        double alpha3 = Math.pow(alpha, 3);
        double gamma = alpha3 * 1.296296296;
        double b0 = 0.9996 * c * (radLat - (alpha * J2) + (beta * J4) - (gamma * J0));
/*
        System.out.println("Huso: " + huso);
            System.out.println("radLat: " + radLat);
            System.out.println("delthaAlpha: " + delthaAlpha);
            System.out.println("A: " + A);
            System.out.println("xi: " + xi);
            System.out.println("n: " + n);
            System.out.println("v: " + v);
            System.out.println("zeta: " + zeta);
            System.out.println("A1: " + A1);
            System.out.println("A2: " + A2);
            System.out.println("J2: " + J2);
            System.out.println("J4: " + J4);
            System.out.println("J0: " + J0);
            System.out.println("alpha: " + alpha);
            System.out.println("beta: " + beta);
            System.out.println("gamma: " + gamma);
            System.out.println("b0: " + b0);


*/

        x = (int)((xi * v * (1 + (zeta/3))) + 500000);
        y = (int) (n * v * (1 + zeta) + b0);

    }

    private double rad(double n){
        return n * Math.PI / 180;
    }

    private int huso(){
        double x = (lon / 6) + 31;
        return (int)x;
    }

    private double alpha(){
        int huso = huso();
        int alpha0 =(huso * 6) - 183;
        return rad(lon) - rad(alpha0);
    }
}

