package com.misael.escuelabd;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.util.ArrayList;

public class EditTutorGUI extends JFrame {
    private JButton    btnCancelar;
    private JButton    btnGuardar;
    private JLabel     labelDireccion;
    private JLabel     labelNombre;
    private JLabel     labelRFC;
    private JLabel     labelTelefono;
    private JLabel     labelTitulo;
    private JPanel     panelMain;
    private JTextField tfDireccion;
    private JTextField tfNombre;
    private JTextField tfRFC;
    private JTextField tfTelefono;

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
        Utilities.setPlacerHolder(tfDireccion, "Ingrese la dirección del tutor");
        Utilities.setPlacerHolder(tfNombre, "Ingrese el nombre del tutor");
        Utilities.setPlacerHolder(tfRFC, "Ingrese el RFC del tutor");
        Utilities.setPlacerHolder(tfTelefono, "Ingrese el teléfono del tutor");
    }

    private void saveData() {
        try {
            getInput();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Verifique los datos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        sqlQuery = "UPDATE tutor SET nombre='" + nombre + "',rfc='" + rfc + "',telefono='" + telefono + "' WHERE id_tutor ='" + idTutor + "'";

        main.conectar.executeQuery(sqlQuery);
    }

    private void getInput() {
        nombre   = Utilities.validate(tfNombre.getText());
        rfc      = Utilities.validate(tfRFC.getText());
        telefono = Utilities.validate(tfTelefono.getText());
    }

    public void setBorderToComponents() {
        tfNombre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
        tfRFC.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
        tfTelefono.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
    }

}
