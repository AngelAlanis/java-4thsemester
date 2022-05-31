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

    public static float[] productosMedios(int x0, int x1, int n) {
        float[] respuestasEnteras = new float[n + 2];
        float[] respuestasFloat   = new float[n + 2];
        int     siguienteValor;
        String  valorToString;
        int     length;
        respuestasEnteras[0] = x0;
        respuestasEnteras[1] = x1;
        respuestasFloat[0]   = respuestasEnteras[0] / 10000;
        respuestasFloat[1]   = respuestasEnteras[1] / 10000;

        for (int i = 2; i < respuestasEnteras.length; i++) {
            siguienteValor = (int) (respuestasEnteras[i - 1] * respuestasEnteras[i - 2]);
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

    public static float[] multiplicadorConstante(int x0, int multiplicador, int n) {
        float[] respuestasEnteras = new float[n + 1];
        float[] respuestasFloat   = new float[n + 1];
        int     siguienteValor;
        String  valorToString;
        int     length;
        respuestasEnteras[0] = x0;
        respuestasFloat[0]   = respuestasEnteras[0] / 10000;

        for (int i = 1; i < respuestasEnteras.length; i++) {
            siguienteValor = (int) (respuestasEnteras[i - 1] * multiplicador);
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

    public static float[] congruencialMixto(int x0, int a, int c, int m, int n) {
        float[] respuestasEnteras = new float[n + 1];
        float[] respuestasFloat   = new float[n + 1];
        int     siguienteValor;

        respuestasEnteras[0] = x0;
        respuestasFloat[0]   = respuestasEnteras[0] / (m - 1);


        for (int i = 1; i < respuestasEnteras.length; i++) {
            siguienteValor       = (int) (((a * respuestasEnteras[i - 1]) + c) % m);
            System.out.println(siguienteValor);
            respuestasEnteras[i] = siguienteValor;
            respuestasFloat[i]   = respuestasEnteras[i] / (m - 1);
        }
        return respuestasFloat;

    }

    public static float[] congruencialAditivo(int x0, int x1, int x2, int x3, int x4, int m, int n) {
        float[] respuestasEnteras = new float[n + 1];
        float[] respuestasFloat   = new float[n + 1];
        int     siguienteValor;

        respuestasEnteras[0] = x0;
        respuestasEnteras[1] = x1;
        respuestasEnteras[2] = x2;
        respuestasEnteras[3] = x3;
        respuestasEnteras[4] = x4;
        respuestasFloat[0]   = respuestasEnteras[0] / (m - 1);
        respuestasFloat[1]   = respuestasEnteras[1] / (m - 1);
        respuestasFloat[2]   = respuestasEnteras[2] / (m - 1);
        respuestasFloat[3]   = respuestasEnteras[3] / (m - 1);
        respuestasFloat[4]   = respuestasEnteras[4] / (m - 1);

        for (int i = 5; i < respuestasEnteras.length; i++) {
            siguienteValor       = (int) ((respuestasEnteras[i - 1] + respuestasEnteras[i - 5])) % (m - 1);
            respuestasEnteras[i] = siguienteValor;
            respuestasFloat[i]   = respuestasEnteras[i] / (m - 1);
        }
        return respuestasFloat;

    }
}
