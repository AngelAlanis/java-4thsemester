package com.misael.escuelabd;

import javax.swing.*;
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
    private JComboBox<String> tfGenero;
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

    int currentPanel = 0;

    NewAlumnoGUI() {
        this.setSize(500, 700);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panelMain);
        this.setLocationRelativeTo(null);
        initActionListeners();
        this.setVisible(true);
    }

    public void initActionListeners() {
        labelSiguiente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                incrementCurrentPanel();
                loadCurrentPanel();
            }
        });

        labelAnterior.addMouseListener(new MouseAdapter() {
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

            case 1 -> cargarPanelGrado();

            case 2 -> cargarPanelTutor();

        }
    }

    private void cargarPanelTutor() {
        panelCards.removeAll();
        panelCards.add(panelInfoTutor);
        panelCards.revalidate();
        panelCards.repaint();
    }

    private void cargarPanelGrado() {
        panelCards.removeAll();
        panelCards.add(panelInfoGrado);
        panelCards.revalidate();
        panelCards.repaint();
    }

    private void cargarPanelAlumno() {
        panelCards.removeAll();
        panelCards.add(panelInfoAlumno);
        panelCards.revalidate();
        panelCards.repaint();
    }

}
