package com.farmacia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class VentanaAgregar extends JFrame {

    JPanel panelPrincipal = new JPanel();

    JLabel labelTitulo = new JLabel("Nuevo producto");
    JLabel labelDescripcion = new JLabel("Descripción del producto");
    JLabel labelPrecio = new JLabel("Precio de venta");
    JLabel labelStock = new JLabel("Productos en existencia");

    JTextField tfDescripcion = new JTextField();
    JTextField tfPrecio = new JTextField();
    JTextField tfStock = new JTextField();

    JButton btnAgregar = new JButton("Agregar");
    JButton btnCancelar = new JButton("Cancelar");

    private String folio;
    private String descripcion;
    private Float precio;
    private int stock;

    public VentanaAgregar(){
        setSize(450, 550);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(panelPrincipal);
        configurarComponentes();
        setVisible(true);
    }

    public void configurarComponentes () {
        panelPrincipal.setLayout(null);
        labelTitulo.setFont(new Font("Myriad Pro", Font.BOLD, 20 ));
        labelTitulo.setBounds(60,50,200,30);
        labelDescripcion.setBounds(60, 100, 300, 30);
        tfDescripcion.setBounds(60, 140, 330, 30);
        labelPrecio.setBounds(60, 180, 300, 30);
        tfPrecio.setBounds(60, 220, 330, 30);
        labelStock.setBounds(60, 260, 300, 30);
        tfStock.setBounds(60, 300, 330, 30);
        btnAgregar.setBounds(60, 380, 150, 30);
        btnCancelar.setBounds(240, 380, 150, 30);

        panelPrincipal.add(labelTitulo);
        panelPrincipal.add(labelDescripcion);
        panelPrincipal.add(tfDescripcion);
        panelPrincipal.add(labelPrecio);
        panelPrincipal.add(tfPrecio);
        panelPrincipal.add(labelStock);
        panelPrincipal.add(tfStock);
        panelPrincipal.add(btnAgregar);
        panelPrincipal.add(btnCancelar);

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Launcher.loginScreen.ventanaPrincipal.escribirInventario(generarFolio(), tfDescripcion.getText(), Integer.parseInt(tfPrecio.getText()) , Integer.parseInt(tfStock.getText()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                dispose();
                JOptionPane.showMessageDialog(null, "Producto agregado correctamente!");
            }
        });
    }

    public String generarFolio(){
        int folio;
        Random rnd = new Random();
        folio = rnd.nextInt(10000);
        return String.valueOf(folio);
    }

}
