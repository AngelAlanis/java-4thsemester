package com.misael.escuelabd;

import javax.swing.*;
import java.awt.Color;
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
    private JLabel            labelDireccion;
    private JTextField        tfDireccion;
    private Color             placeHolderColor;

    String sqlQuery, nombreAlumno, nombreTutor, matriculaAlumno, fechaNacimientoAlumno, telefonoAlumno, telefonoTutor, rfcTutor, direccion, cantidadRecibida, nivel;
    int generoAlumno, nivelIndex, grado;
    int currentPanel = 0;
    int montoAPagar, montoPagado;
    MainGUI main;

    NewAlumnoGUI(MainGUI main) {
        this.main = main;
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
        labelExtracurricular.setVisible(false);
        cbExtraCurricular.setVisible(false);
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
        tfDireccion.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
    }

    public void initActionListeners() {

        placeHolderListeners();

        btnRegistrar.addActionListener(e -> {
            try {
                matriculaAlumno       = Utilities.validate(tfMatricula.getText());
                nombreAlumno          = Utilities.validate(tfNombreAlumno.getText());
                generoAlumno          = Utilities.validate(cbGenero.getSelectedIndex());
                fechaNacimientoAlumno = Utilities.validate(tfFechaNacimiento.getText());
                telefonoAlumno        = Utilities.validate(tfTelefonoAlumno.getText());
                nombreTutor           = Utilities.validate(tfNombreTutor.getText());
                rfcTutor              = Utilities.validate(tfRFCTutor.getText());
                telefonoTutor         = Utilities.validate(tfTelefonoTutor.getText());
                nivelIndex            = Utilities.validate(cbGrado.getSelectedIndex());
                direccion             = Utilities.validate(tfDireccion.getText());
                nivel                 = getNivelFromIndex(nivelIndex);
                grado                 = Utilities.validate(cbYear.getSelectedIndex());
                montoPagado           = Integer.parseInt(Utilities.validate(tfCantidadRecibida.getText()));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Verifique los datos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            sqlQuery = "INSERT INTO alumno (matricula, nombre, genero, fecha_nacimiento, telefono)"
                    + "\nVALUES ('" + matriculaAlumno + "','" + nombreAlumno + "','" + generoAlumno + "','" + fechaNacimientoAlumno + "','" + telefonoAlumno + "');"
                    + "\nSET @AlumnoID = LAST_INSERT_ID();"
                    + "\nINSERT INTO tutor(nombre, rfc, telefono)"
                    + "\nVALUES ('" + nombreTutor + "','" + rfcTutor + "','" + telefonoTutor + "');"
                    + "\nSET @TutorID = LAST_INSERT_ID();"
                    + "\nSELECT id_grado INTO  @GradoID FROM grado WHERE grado= '" + grado + "' AND nivel ='" + nivel + "';"
                    + "\nINSERT INTO inscripcion (id_alumno, id_grado, monto, pagado) VALUES (@AlumnoID, @GradoID" + ",'" + montoAPagar + "','" + montoPagado + "');"
                    + "\nINSERT INTO alumno_tutor (id_tutor, id_alumno, direccion) VALUES (@TutorID, @AlumnoID,'" + direccion + "');";

            System.out.println(sqlQuery);

            main.conectar.executeQuery(sqlQuery);
            this.dispose();
            main.refreshTable();

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

        cbGrado.addActionListener(e -> {
            if (cbGrado.getSelectedIndex() == 3) {
                labelExtracurricular.setVisible(true);
                cbExtraCurricular.setVisible(true);
            } else {
                labelExtracurricular.setVisible(false);
                cbExtraCurricular.setVisible(false);
            }
        });

        cbYear.addActionListener(e -> {
            if (cbGrado.getSelectedIndex() > 0 || cbYear.getSelectedIndex() > 0) {
                montoAPagar = calculateInscriptionCost();
                labelPrecioInscripcion.setText("$" + montoAPagar);
            }
        });

    }

    private int calculateInscriptionCost() {
        switch (cbGrado.getSelectedIndex()) {
            case 1 -> {
                return 2000;
            }
            case 2 -> {
                return 2500;
            }
            case 3 -> {
                return 3000;
            }

            default -> {
                return -1;
            }
        }
    }

    private void placeHolderListeners() {
        Utilities.setPlacerHolder(tfNombreAlumno, "Ingrese el nombre del alumno");
        Utilities.setPlacerHolder(tfMatricula, "Ingrese la matrícula del alumno");
        Utilities.setPlacerHolder(tfFechaNacimiento, "Ingrese la fecha de nacimiento del alumno");
        Utilities.setPlacerHolder(tfTelefonoAlumno, "Ingrese el teléfono del alumno");
        Utilities.setPlacerHolder(tfNombreTutor, "Ingrese el nombre del tutor");
        Utilities.setPlacerHolder(tfTelefonoAlumno, "Ingrese el teléfono del alumno");
        Utilities.setPlacerHolder(tfRFCTutor, "Ingrese el RFC del tutor");
        Utilities.setPlacerHolder(tfTelefonoTutor, "Ingrese el teléfono del tutor");
        Utilities.setPlacerHolder(tfDireccion, "Ingrese la dirección del tutor");
        Utilities.setPlacerHolder(tfCantidadRecibida, "Ingrese la cantidad recibida");
        Utilities.setPlaceHolder(cbGenero, 0);
        Utilities.setPlaceHolder(cbExtraCurricular, 0);
        Utilities.setPlaceHolder(cbYear, 0);
        Utilities.setPlaceHolder(cbGrado, 0);
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

    private String getNivelFromIndex(int index) {
        switch (index) {
            case 1 -> {
                return "Primaria";
            }

            case 2 -> {
                return "Secundaria";
            }

            case 3 -> {
                return "Bachillerato";
            }

            default -> throw new NullPointerException();
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
