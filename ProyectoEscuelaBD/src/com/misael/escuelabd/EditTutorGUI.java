package com.misael.escuelabd;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

        tfNombre.setText(data.get(1).toString());
        tfRFC.setText(data.get(2).toString());
        tfTelefono.setText(data.get(3).toString());
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
        tfNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfNombre.getText().equals("Ingrese el nombre del tutor")) {
                    tfNombre.setText("");
                    tfNombre.setForeground(Color.BLACK);
                }
            }


            @Override
            public void focusLost(FocusEvent e) {
                if (tfNombre.getText().trim().equals("Ingrese el nombre del tutor") || tfNombre.getText().trim().isEmpty()) {
                    tfNombre.setText("Ingrese el nombre del tutor");
                    tfNombre.setForeground(placeHolderColor);
                }
            }
        });

        tfRFC.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfRFC.getText().equals("Ingrese el RFC del tutor")) {
                    tfRFC.setText("");
                    tfRFC.setForeground(Color.BLACK);
                }
            }


            @Override
            public void focusLost(FocusEvent e) {
                if (tfRFC.getText().trim().equals("Ingrese el RFC del tutor") || tfRFC.getText().trim().isEmpty()) {
                    tfRFC.setText("Ingrese el RFC del tutor");
                    tfRFC.setForeground(placeHolderColor);
                }
            }
        });

        tfTelefono.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfTelefono.getText().equals("Ingrese el teléfono del tutor")) {
                    tfTelefono.setText("");
                    tfTelefono.setForeground(Color.BLACK);
                }
            }


            @Override
            public void focusLost(FocusEvent e) {
                if (tfTelefono.getText().trim().equals("Ingrese el teléfono del tutor") || tfTelefono.getText().trim().isEmpty()) {
                    tfTelefono.setText("Ingrese el teléfono del tutor");
                    tfTelefono.setForeground(placeHolderColor);
                }
            }
        });
    }

    private void saveData() {
        nombre   = tfNombre.getText();
        rfc      = tfRFC.getText();
        telefono = tfTelefono.getText();

        sqlQuery = "UPDATE tutor SET nombre='" + nombre + "',rfc='" + rfc + "',telefono='" + telefono + "' WHERE id_tutor ='" + idTutor + "'";

        main.conectar.executeQuery(sqlQuery);
    }

    public void setBorderToComponents() {
        tfNombre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
        tfTelefono.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
        tfRFC.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
    }

}
