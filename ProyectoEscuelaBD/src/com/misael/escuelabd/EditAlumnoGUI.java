package com.misael.escuelabd;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
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

    public EditAlumnoGUI(MainGUI main, int idAlumno) {
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
        Utilities.setPlacerHolder(tfNombre, "Ingrese el nombre del alumno");
        Utilities.setPlacerHolder(tfMatricula, "Ingrese la matrícula del alumno");
        Utilities.setPlacerHolder(tfFechaNacimiento, "Ingrese la fecha de nacimiento del alumno");
        Utilities.setPlacerHolder(tfTelefono, "Ingrese el teléfono del alumno");
        Utilities.setPlaceHolder(cbGenero, 0);
    }

}
