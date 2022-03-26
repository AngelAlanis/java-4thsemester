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
                b.add(i, fxi.get(i - 1));
            }

            a.add(i, fxi.get(i - 1));

        } while (error.get(i - 1) > tolerancia);

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
                b.add(i, fxi.get(i - 1));
            }

            a.add(i, fxi.get(i - 1));

        } while (error.get(i - 1) > tolerancia);

        return xi.get(i - 1);
    }

    public static double[] busquedaIncremental(String expresion) {
        double[] resultados = new double[2];

        double fx;
        double fx1 = 0;
        int    i   = 0;

        do {
            fx = evaluarExpresion(expresion, i);
            i++;

            if ((fx * fx1) < 0) {
                break;
            }

            fx1 = evaluarExpresion(expresion, i);
            i++;
        } while ((fx * fx1) > 0);

        resultados[0] = i - 1;
        resultados[1] = 1;

        return resultados;
    }

    public static String imprimirTabla(ArrayList<Double> a, ArrayList<Double> b, ArrayList<Double> fa, ArrayList<Double> fb, ArrayList<Double> xi, ArrayList<Double> fxi, ArrayList<Double> error) {

        String respuesta = "";

        System.out.println("a");
        System.out.println(a.toString());
        System.out.println("fa");
        System.out.println(fa.toString());
        System.out.println("b");
        System.out.println(b.toString());
        System.out.println("fb");
        System.out.println(fb.toString());
        System.out.println("xi");
        System.out.println(xi.toString());
        System.out.println("fxi");
        System.out.println(fxi.toString());
        System.out.println("error");
        System.out.println(error.toString());


//        for(int i = 0; i < a.size(); i++){
//            respuesta += "  " + i;
//            respuesta += "  " + a.get(i);
//            respuesta += "  " + fa.get(i);
//            respuesta += "  " + b.get(i);
//            respuesta += "  " + fb.get(i);
//            respuesta += "  " + xi.get(i);
//            respuesta += "  " + error.get(i);
//            respuesta += "  " + fxi.get(i);
//            respuesta += "\n";
//        }
        return "";
    }
}