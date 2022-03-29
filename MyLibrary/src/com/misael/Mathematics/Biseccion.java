package com.misael.Mathematics;

import java.text.DecimalFormat;

public class Biseccion {

    private int    i;
    private double a, b, xi, error, fa, fxi;

    DecimalFormat df = new DecimalFormat("#.####");

    public Biseccion() {

    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = Double.parseDouble(df.format(a));
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = Double.parseDouble(df.format(b));
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

    public double getFa() {
        return fa;
    }

    public void setFa(double fa) {
        this.fa = Double.parseDouble(df.format(fa));
    }

    public double getFxi() {
        return fxi;
    }

    public void setFxi(double fxi) {
        this.fxi = Double.parseDouble(df.format(fxi));
    }
}
