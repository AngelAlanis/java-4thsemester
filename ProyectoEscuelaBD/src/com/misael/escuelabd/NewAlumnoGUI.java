package com.misael.escuelabd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
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
    private JLabel            labelMatricula;
    private JTextField        tfMatricula;
    private JLabel            lbCantidadRecibida;
    private JTextField        tfCantidadRecibida;
    private JLabel            labelCantidadAPagar;
    private JLabel            labelPrecioInscripcion;
    private Color             placeHolderColor;

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
        placeHolderColor = new Color(177, 179, 174);
    }


    private void setBorderToComponents() {
        tfNombreAlumno.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfMatricula.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfFechaNacimiento.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfNombreTutor.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfRFCTutor.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfTelefonoAlumno.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfTelefonoTutor.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        cbGenero.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        cbGrado.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        cbYear.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        cbExtraCurricular.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        tfCantidadRecibida.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
    }

    public void initActionListeners() {

        placeHolderListeners();

        btnRegistrar.addActionListener(e -> {
            String matriculaAlumno       = tfMatricula.getText();
            String nombreAlumno          = tfNombreAlumno.getText();
            int    generoAlumno          = cbGenero.getSelectedIndex();
            String fechaNacimientoAlumno = tfFechaNacimiento.getText();
            String telefonoAlumno        = tfTelefonoAlumno.getText();

            String nombreTutor   = tfNombreTutor.getText();
            String rfcTutor      = tfRFCTutor.getText();
            String telefonoTutor = tfTelefonoTutor.getText();

            int    grado            = cbGrado.getSelectedIndex();
            int    year             = cbYear.getSelectedIndex();
            String cantidadRecibida = tfCantidadRecibida.getText();

            String sql = "INSERT INTO alumno (matricula, nombre, genero, fecha_nacimiento, telefono) " +
                    "VALUES ('" + matriculaAlumno + "','" + nombreAlumno + "','" + generoAlumno + "','" + telefonoAlumno + "','" + telefonoAlumno + "')";

            MainGUI.conectar.guardarAlumno(sql);

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

    private void placeHolderListeners() {
        tfNombreAlumno.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfNombreAlumno.getText().equals("Ingrese el nombre del alumno")) {
                    tfNombreAlumno.setText("");
                    tfNombreAlumno.setForeground(Color.BLACK);
                }
            }


            @Override
            public void focusLost(FocusEvent e) {
                if (tfNombreAlumno.getText().trim().equals("Ingrese el nombre del alumno") || tfNombreAlumno.getText().trim().isEmpty()) {
                    tfNombreAlumno.setText("Ingrese el nombre del alumno");
                    tfNombreAlumno.setForeground(placeHolderColor);
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

        tfTelefonoAlumno.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfTelefonoAlumno.getText().equals("Ingrese el teléfono del alumno")) {
                    tfTelefonoAlumno.setText("");
                    tfTelefonoAlumno.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfTelefonoAlumno.getText().trim().equals("Ingrese el teléfono del alumno") || tfTelefonoAlumno.getText().trim().isEmpty()) {
                    tfTelefonoAlumno.setText("Ingrese el teléfono del alumno");
                    tfTelefonoAlumno.setForeground(placeHolderColor);
                }
            }
        });

        tfNombreTutor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfNombreTutor.getText().equals("Ingrese el nombre del tutor")) {
                    tfNombreTutor.setText("");
                    tfNombreTutor.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfNombreTutor.getText().trim().equals("Ingrese el nombre del tutor") || tfNombreTutor.getText().trim().isEmpty()) {
                    tfNombreTutor.setText("Ingrese el nombre del tutor");
                    tfNombreTutor.setForeground(placeHolderColor);
                }
            }
        });

        tfRFCTutor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfRFCTutor.getText().equals("Ingrese el RFC del tutor")) {
                    tfRFCTutor.setText("");
                    tfRFCTutor.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfRFCTutor.getText().trim().equals("Ingrese el RFC del tutor") || tfRFCTutor.getText().trim().isEmpty()) {
                    tfRFCTutor.setText("Ingrese el RFC del tutor");
                    tfRFCTutor.setForeground(placeHolderColor);
                }
            }
        });

        tfTelefonoTutor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfTelefonoTutor.getText().equals("Ingrese el teléfono del tutor")) {
                    tfTelefonoTutor.setText("");
                    tfTelefonoTutor.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfTelefonoTutor.getText().trim().equals("Ingrese el teléfono del tutor") || tfTelefonoTutor.getText().trim().isEmpty()) {
                    tfTelefonoTutor.setText("Ingrese el teléfono del tutor");
                    tfTelefonoTutor.setForeground(placeHolderColor);
                }
            }
        });

        tfCantidadRecibida.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfCantidadRecibida.getText().equals("Ingrese la cantidad recibida")) {
                    tfCantidadRecibida.setText("");
                    tfCantidadRecibida.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfCantidadRecibida.getText().trim().equals("Ingrese la cantidad recibida") || tfCantidadRecibida.getText().trim().isEmpty()) {
                    tfCantidadRecibida.setText("Ingrese la cantidad recibida");
                    tfCantidadRecibida.setForeground(placeHolderColor);
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

        cbExtraCurricular.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (cbExtraCurricular.getSelectedIndex() > 0) {
                    cbExtraCurricular.setForeground(Color.BLACK);
                } else {
                    cbExtraCurricular.setForeground(placeHolderColor);
                }
            }
        });

        cbYear.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (cbYear.getSelectedIndex() > 0) {
                    cbYear.setForeground(Color.BLACK);
                } else {
                    cbYear.setForeground(placeHolderColor);
                }
            }
        });

        cbGrado.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (cbGrado.getSelectedIndex() > 0) {
                    cbGrado.setForeground(Color.BLACK);
                } else {
                    cbGrado.setForeground(placeHolderColor);
                }
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
