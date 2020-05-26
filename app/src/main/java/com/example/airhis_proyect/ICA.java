package com.example.airhis_proyect;

import android.content.Intent;

import java.util.ArrayList;

public class ICA {

    ArrayList<Intervals> intervals = new ArrayList<>();

    public ICA() {
        intervals.add(new Intervals(0,50, 0, 12));
        intervals.add(new Intervals(51, 100, 13, 37));
        intervals.add(new Intervals(101,150,38,55));
        intervals.add(new Intervals(151, 200,56,150));
        intervals.add(new Intervals(201,300,151,250));
        intervals.add(new Intervals(301,500,251,500));
    }

    public double calculateIca(double n){
        if((n > 12 & n < 13) || (n > 37 & n < 38) || (n > 55 & n < 56) || (n > 150 & n < 151) || (n > 250 & n < 251)){
            n = Math.round(n);
            System.out.println(n);
        }

        for (int i = 0; i < intervals.size(); i++){
            Intervals interval = intervals.get(i);
            if(n >= interval.PCmin & n <= interval.PCmax){
                return  (((interval.Imax - interval.Imin)/(interval.PCmax - interval.PCmin)) * (n - interval.PCmin) + interval.Imin);
            }
        }
        return 0.0;
    }

    private class Intervals {

        private int Imin = 0;
        private int Imax = 0;
        private int PCmin = 0;
        private int PCmax = 0;

        public Intervals(int imin, int imax, int PCmin, int PCmax) {
            Imin = imin;
            Imax = imax;
            this.PCmin = PCmin;
            this.PCmax = PCmax;
        }

        public int getImin() {
            return Imin;
        }

        public void setImin(int imin) {
            Imin = imin;
        }

        public int getImax() {
            return Imax;
        }

        public void setImax(int imax) {
            Imax = imax;
        }

        public int getPCmin() {
            return PCmin;
        }

        public void setPCmin(int PCmin) {
            this.PCmin = PCmin;
        }

        public int getPCmax() {
            return PCmax;
        }

        public void setPCmax(int PCmax) {
            this.PCmax = PCmax;
        }
    }

}
