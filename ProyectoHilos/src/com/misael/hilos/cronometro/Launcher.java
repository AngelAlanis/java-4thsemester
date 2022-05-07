package com.misael.hilos.cronometro;

import com.formdev.flatlaf.FlatDarkLaf;

public class Launcher {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        var interfazCronometro = new InterfazCronometro();
    }

}
