package com.misael.hilos.empleados;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LauncherEmpleados extends JFrame {

    GamePanel panelPrincipal;

    public LauncherEmpleados() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panelPrincipal = new GamePanel();
        this.setContentPane(panelPrincipal);
        this.pack();
        initActionListeners();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initActionListeners() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                panelPrincipal.stopMusic();

                for (int i = 0; i < panelPrincipal.npcs.length; i++) {
                    panelPrincipal.npcs[i].empleadoThread.stopThread();
                }

                panelPrincipal.stopThread();
            }
        });
    }
}
