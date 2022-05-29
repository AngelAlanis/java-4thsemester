package com.misael.escuelabd;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FilterGUI extends JFrame {
    private JCheckBox         activoCheckBox;
    private JCheckBox         inactivoCheckBox;
    private JPanel            panelMain;
    private JCheckBox         masculinoCheckBox;
    private JCheckBox         femeninoCheckBox;
    private JLabel            estadoDelAlumnoLabel;
    private JLabel            nivelLabel;
    private JCheckBox         primariaCheckBox;
    private JCheckBox         secundariaCheckBox;
    private JCheckBox         bachilleratoCheckBox;
    private JLabel            gradoLabel;
    private JLabel            géneroLabel;
    private JButton           cancelarButton;
    private JButton           aplicarButton;
    private JLabel            filtrarLabel;
    private JComboBox<String> gradosComboBox;
    public  String            fullQuery   = "";
    private String            activeQuery = "";
    private String            genderQuery = "";
    private String            levelQuery  = "";

    public FilterGUI() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panelMain);
        this.setUndecorated(true);
        initActionListeners();
        initComponents();
        this.pack();
    }

    public void initComponents() {
        aplicarButton.addActionListener(e -> {

            if (activoCheckBox.isSelected() && !inactivoCheckBox.isSelected()) {
                activeQuery = " AND alumno.activo = 1";
            } else if (inactivoCheckBox.isSelected() && !activoCheckBox.isSelected()) {
                activeQuery = " AND alumno.activo = 0";
            } else if (activoCheckBox.isSelected() && inactivoCheckBox.isSelected()) {
                activeQuery = "";
            }

            if (masculinoCheckBox.isSelected() && !femeninoCheckBox.isSelected()) {
                genderQuery = " AND alumno.genero = 0";
            } else if (femeninoCheckBox.isSelected() && !masculinoCheckBox.isSelected()) {
                genderQuery = " AND alumno.genero = 1";
            } else if (masculinoCheckBox.isSelected() && femeninoCheckBox.isSelected()) {
                genderQuery = "";
            }

            if (primariaCheckBox.isSelected() && !secundariaCheckBox.isSelected() && !bachilleratoCheckBox.isSelected()) {
                levelQuery = " AND grado.nivel = 'Primaria'";
            } else if (secundariaCheckBox.isSelected() && !primariaCheckBox.isSelected() && !bachilleratoCheckBox.isSelected()) {
                levelQuery = " AND grado.nivel = 'Secundaria'";
            } else if (bachilleratoCheckBox.isSelected() && !primariaCheckBox.isSelected() && !secundariaCheckBox.isSelected()) {
                levelQuery = " AND grado.nivel = 'Bachillerato'";
            } else if (primariaCheckBox.isSelected() && secundariaCheckBox.isSelected() && bachilleratoCheckBox.isSelected()) {
                levelQuery = "";
            }

            if (gradosComboBox.getSelectedIndex() > 0) {
                levelQuery += "AND grado.grado = '" + gradosComboBox.getSelectedIndex() + "'";
            } else {
                levelQuery += "";
            }

            fullQuery = activeQuery + genderQuery + levelQuery;

            System.out.println(fullQuery);
        });
    }

    public void initActionListeners() {
        cancelarButton.addActionListener(e -> this.setVisible(false));
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        var filter = new FilterGUI();
    }

}
