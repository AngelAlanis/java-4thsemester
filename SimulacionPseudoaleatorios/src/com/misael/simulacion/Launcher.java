package com.misael.simulacion;

import java.util.Arrays;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("Método centros al cuadrado dos dígitos: x0 = 83, n = 6");
        System.out.println(Arrays.toString(Metodos.centrosCuadradoDosDigitos(83, 6)));

        System.out.println("\nMétodo centros al cuadrado cuatro dígitos: x0 = 5735, n = 6");
        System.out.println(Arrays.toString(Metodos.centrosCuadradoCuatroDigitos(5735, 6)));

        System.out.println("\nMétodo fraccionario: x0 = 0.528, n = 13");
        System.out.println(Arrays.toString(Metodos.fraccionario(0.528, 13)));

        System.out.println("\nMétodo centros productos medios: x0 = 5015, x1 = 5734, n = 5");
        System.out.println(Arrays.toString(Metodos.productosMedios(5015, 5734, 5)));

        System.out.println("\nMétodo multiplicador constante: x0 = 9803, a = 6965, n = 6");
        System.out.println(Arrays.toString(Metodos.multiplicadorConstante(9803, 6965, 5)));

        System.out.println("\nMétodo congruencial aditivo: x0 = 65, x1 = 89, x2 = 98, x3 = 3, x4 = 69, m = 100, n = 7");
        System.out.println(Arrays.toString(Metodos.congruencialAditivo(65, 89, 98, 3, 69, 100, 7)));

        System.out.println("\nMétodo congruencial mixto: x0 = 37, a = 19, c = 33, m = 100, n = 4");
        System.out.println(Arrays.toString(Metodos.congruencialMixto(37, 19, 33, 100, 4)));
    }
}
