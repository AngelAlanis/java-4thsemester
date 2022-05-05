package com.misael.hilos.empleados;

import javax.swing.JFrame;

public class InterfazEmpleados extends JFrame {

    private GamePanel panelPrincipal;

    public InterfazEmpleados() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        configurarComponentes();
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void configurarComponentes() {
        panelPrincipal = new GamePanel();
    }

    public static void main(String[] args) {
        var interfazEmpleados = new InterfazEmpleados();
    }


}
