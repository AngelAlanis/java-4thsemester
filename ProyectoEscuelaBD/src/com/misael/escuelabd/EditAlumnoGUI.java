package com.misael.escuelabd;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
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
    String  matricula;
    String  nombre;
    String  genero;
    String  fechaNacimiento;
    String  telefono;
    String  sqlQuery;
    int     idAlumno;
    Color   placeHolderColor;

    public EditAlumnoGUI(MainGUI main, int idAlumno) {
        placeHolderColor = new Color(177, 179, 174);
        this.main        = main;
        this.idAlumno    = idAlumno;

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
        ArrayList<Object> data = main.conectar.readData("SELECT matricula, nombre, genero, fecha_nacimiento, telefono FROM alumno WHERE id_alumno = " + idAlumno);

        tfMatricula.setText(data.get(0).toString());
        tfNombre.setText(data.get(1).toString());
        cbGenero.setSelectedIndex(getGenderIndex(data.get(2).toString()));
        tfFechaNacimiento.setText(data.get(3).toString());
        tfTelefono.setText(data.get(4).toString());
    }

    public void saveData() {

        try {
            matricula       = Utilities.validate(tfMatricula.getText());
            nombre          = Utilities.validate(tfNombre.getText());
            genero          = Utilities.validate(getGenderString(cbGenero.getSelectedIndex()));
            fechaNacimiento = Utilities.validate(tfFechaNacimiento.getText());
            telefono        = Utilities.validate(tfTelefono.getText());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Verifique los datos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        sqlQuery = "UPDATE alumno SET matricula = '" + matricula + "', nombre = '" + nombre + "', genero='" + genero + "', fecha_nacimiento='" + fechaNacimiento + "', telefono='" + telefono + "' WHERE id_alumno=" + idAlumno;
        main.conectar.executeQuery(sqlQuery);
    }

    public String getGenderString(int gender) {
        switch (gender) {
            case 0 -> {
                return "Masculino";
            }

            case 1 -> {
                return "Femenino";
            }
        }
        return null;
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
        btnRegistrar.addActionListener(e -> {
            saveData();
            this.dispose();
            main.refreshTable();
        });
        placeHolderListeners();
    }

    private void placeHolderListeners() {

        tfNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfNombre.getText().equals("Ingrese el nombre del alumno")) {
                    tfNombre.setText("");
                    tfNombre.setForeground(Color.BLACK);
                }
            }


            @Override
            public void focusLost(FocusEvent e) {
                if (tfNombre.getText().trim().equals("Ingrese el nombre del alumno") || tfNombre.getText().trim().isEmpty()) {
                    tfNombre.setText("Ingrese el nombre del alumno");
                    tfNombre.setForeground(placeHolderColor);
                }
            }
        });

        tfMatricula.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfMatricula.getText().equals("Ingrese la matrícula del alumno")) {
                    tfMatricula.setText("");
                    tfMatricula.setForeground(Color.BLACK);
                }
            }


            @Override
            public void focusLost(FocusEvent e) {
                if (tfMatricula.getText().trim().equals("Ingrese la matrícula del alumno") || tfMatricula.getText().trim().isEmpty()) {
                    tfMatricula.setText("Ingrese la matrícula del alumno");
                    tfMatricula.setForeground(placeHolderColor);
                }
            }
        });

        tfFechaNacimiento.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfFechaNacimiento.getText().equals("Ingrese la fecha de nacimiento del alumno")) {
                    tfFechaNacimiento.setText("");
                    tfFechaNacimiento.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfFechaNacimiento.getText().trim().equals("Ingrese la fecha de nacimiento del alumno") || tfFechaNacimiento.getText().trim().isEmpty()) {
                    tfFechaNacimiento.setText("Ingrese la fecha de nacimiento del alumno");
                    tfFechaNacimiento.setForeground(placeHolderColor);
                }
            }
        });

        tfTelefono.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfTelefono.getText().equals("Ingrese el teléfono del alumno")) {
                    tfTelefono.setText("");
                    tfTelefono.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfTelefono.getText().trim().equals("Ingrese el teléfono del alumno") || tfTelefono.getText().trim().isEmpty()) {
                    tfTelefono.setText("Ingrese el teléfono del alumno");
                    tfTelefono.setForeground(placeHolderColor);
                }
            }
        });

        cbGenero.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (cbGenero.getSelectedIndex() > 0) {
                    cbGenero.setForeground(Color.BLACK);
                } else {
                    cbGenero.setForeground(placeHolderColor);
                }
            }
        });

    }

}
