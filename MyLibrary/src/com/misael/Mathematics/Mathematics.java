package com.misael.Mathematics;

import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

public class Mathematics {

    static ArrayList<ReglaFalsa>    tablaReglaFalsa    = new ArrayList<>();
    static ArrayList<Biseccion>     tablaBiseccion     = new ArrayList<>();
    static ArrayList<Secante>       tablaSecante       = new ArrayList<>();
    static ArrayList<NewtonRaphson> tablaNewtonRaphson = new ArrayList<>();

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

    public static double promedio(double numero1, double numero2) {
        return ((numero1 + numero2) / 2);
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

    public static double nextSolucionSecante(ArrayList<Secante> filas) {
        int i = filas.size() - 1;
        return new ExpressionBuilder(" _x2 - (((_x2 - _x1) / (_fx2 - _fx1)) * _fx2) ")
                .variables("_x1", "_x2", "_fx1", "_fx2")
                .build()
                .setVariable("_x1", filas.get(i - 2).getXi())
                .setVariable("_x2", filas.get(i - 1).getXi())
                .setVariable("_fx1", filas.get(i - 2).getFxi())
                .setVariable("_fx2", filas.get(i - 1).getFxi())
                .evaluate();
    }

    public static double nextSolucionNewtonRapson(String expresion, String derivada, double x) {

        double evaluacionExpresion = new ExpressionBuilder(expresion)
                .variables("x")
                .build()
                .setVariable("x", x)
                .evaluate();

        double evaluacionDerivada = new ExpressionBuilder(derivada)
                .variables("x")
                .build()
                .setVariable("x", x)
                .evaluate();

        return (x - (evaluacionExpresion / evaluacionDerivada));

    }

    public static double evaluarExpresion(String expresion, double x) {
        return new ExpressionBuilder(expresion)
                .variables("x")
                .build()
                .setVariable("x", x)
                .evaluate();
    }

    public static double metodoReglaFalsa(String expresion, double tolerancia) {
        double[] intervaloInicial = new double[2];

        try {
            intervaloInicial = busquedaIncremental(expresion);
        } catch (TeoremaDeBolzanoException e) {
            return Double.NaN;
        }

        ArrayList<ReglaFalsa> filas = new ArrayList<>();

        filas.add(new ReglaFalsa());

        int i = 0;

        //Se añaden los valores iniciales de a y b según el intervalo
        filas.get(i).setA(intervaloInicial[0]);
        filas.get(i).setB(intervaloInicial[1]);

        do {
            //Se calcula fa y fb según el valor actual de a y b
            filas.get(i).setFa(evaluarExpresion(expresion, filas.get(i).getA()));
            filas.get(i).setFb(evaluarExpresion(expresion, filas.get(i).getB()));

            //Se calcula xi con la fórmula de xi+1
            filas.get(i).setXi(nextSolucionReglaFalsa(
                    filas.get(i).getA(),
                    filas.get(i).getB(),
                    filas.get(i).getFa(),
                    filas.get(i).getFb()));

            //Si es la primera iteración el error se pone en 0
            if (i == 0) {
                filas.get(i).setError(0.0);
            } else {
                filas.get(i).setError(error(
                        filas.get(i).getXi(),
                        filas.get(i - 1).getXi()));
            }

            filas.get(i).setFxi(evaluarExpresion(expresion, filas.get(i).getXi()));

            filas.get(i).setI(i);

            i++;

            filas.add(new ReglaFalsa());

            //Se evalúa si fa * fb es menor que 0
            if ((filas.get(i - 1).getFa() * (filas.get(i - 1).getFxi()) < 0)) {

                filas.get(i).setB(filas.get(i - 1).getXi());
                filas.get(i).setA(filas.get(i - 1).getA());
            } else {
                filas.get(i).setA(filas.get(i - 1).getXi());
                filas.get(i).setB(filas.get(i - 1).getB());
            }

        } while (i <= 1 || absoluto(filas.get(i - 1).getError()) > tolerancia);

        filas.remove(i);

        setTablaReglaFalsa(filas);

        return filas.get(i - 1).getXi();
    }

    public static double metodoBiseccion(String expresion, double tolerancia) {
         double[] intervaloInicial = new double[2];

        try {
            intervaloInicial = busquedaIncremental(expresion);
        } catch (TeoremaDeBolzanoException e) {
            return Double.NaN;
        }

        ArrayList<Biseccion> filas = new ArrayList<>();

        filas.add(new Biseccion());

        int i = 0;

        filas.get(i).setA(intervaloInicial[0]);
        filas.get(i).setB(intervaloInicial[1]);

        do {
            //Se calcula fa y fb según el valor actual de a
            filas.get(i).setFa(evaluarExpresion(expresion, filas.get(i).getA()));

            //Se calcula xi con la fórmula de xi+1
            filas.get(i).setXi(nextSolucionBiseccion(filas.get(i).getA(), filas.get(i).getB()));

            //Si es la primera iteración el error se pone en 0
            if (i == 0) {
                filas.get(i).setError(0.0);
            } else {
                filas.get(i).setError(error(filas.get(i).getXi(), filas.get(i - 1).getXi()));
            }

            filas.get(i).setFxi(evaluarExpresion(expresion, filas.get(i).getXi()));

            filas.get(i).setI(i);

            i++;

            filas.add(new Biseccion());

            //Se evalua si fa * fxi es menor que 0
            if ((filas.get(i - 1).getFa() * filas.get(i - 1).getFxi()) < 0) {
                filas.get(i).setB(filas.get(i - 1).getXi()); //En b se pone el valor de xi
                filas.get(i).setA(filas.get(i - 1).getA()); // a se queda igual
            } else {
                filas.get(i).setA(filas.get(i - 1).getXi()); //En a se pone el valor de xi
                filas.get(i).setB(filas.get(i - 1).getB()); //b se queda igual
            }

        } while (i <= 1 || absoluto(filas.get(i - 1).getError()) > tolerancia);

        filas.remove(i);

        setTablaBiseccion(filas);
        return filas.get(i - 1).getXi();
    }

    public static double metodoSecante(String expresion, double tolerancia) {
        double[] intervaloInicial = new double[2];

        try {
            intervaloInicial = busquedaIncremental(expresion);
        } catch (TeoremaDeBolzanoException e) {
            return Double.NaN;
        }

        double xm1 = intervaloInicial[0];
        double x0  = intervaloInicial[1];

        double fxm1 = evaluarExpresion(expresion, xm1);
        double fx0  = evaluarExpresion(expresion, x0);

        if (absoluto(fxm1) < absoluto(fx0)) {
            double temp  = x0;
            double ftemp = fx0;
            x0   = xm1;
            fx0  = fxm1;
            xm1  = temp;
            fxm1 = ftemp;
        }


        ArrayList<Secante> filas = new ArrayList<>();

        filas.add(new Secante());

        filas.get(0).setI(0);
        filas.get(0).setXi(xm1);
        filas.get(0).setFxi(fxm1);
        filas.get(0).setError(0.0);

        int i = 1;

        filas.add(new Secante());

        filas.get(1).setI(1);
        filas.get(1).setXi(x0);
        filas.get(1).setFxi(fx0);
        filas.get(1).setError(0.0);

        do {
            filas.add(new Secante());
            i++;

            filas.get(i).setI(i);
            filas.get(i).setXi(nextSolucionSecante(filas));
            filas.get(i).setFxi((evaluarExpresion(expresion, filas.get(i).getXi())));
            filas.get(i).setError(error(filas.get(i).getXi(), filas.get(i - 1).getXi()));

        } while (absoluto(filas.get(i).getError()) > tolerancia);

        filas.remove(i);

        setTablaSecante(filas);
        return filas.get(i - 1).getXi();
    }

    public static double metodoNewtonRaphson(String expresion, double tolerancia) {
        double[] intervaloInicial = new double[2];

        try {
            intervaloInicial = busquedaIncremental(expresion);
        } catch (TeoremaDeBolzanoException e) {
            return Double.NaN;
        }

        double   solucionInicial  = promedio(intervaloInicial[0], intervaloInicial[1]);
        String   derivada         = derivar(expresion);

        ArrayList<NewtonRaphson> filas = new ArrayList<>();

        int i = 0;

        filas.add(new NewtonRaphson());

        filas.get(i).setI(i);
        filas.get(i).setXi(solucionInicial);
        filas.get(i).setError(0.0);
        filas.get(i).setFxi(evaluarExpresion(expresion, filas.get(i).getXi()));
        filas.get(i).setDfxi(evaluarExpresion(derivada, filas.get(i).getXi()));

        do {
            filas.add(new NewtonRaphson());
            i++;

            filas.get(i).setI(i);
            filas.get(i).setXi(nextSolucionNewtonRapson(expresion, derivada, filas.get(i - 1).getXi()));
            filas.get(i).setError(error(filas.get(i).getXi(), filas.get(i - 1).getXi()));
            filas.get(i).setFxi(evaluarExpresion(expresion, filas.get(i).getXi()));
            filas.get(i).setDfxi(evaluarExpresion(derivada, filas.get(i).getXi()));


        } while (absoluto(filas.get(i).getError()) > tolerancia);

        filas.remove(i);

        setTablaNewtonRaphson(filas);
        return filas.get(i - 1).getXi();

    }

    public static String derivar(String expresion) {
        Node nodoFuncion;
        Node nodoDerivada;
        DJep djep;

        try {
            djep = new DJep();
            djep.addStandardFunctions();
            djep.addStandardConstants();
            djep.addComplex();
            djep.setAllowAssignment(true);
            djep.setAllowUndeclared(true);
            djep.setImplicitMul(true);
            djep.addStandardDiffRules();

            nodoFuncion  = djep.parse(expresion);
            nodoDerivada = djep.differentiate(nodoFuncion, "x");
            nodoDerivada = djep.simplify(nodoDerivada);

            return djep.toString(nodoDerivada);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void setTablaNewtonRaphson(ArrayList<NewtonRaphson> tablaNewtonRaphson) {
        Mathematics.tablaNewtonRaphson = tablaNewtonRaphson;
    }

    public ArrayList<NewtonRaphson> getTablaNewtonRaphson() {
        return tablaNewtonRaphson;
    }

    public static double[] busquedaIncremental(String expresion) throws TeoremaDeBolzanoException {
        double[] resultados = new double[2];

        ArrayList<Double> fx = new ArrayList<>();

        int i = -1;

        do {
            i++;
            fx.add(evaluarExpresion(expresion, i));

            if (i > 80) {
                throw new TeoremaDeBolzanoException("Se realizaron " + i + " iteraciones y no se encontró el resultado.");
            }

        } while (i <= 1 || (fx.get(i) * fx.get(i - 1)) >= 0);

        resultados[0] = i - 1;
        resultados[1] = i;

        return resultados;
    }

    public ArrayList<ReglaFalsa> getTablaReglaFalsa() {
        return tablaReglaFalsa;
    }

    public static void setTablaReglaFalsa(ArrayList<ReglaFalsa> tablaReglaFalsa) {
        Mathematics.tablaReglaFalsa = tablaReglaFalsa;
    }

    public ArrayList<Biseccion> getTablaBiseccion() {
        return tablaBiseccion;
    }

    public static void setTablaBiseccion(ArrayList<Biseccion> tablaBiseccion) {
        Mathematics.tablaBiseccion = tablaBiseccion;
    }

    public ArrayList<Secante> getTablaSecante() {
        return tablaSecante;
    }

    public static void setTablaSecante(ArrayList<Secante> tablaSecante) {
        Mathematics.tablaSecante = tablaSecante;
    }

}