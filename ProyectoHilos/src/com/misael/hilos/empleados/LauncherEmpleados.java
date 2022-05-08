package com.misael.hilos.empleados;

import javax.swing.JFrame;

public class LauncherEmpleados extends JFrame {

    public LauncherEmpleados() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GamePanel panelPrincipal = new GamePanel();
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
