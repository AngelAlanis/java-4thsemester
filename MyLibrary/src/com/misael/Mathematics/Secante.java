package com.misael.Mathematics;

import java.text.DecimalFormat;

public class Secante {

    private int i;
    private double xi, error, fxi;

    DecimalFormat df = new DecimalFormat("#.####");

    public Secante(){

    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public double getXi() {
        return xi;
    }

    public void setXi(double xi) {
        this.xi = Double.parseDouble(df.format(xi));
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = Double.parseDouble(df.format(error));
    }

    public double getFxi() {
        return fxi;
    }

    public void setFxi(double fxi) {
        this.fxi = Double.parseDouble(df.format(fxi));
    }
}
