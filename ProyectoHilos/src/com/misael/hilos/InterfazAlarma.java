package com.misael.hilos;


import javax.swing.*;
import java.awt.*;

public class InterfazAlarma extends JFrame {
    private FondoFuego panelPrincipal;
    private JLabel     labelFireStatus;
    private JButton    btnToggleFire;
    private ImageIcon  fireAlarmImage;

    public InterfazAlarma() {
        this.setSize(640, 480);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        configurarComponentes();
        initActionListeners();
        this.setContentPane(panelPrincipal);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void configurarComponentes() {
        fireAlarmImage  = new ImageIcon("ProyectoHilos/src/resources/fireAlarm.png");
        panelPrincipal  = new FondoFuego();
        btnToggleFire   = new JButton("");
        labelFireStatus = new JLabel("false");

        panelPrincipal.add(btnToggleFire);
        btnToggleFire.setIcon(fireAlarmImage);
    }

    private void initActionListeners() {

        Alarma alarma = new Alarma();
        Thread hilo   = new Thread(alarma);
        hilo.start();

        btnToggleFire.addActionListener(e -> {

            if (Alarma.isOnFire) {
                Alarma.isOnFire           = false;
                panelPrincipal.imageInUse = panelPrincipal.imageNormal;
            } else {
                Alarma.isOnFire           = true;
                panelPrincipal.imageInUse = panelPrincipal.imageFire;

            }

            labelFireStatus.setText(String.valueOf(Alarma.isOnFire));
            panelPrincipal.repaint();
        });
    }
}
