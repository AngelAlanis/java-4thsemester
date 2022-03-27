package com.misael.Mathematics;

public class ReglaFalsa {

    private double a, b, fa, fb, xi, error, fxi;

    public ReglaFalsa(double a, double b, double fa, double fb, double xi, double error, double fxi) {
        this.a     = a;
        this.b     = b;
        this.fa    = fa;
        this.fb    = fb;
        this.xi    = xi;
        this.error = error;
        this.fxi   = fxi;
    }

    public ReglaFalsa() {

    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getFa() {
        return fa;
    }

    public void setFa(double fa) {
        this.fa = fa;
    }

    public double getFb() {
        return fb;
    }

    public void setFb(double fb) {
        this.fb = fb;
    }

    public double getXi() {
        return xi;
    }

    public void setXi(double xi) {
        this.xi = xi;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getFxi() {
        return fxi;
    }

    public void setFxi(double fxi) {
        this.fxi = fxi;
    }
}
