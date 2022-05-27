package com.misael.escuelabd;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class EditTutorGUI extends JFrame {
    private JPanel     panelMain;
    private JTextField tfNombre;
    private JTextField tfRFC;
    private JButton    btnCancelar;
    private JButton    btnGuardar;
    private JTextField tfTelefono;
    private JLabel     labelTitulo;
    private JLabel     labelNombre;
    private JLabel     labelRFC;
    private JLabel     labelTelefono;
    private JLabel     labelDireccion;
    private JTextField tfDireccion;

    Color   placeHolderColor;
    MainGUI main;
    String  nombre, rfc, telefono, sqlQuery;
    int idTutor;

    public EditTutorGUI(MainGUI main, int idTutor) {
        this.main    = main;
        this.idTutor = idTutor;
        this.setSize(500, 700);
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        readData();
        initActionListeners();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initComponents() {
        setBorderToComponents();
        placeHolderColor = new Color(177, 179, 174);
    }

    public void readData() {
        ArrayList<Object> data = main.conectar.readData("SELECT nombre, rfc, telefono FROM tutor WHERE id_tutor = " + idTutor);

        tfNombre.setText(data.get(0).toString());
        tfRFC.setText(data.get(1).toString());
        tfTelefono.setText(data.get(2).toString());
    }

    public void initActionListeners() {

        btnGuardar.addActionListener(e -> {
            saveData();
            this.dispose();
            main.refreshTable();
        });

        btnCancelar.addActionListener(e -> this.dispose());

        placeHolderListeners();
    }

    private void placeHolderListeners() {
        Utilities.setPlacerHolder(tfNombre, "Ingrese el nombre del tutor");
        Utilities.setPlacerHolder(tfRFC, "Ingrese el RFC del tutor");
        Utilities.setPlacerHolder(tfTelefono, "Ingrese el teléfono del tutor");
        Utilities.setPlacerHolder(tfDireccion, "Ingrese la dirección del tutor");
    }

    private void saveData() {
        try {
            nombre   = Utilities.validate(tfNombre.getText());
            rfc      = Utilities.validate(tfRFC.getText());
            telefono = Utilities.validate(tfTelefono.getText());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Verifique los datos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        sqlQuery = "UPDATE tutor SET nombre='" + nombre + "',rfc='" + rfc + "',telefono='" + telefono + "' WHERE id_tutor ='" + idTutor + "'";

        main.conectar.executeQuery(sqlQuery);
    }

    public void setBorderToComponents() {
        tfNombre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
        tfTelefono.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
        tfRFC.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
    }

}
