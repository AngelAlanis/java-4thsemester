package com.farmacia;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.print.PrinterException;
import java.util.Objects;


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
        if (cantidadIngresada < total) {
            JOptionPane.showMessageDialog(null, "La cantidad ingresada es menor a la del costo total.");
            return;
        }

        JFrame frameRecibo = new JFrame();
        JTextPane tpRecibo = new JTextPane();
        tpRecibo.setEditable(false);

        frameRecibo.setLayout(new BorderLayout());

        DateTimeFormatter formatoFecha = DateTimeFormatter.ISO_DATE;
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String horaActual = formatoHora.format(now);
        String fechaActual = formatoFecha.format(now);

        ImageIcon logoByN = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/logoRecibo.png")));
        JLabel labelLogo = new JLabel();
        labelLogo.setIcon(logoByN);

        tpRecibo.setText(tpRecibo.getText() + ("--------------------------------------------------------"));
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n\n%34s", "VesaPharmacy")));
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%41s", "Blvd. Felipe Pescador 1830")));
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%45s", "Nueva Vizcaya, 34080 Durango, Dgo.")));
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%36s", "+52 610 438 2356")));

        tpRecibo.setText(tpRecibo.getText() + ("\n\n--------------------------------------------------------"));
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%-5s %-20.20s %-12s %-12s", "Cant.", "Articulo", "Precio unit.", "Importe")));
        tpRecibo.setText(tpRecibo.getText() + ("\n--------------------------------------------------------"));

        for (Producto listaProducto : listaProductos) {
            tpRecibo.setText(tpRecibo.getText() + (String.format("\n%-5s %-20.20s $%-12.2f $%-12.2f",
                    listaProducto.getCantidad(),
                    listaProducto.getDescripcion(),
                    listaProducto.getPrecioVenta(),
                    listaProducto.getImporte())));
        }

        tpRecibo.setText(tpRecibo.getText() + ("\n--------------------------------------------------------"));

        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%s $%41.2f", "Total", total)));
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%s $%38.2f", "Recibido", cantidadIngresada)));
        float cambio = cantidadIngresada - total;
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%s $%40.2f", "Cambio", cambio)));

        tpRecibo.setText(tpRecibo.getText() + ("\n--------------------------------------------------------"));


        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%15s %5s %10s %5s", "Hora:", horaActual, "Fecha:", fechaActual)));

        tpRecibo.setText(tpRecibo.getText() + ("\n********************************************************"));

        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%38s", "GRACIAS POR SU COMPRA")));

        tpRecibo.setText(tpRecibo.getText() + ("\n********************************************************"));

        tpRecibo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        frameRecibo.add(tpRecibo, BorderLayout.CENTER);
        frameRecibo.getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frameRecibo.pack();
        frameRecibo.setVisible(true);
        frameRecibo.setLocationRelativeTo(null);

        try {
            tpRecibo.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }

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
