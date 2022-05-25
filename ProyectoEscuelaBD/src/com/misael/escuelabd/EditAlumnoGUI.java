package com.misael.escuelabd;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    int     idAlumno;

    public EditAlumnoGUI(MainGUI main, int idAlumno) {

        this.main     = main;
        this.idAlumno = idAlumno;

        this.setSize(600, 700);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panelMain);
        initComponents();
        initActionListeners();
        readData();
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

    public void readData() {

        ArrayList<String> data = main.conectar.readData("SELECT matricula, nombre, genero, fecha_nacimiento, telefono FROM alumno WHERE id_alumno = " + idAlumno);

        tfMatricula.setText(data.get(0));
        tfNombre.setText(data.get(1));
        cbGenero.setSelectedIndex(getGenderIndex(data.get(2)));
        tfFechaNacimiento.setText(data.get(3));
        tfTelefono.setText(data.get(4));
    }

    public int getGenderIndex(String gender) {
        switch (gender) {
            case "Masculino" -> {
                return 0;
            }
            case "Femenino" -> {
                return 1;
            }
        }
        return -1;
    }

    public void initActionListeners() {
        btnCancelar.addActionListener(e -> this.dispose());
        btnRegistrar.addActionListener(e -> main.conectar.executeQuery(""));
    }

}
