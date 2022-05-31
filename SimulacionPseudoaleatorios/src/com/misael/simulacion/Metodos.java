package com.misael.simulacion;

public class Metodos {

    public static float[] fraccionario(double x0, int n) {
        float[] respuestas = new float[n + 1];
        float   siguienteValor;
        String  valorToString;
        int     length;
        respuestas[0] = (float) x0;

        for (int i = 1; i < respuestas.length; i++) {
            siguienteValor = (float) Math.pow(respuestas[i - 1], 2);
            valorToString  = String.valueOf(siguienteValor);

            if (valorToString.length() % 2 == 1) {
                String[] splitString = valorToString.split("\\.");
                valorToString = splitString[0] + ".0" + splitString[1];
            }

            length = valorToString.length();

            respuestas[i] = Float.parseFloat("0." + valorToString.charAt((length / 2) - 1) + "" + valorToString.charAt((length / 2)) + "" + valorToString.charAt((length / 2) + 1));
        }
        return respuestas;
    }

    public static float[] centrosCuadradoDosDigitos(int x0, int n) {
        float[] respuestasEnteras = new float[n + 1];
        float[] respuestasFloat   = new float[n + 1];
        int     siguienteValor;
        String  valorToString;
        int     length;
        respuestasEnteras[0] = x0;
        respuestasFloat[0]   = respuestasEnteras[0] / 100;

        for (int i = 1; i < respuestasEnteras.length; i++) {
            siguienteValor = (int) Math.pow(respuestasEnteras[i - 1], 2);
            valorToString  = String.valueOf(siguienteValor);

            if (valorToString.length() % 2 == 1) {
                valorToString = "0" + valorToString;
            }

            length = valorToString.length();

            respuestasEnteras[i] = Integer.parseInt(valorToString.charAt((length / 2) - 1) + "" + valorToString.charAt(length / 2));
            respuestasFloat[i]   = respuestasEnteras[i] / 100;
        }
        return respuestasFloat;
    }

    public static float[] centrosCuadradoCuatroDigitos(int x0, int n) {
        float[] respuestasEnteras = new float[n + 1];
        float[] respuestasFloat   = new float[n + 1];
        int     siguienteValor;
        String  valorToString;
        int     length;
        respuestasEnteras[0] = x0;
        respuestasFloat[0]   = respuestasEnteras[0] / 10000;

        for (int i = 1; i < respuestasEnteras.length; i++) {
            siguienteValor = (int) Math.pow(respuestasEnteras[i - 1], 2);
            valorToString  = String.valueOf(siguienteValor);

            if (valorToString.length() % 2 == 1) {
                valorToString = "0" + valorToString;
            }

            length = valorToString.length();

            respuestasEnteras[i] = Integer.parseInt(valorToString.charAt((length / 2) - 2) + "" + valorToString.charAt((length / 2) - 1) + "" + valorToString.charAt((length / 2)) + "" + valorToString.charAt((length / 2) + 1));
            respuestasFloat[i]   = respuestasEnteras[i] / 10000;
        }
        return respuestasFloat;
    }

    public static void productosMedios() {

    }

    public static void multiplicadorConstante() {

    }

    public static void congruencialMixto() {

    }

    public static void congruencialAditivo() {

    }
}
