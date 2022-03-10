package com.farmacia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FrameCobrar extends JFrame {
    JLabel labelInformacion = new JLabel("Ingrese la cantidad del cliente recibida");
    JPanel panelCobrar = new JPanel();
    JTextField tfCantidad = new JTextField();
    GridBagConstraints gbc = new GridBagConstraints();
    JTextPane tPRecibo = new JTextPane();

    float cantidadIngresada;

    public FrameCobrar() {
        this.setLayout(new GridBagLayout());
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setUndecorated(true);
        this.setContentPane(panelCobrar);
        this.setSize(280, 120);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void pedirCantidad(ArrayList<Producto> listaProductos, float total) {
        labelInformacion.setFont(new Font("Arial", Font.BOLD, 12));
        tfCantidad.setPreferredSize(new Dimension(150, 30));
        panelCobrar.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCobrar.add(labelInformacion, gbc);
        gbc.gridy = 1;
        panelCobrar.add(tfCantidad, gbc);

        definirActionListeners(listaProductos, total);
    }

    public void imprimirTicket(ArrayList<Producto> listaProductos, float total) {
        JFrame frameRecibo = new JFrame();
        JTextPane tpRecibo = new JTextPane();
        tpRecibo.setEditable(false);

        frameRecibo.add(tpRecibo);

        String resultado = "";

        resultado += "--------------------------------------------------------------";
        resultado += String.format("\n\n%35s", "VesaPharmacy.com");
        resultado += String.format("\n%35s", "Blvd. Felipe Pescador 1830, Nueva Vizcaya, 34080 Durango, Dgo.");
        resultado += String.format("\n%35s", "+52 610 438 2356");
        resultado += "\n\n--------------------------------------------------------------";
        resultado += String.format("\n%-22.22s %-12s %-12s %-12s", "Producto", "Precio", "Cantidad", "Importe");
        resultado += "\n--------------------------------------------------------------";

        for (int i = 0; i < listaProductos.size(); i++) {
            resultado += String.format("\n%-22.22s $%-12.2f %-12s $%-12.2f",
                    listaProductos.get(i).getDescripcion(),
                    listaProductos.get(i).getPrecioVenta(),
                    listaProductos.get(i).getCantidad(),
                    listaProductos.get(i).getImporte());
        }

        resultado += "\n--------------------------------------------------------------";

        resultado += String.format("\n%s %49.2f", "Total", total);
        resultado += String.format("\n%s $%46.2f", "Recibido", cantidadIngresada);
        float cambio = cantidadIngresada - total;
        resultado += String.format("\n%s $%48.2f", "Cambio", cambio);

        resultado += "\n--------------------------------------------------------------";

        resultado += String.format("\n%40s", "GRACIAS POR SU COMPRA");

        resultado += "\n--------------------------------------------------------------";

        tpRecibo.setText(resultado);
        frameRecibo.getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frameRecibo.pack();
        frameRecibo.setVisible(true);
        frameRecibo.setLocationRelativeTo(null);
        System.out.println(resultado);
    }

    public void definirActionListeners(ArrayList<Producto> listaProductos, float total) {
        tfCantidad.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 10) {
                    if (tfCantidad.getText().isEmpty()) {

                    } else if ((Float.parseFloat(tfCantidad.getText())) <= 0) {

                    } else {
                        cantidadIngresada = Float.parseFloat(tfCantidad.getText());
                        imprimirTicket(listaProductos, total);
                        dispose();
                    }
                } else if (e.getKeyChar() == 27) {
                    dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

}
