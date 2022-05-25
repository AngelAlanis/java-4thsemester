package com.misael.escuelabd;

import javax.swing.*;
import java.awt.*;

public class EditAlumnoGUI extends JFrame {
    private JTextField        tfNombre;
    private JLabel            labelNombre;
    private JLabel            labelGenero;
    private JComboBox<String> cbGenero;
    private JLabel            labelMatricula;
    private JTextField        tfMatricula;
    private JLabel            labelTitulo;
    private JPanel            panelMain;
    private JButton           btnRegistrar;
    private JLabel            labelFechaNacimiento;
    private JTextField        tfFechaNacimiento;
    private JLabel            labelTelefono;
    private JTextField        tfTelefono;
    private JButton           btnCancelar;

    MainGUI main;

    public EditAlumnoGUI(MainGUI main) {
        this.main = main;
        this.setSize(600, 700);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panelMain);
        initComponents();
        initActionListeners();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initComponents() {
        tfNombre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfMatricula.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfFechaNacimiento.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfTelefono.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        cbGenero.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
    }

    public void initActionListeners() {
        btnCancelar.addActionListener(e -> this.dispose());
        btnRegistrar.addActionListener(e -> main.conectar.executeQuery(""));
    }

}
