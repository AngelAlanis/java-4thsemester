package com.misael.hilos.mainScreen;

import com.formdev.flatlaf.FlatDarkLaf;

public class Launcher {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        var main = new MainScreenGUI();
    }

}
