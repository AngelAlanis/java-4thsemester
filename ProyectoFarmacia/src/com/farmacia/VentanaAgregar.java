package com.farmacia;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class VentanaAgregar extends JFrame {

    JPanel panelPrincipal = new JPanel();
    ImageIcon iconoAceptar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/aceptar.png")));
    ImageIcon iconoCancelar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/cancelar.png")));
    JLabel labelTitulo = new JLabel("Nuevo producto");
    JLabel labelDescripcion = new JLabel("Descripción del producto");
    JLabel labelPrecio = new JLabel("Precio de venta");
    JLabel labelStock = new JLabel("Productos en existencia");

    JTextField tfDescripcion = new JTextField();
    JTextField tfPrecio = new JTextField();
    JTextField tfStock = new JTextField();

    JButton btnAceptar = new JButton("Aceptar");
    JButton btnCancelar = new JButton("Cancelar");

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
        btnAceptar.setBounds(60, 380, 150, 30);
        btnCancelar.setBounds(240, 380, 150, 30);

        btnAceptar.setIcon(iconoAceptar);
        btnCancelar.setIcon(iconoCancelar);

        panelPrincipal.add(labelTitulo);
        panelPrincipal.add(labelDescripcion);
        panelPrincipal.add(tfDescripcion);
        panelPrincipal.add(labelPrecio);
        panelPrincipal.add(tfPrecio);
        panelPrincipal.add(labelStock);
        panelPrincipal.add(tfStock);
        panelPrincipal.add(btnAceptar);
        panelPrincipal.add(btnCancelar);

        btnAceptar.addActionListener(e -> {
            try {
                Launcher.loginScreen.ventanaPrincipal.escribirInventario(generarFolio(), tfDescripcion.getText(), Integer.parseInt(tfPrecio.getText()) , Integer.parseInt(tfStock.getText()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            dispose();
            JOptionPane.showMessageDialog(null, "Producto agregado correctamente!");
        });

        btnCancelar.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar?", "", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {
                dispose();
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
