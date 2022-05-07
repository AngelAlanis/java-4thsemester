package com.misael.hilos.sincronizados;

import com.formdev.flatlaf.FlatDarkLaf;

public class Launcher {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        var interfazCuatroHilos = new InterfazCuatroHilos();
    }
}

