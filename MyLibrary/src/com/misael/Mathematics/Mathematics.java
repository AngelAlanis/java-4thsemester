package com.misael.Mathematics;

import net.objecthunter.exp4j.ExpressionBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Mathematics {

    public static double euler = 2.71828182845904523536;

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

    public static double nextSolucionSecante(int i, ArrayList<Double> xi, ArrayList<Double> fxi) {
        return new ExpressionBuilder("_x2 - (_fx2 *((_x2 - _x1) / (_fx2 - _fx1)))")
                .variables("_x1", "_x2", "_fx1", "_fx2")
                .build()
                .setVariable("_x1", xi.get(i))
                .setVariable("_x2", xi.get(i - 1))
                .setVariable("_fx1", fxi.get(i))
                .setVariable("_fx2", fxi.get(i - 1))
                .evaluate();
    }

    public static double evaluarExpresion(String expresion, double x) {
        return new ExpressionBuilder(expresion)
                .variables("x")
                .build()
                .setVariable("x", x)
                .evaluate();
    }

    public static double metodoReglaFalsa(String expresion, double tolerancia) {
        double[] intervaloInicial = busquedaIncremental(expresion);

        ArrayList<Double> a     = new ArrayList<>();
        ArrayList<Double> b     = new ArrayList<>();
        ArrayList<Double> fa    = new ArrayList<>();
        ArrayList<Double> fb    = new ArrayList<>();
        ArrayList<Double> xi    = new ArrayList<>();
        ArrayList<Double> fxi   = new ArrayList<>();
        ArrayList<Double> error = new ArrayList<>();

        int i = 0;

        //Se añaden los valores iniciales de a y b según el intervalo
        a.add(i, intervaloInicial[0]);
        b.add(i, intervaloInicial[1]);

        do {
            //Se calcula fa y fb según el valor actual de a y b
            fa.add(i, evaluarExpresion(expresion, a.get(i)));
            fb.add(i, evaluarExpresion(expresion, b.get(i)));

            //Se calcula xi con la fórmula de xi+1
            xi.add(i, nextSolucionReglaFalsa(a.get(i), b.get(i), fa.get(i), fb.get(i)));

            //Si es la primera iteración el error se pone en 0
            if (i == 0) {
                error.add(i, 0.0);
            } else {
                error.add(i, error(xi.get(i), xi.get(i - 1)));
            }

            fxi.add(i, evaluarExpresion(expresion, xi.get(i)));

            i++;

            //Se evalua si fa * fb es menor que 0
            if ((fa.get(i - 1) * fxi.get(i - 1)) < 0) {
                b.add(i, xi.get(i - 1));
                a.add(i, a.get(i - 1));
            } else {
                a.add(i, xi.get(i - 1));
                b.add(i, b.get(i - 1));
            }

        } while (i <= 1 && error.get(i - 1) == 0 || absoluto(error.get(i - 1)) > tolerancia);

        System.out.println(xi.toString());
        return xi.get(i - 1);
    }

    public static double metodoBiseccion(String expresion, double tolerancia) {
        double[] intervaloInicial = busquedaIncremental(expresion);

        ArrayList<Double> a     = new ArrayList<>();
        ArrayList<Double> b     = new ArrayList<>();
        ArrayList<Double> xi    = new ArrayList<>();
        ArrayList<Double> error = new ArrayList<>();
        ArrayList<Double> fa    = new ArrayList<>();
        ArrayList<Double> fxi   = new ArrayList<>();

        int i = 0;

        a.add(i, intervaloInicial[0]);
        b.add(i, intervaloInicial[1]);

        do {
            //Se calcula fa y fb según el valor actual de a
            fa.add(i, evaluarExpresion(expresion, a.get(i)));

            //Se calcula xi con la fórmula de xi+1
            xi.add(i, nextSolucionBiseccion(a.get(i), b.get(i)));

            //Si es la primera iteración el error se pone en 0
            if (i == 0) {
                error.add(i, 0.0);
            } else {
                error.add(i, error(xi.get(i), xi.get(i - 1)));
            }

            fxi.add(i, evaluarExpresion(expresion, xi.get(i)));

            i++;

            //Se evalua si fa * fb es menor que 0
            if ((fa.get(i - 1) * fxi.get(i - 1)) < 0) {
                b.add(i, xi.get(i - 1)); //En b se pone el valor de xi
                a.add(i, a.get(i - 1)); // a se queda igual
            } else {
                a.add(i, xi.get(i - 1)); //En a se pone el valor de xi
                b.add(i, b.get(i - 1)); //b se queda igual
            }

        } while (i <= 1 && error.get(i - 1) == 0 || absoluto(error.get(i - 1)) > tolerancia);

        System.out.println(xi.toString());

        return xi.get(i - 1);
    }

    public static double metodoSecante(String expresion, double tolerancia) {
        double[] intervaloInicial = busquedaIncremental(expresion);

        double xm1 = evaluarExpresion(expresion, intervaloInicial[0]);
        double x0  = evaluarExpresion(expresion, intervaloInicial[1]);

        if (absoluto(xm1) < absoluto(x0)) {
            double temp = x0;
            x0  = xm1;
            xm1 = temp;
        }


        return Double.NaN;
    }

    public static double[] busquedaIncremental(String expresion) {
        double[] resultados = new double[2];

        ArrayList<Double> fx = new ArrayList<>();

        int i = -1;

        do {
            i++;
            fx.add(evaluarExpresion(expresion, i));
        } while (i <= 1 || (fx.get(i) * fx.get(i - 1)) >= 0);

        resultados[0] = i - 1;
        resultados[1] = i;

        return resultados;
    }

}