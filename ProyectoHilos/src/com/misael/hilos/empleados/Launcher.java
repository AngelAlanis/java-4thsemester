package com.misael.hilos.empleados;

import javax.swing.JFrame;

public class Launcher extends JFrame {

    public Launcher() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GamePanel panelPrincipal = new GamePanel();
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        var interfazEmpleados = new Launcher();
    }


}
