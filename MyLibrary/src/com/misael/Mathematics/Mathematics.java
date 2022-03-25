package com.misael.Mathematics;

import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class Mathematics {

    ArrayList<ArrayList<Double>> solucionesReglaFalsa = new ArrayList<>();

    public static double potencia(double numero, double exponente) {

        if (exponente == 0.0) {
            return 1.0;
        }

        if (exponente > 0) {
            double resultado = numero;
            for (int i = 0; i < exponente - 1; i++) {
                resultado = resultado * numero;
            }

            return resultado;
        }

        if (exponente < 0) {
            double resultado = numero;
            for (double i = exponente - 1; i < 0; i++) {
                resultado = resultado / numero;
            }

            return resultado;
        }

        return Double.NaN;

    }

    public static double raiz(double numero) {

        double temp;
        double resultado = numero / 2;

        do {
            temp      = resultado;
            resultado = (temp + (numero / temp)) / 2;
        } while ((temp - resultado) != 0);

        return resultado;
    }

    public static double absoluto(double numero) {
        if (numero >= 0) {
            return numero;
        }

        if (numero < 0) {
            return numero * -1;
        }

        return Double.NaN;
    }

    public static double error(double a, double b) {
        return absoluto((a - b) / a);
    }

    public static double nextSolucionBiseccion(double a, double b) {
        return (a + b) / 2;
    }

    public static double nextSolucionReglaFalsa(double a, double b, double fa, double fb) {
        return (((a * fb) - (b * fa))
                / (fb - fa));
    }

    public static double evaluarExpresion(String expresion, double x) {
        return new ExpressionBuilder(expresion)
                .variables("x")
                .build()
                .setVariable("x", x)
                .evaluate();
    }

    public static double metodoReglaFalsa() {

        return Double.NaN;
    }

    public static double[] busquedaIncremental(String expresion) {
        double[] resultados = new double[2];

        double fx  = 0;
        double fx1 = 0;
        int    i   = 0;

        do {
            fx = evaluarExpresion(expresion, i);
            i++;
            fx1 = evaluarExpresion(expresion, i);
            i++;
        } while ((fx * fx1) > 0);

        resultados[0] = i - 1;
        resultados[1] = 1;

        return resultados;
    }


}
