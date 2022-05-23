package com.misael.escuelabd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewAlumnoGUI extends JFrame {

    private JPanel            panelCards;
    private JPanel            panelInfoAlumno;
    private JPanel            panelInfoTutor;
    private JPanel            panelInfoGrado;
    private JLabel            labelTituloAlumno;
    private JTextField        tfTelefonoAlumno;
    private JTextField        tfNombreAlumno;
    private JComboBox<String> cbGenero;
    private JLabel            labelNombre;
    private JLabel            labelGenero;
    private JLabel            labelTelefono;
    private JPanel            panelMain;
    private JLabel            labelAnterior;
    private JLabel            labelSiguiente;
    private JTextField        tfNombreTutor;
    private JTextField        tfRFCTutor;
    private JTextField        tfTelefonoTutor;
    private JLabel            labelNombreTutor;
    private JLabel            labelRFC;
    private JLabel            labelTelefonoTutor;
    private JComboBox<String> cbGrado;
    private JComboBox<String> cbYear;
    private JComboBox<String> cbExtraCurricular;
    private JLabel            labelGrado;
    private JLabel            lbYear;
    private JLabel            labelExtracurricular;
    private JLabel            labelTituloTutor;
    private JLabel            labelTituloGrado;
    private JButton           btnRegistrar;
    private JTextField        tfFechaNacimiento;
    private JLabel            lbFechaNacimiento;
    private JButton           btnAnterior;
    private JButton           btnSiguiente;

    int currentPanel = 0;

    NewAlumnoGUI() {
        this.setSize(500, 700);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panelMain);
        this.setLocationRelativeTo(null);
        initComponents();
        initActionListeners();
        this.setVisible(true);
    }

    public void initComponents() {
        setBorderToComponents();
    }


    private void setBorderToComponents() {
        tfNombreAlumno.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfFechaNacimiento.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfNombreTutor.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfRFCTutor.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfTelefonoAlumno.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfTelefonoTutor.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        cbGenero.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        cbGrado.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        cbYear.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        cbExtraCurricular.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
    }

    public void initActionListeners() {

        btnRegistrar.addActionListener(e -> {
            String nombreAlumno    = tfNombreAlumno.getText();
            int    genero          = cbGenero.getSelectedIndex();
            String fechaNacimiento = tfFechaNacimiento.getText();
            String telefonoAlumno  = tfTelefonoAlumno.getText();

            String nombreTutor   = tfNombreTutor.getText();
            String rfcTutor      = tfRFCTutor.getText();
            String telefonoTutor = tfTelefonoTutor.getText();

            int grado = cbGrado.getSelectedIndex();
            int year  = cbYear.getSelectedIndex();

            String sql = "INSERT INTO `alumno` (`nombre`, `genero`, `fecha_nacimiento`, `telefono`) " +
                    "VALUES ('" + nombreAlumno + "','" + genero + "','" + fechaNacimiento + "','" + telefonoAlumno + "')";

        });

        btnSiguiente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                incrementCurrentPanel();
                loadCurrentPanel();
            }
        });

        btnAnterior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                decreaseCurrentPanel();
                loadCurrentPanel();
            }
        });
    }

    private void incrementCurrentPanel() {
        if (currentPanel < 2) {
            currentPanel++;
        }
    }

    private void decreaseCurrentPanel() {
        if (currentPanel > 0) {
            currentPanel--;
        }
    }

    private void loadCurrentPanel() {
        switch (currentPanel) {
            case 0 -> cargarPanelAlumno();

            case 1 -> cargarPanelTutor();

            case 2 -> cargarPanelGrado();

        }
    }

    private void cargarPanelTutor() {
        panelCards.removeAll();
        panelCards.add(panelInfoTutor);
        panelCards.revalidate();
        panelCards.repaint();
        btnAnterior.setEnabled(true);
        btnSiguiente.setEnabled(true);

    }

    private void cargarPanelGrado() {
        panelCards.removeAll();
        panelCards.add(panelInfoGrado);
        panelCards.revalidate();
        panelCards.repaint();
        btnAnterior.setEnabled(true);
        btnSiguiente.setEnabled(false);
    }

    private void cargarPanelAlumno() {
        panelCards.removeAll();
        panelCards.add(panelInfoAlumno);
        panelCards.revalidate();
        panelCards.repaint();
        btnAnterior.setEnabled(false);
        btnSiguiente.setEnabled(true);
    }

}
