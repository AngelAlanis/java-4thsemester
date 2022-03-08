package com.farmacia;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class Launcher {

    static LoginScreen loginScreen;

    public static void main(String[] args) throws Exception {
        FlatLightLaf.setup();
        //loginScreen = new LoginScreen();
        VentanaPrincipal launcher = new VentanaPrincipal();
    }

}
