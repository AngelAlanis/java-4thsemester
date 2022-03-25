package com.misael.Mathematics;

public class Mathematics {

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

        if(exponente < 0){
            double resultado = numero;
            for (double i = exponente - 1; i < 0; i++) {
                resultado = resultado / numero;
            }

            return resultado;
        }

        return -1;

    }
}
