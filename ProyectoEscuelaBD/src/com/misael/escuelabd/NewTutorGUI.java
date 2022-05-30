package com.misael.escuelabd;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;

public class NewTutorGUI extends JFrame {
    private JButton    btnCancelar;
    private JButton    btnGuardar;
    private JLabel     labelNombre;
    private JLabel     labelRFC;
    private JLabel     labelTelefono;
    private JLabel     labelTitulo;
    private JPanel     panelMain;
    private JTextField tfNombre;
    private JTextField tfRFC;
    private JTextField tfTelefono;

    Color   placeHolderColor;
    MainGUI main;
    String  nombre, rfc, telefono, sqlQuery;

    public NewTutorGUI(MainGUI main) {
        this.main = main;
        this.setSize(500, 700);
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        initActionListeners();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initComponents() {
        setBorderToComponents();
        placeHolderColor = new Color(177, 179, 174);
    }

    public void initActionListeners() {

        btnGuardar.addActionListener(e -> saveTutor());

        btnCancelar.addActionListener(e -> this.dispose());

        Utilities.setPlacerHolder(tfNombre, "Ingrese el nombre del tutor");
        Utilities.setPlacerHolder(tfRFC, "Ingrese el RFC del tutor");
        Utilities.setPlacerHolder(tfTelefono, "Ingrese el teléfono del tutor");
        Utilities.setPlacerHolder(tfNombre, "Ingrese el nombre del tutor");

    }

    private void saveTutor() {
        try {
            nombre   = Utilities.validate(tfNombre.getText());
            rfc      = Utilities.validate(tfRFC.getText());
            telefono = Utilities.validate(tfTelefono.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Verifique los datos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        sqlQuery = "INSERT INTO tutor (nombre, rfc, telefono) VALUES ('" + nombre + "','" + rfc + "','" + telefono + "')";
        main.conectar.executeQuery(sqlQuery);
        this.dispose();
        main.refreshTable();
    }

    public void setBorderToComponents() {
        tfNombre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
        tfRFC.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
        tfTelefono.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
    }

}
