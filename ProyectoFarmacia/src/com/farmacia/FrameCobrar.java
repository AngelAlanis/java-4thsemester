package com.farmacia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FrameCobrar extends JFrame {
    JLabel labelInformacion = new JLabel("Ingrese la cantidad del cliente recibida");
    JPanel panelCobrar = new JPanel();
    JTextField tfCantidad = new JTextField();
    GridBagConstraints gbc = new GridBagConstraints();

    float cantidadIngresada;

    public FrameCobrar(){
        this.setLayout(new GridBagLayout());
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setUndecorated(true);
        this.setContentPane(panelCobrar);
        this.setSize(280, 120);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void pedirCantidad (){
        labelInformacion.setFont(new Font("Arial", Font.BOLD, 12));
        tfCantidad.setPreferredSize(new Dimension(150, 30));
        panelCobrar.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCobrar.add(labelInformacion, gbc);
        gbc.gridy = 1;
        panelCobrar.add(tfCantidad, gbc);

        definirActionListeners();
    }

    public void imprimirTicket(){

        System.out.println(cantidadIngresada);
    }

    public void definirActionListeners(){
        tfCantidad.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 10){
                    if(tfCantidad.getText().isEmpty()){

                    } else if ((Float.parseFloat(tfCantidad.getText())) <= 0) {

                    } else {
                        cantidadIngresada = Float.parseFloat(tfCantidad.getText());
                        System.out.println(cantidadIngresada);
                        dispose();
                    }
                } else if(e.getKeyChar() == 27){
                    dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

}
