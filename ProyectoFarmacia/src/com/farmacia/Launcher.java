package com.farmacia;

import com.formdev.flatlaf.FlatDarkLaf;

public class Launcher {

    static LoginScreen loginScreen;

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        loginScreen = new LoginScreen();
    }
}
