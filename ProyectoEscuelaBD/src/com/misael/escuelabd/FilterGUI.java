package com.misael.escuelabd;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private String            sqlQuery;

    public FilterGUI(int x, int y) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panelMain);
        this.setUndecorated(true);
        this.setLocation(x, y);
        initActionListeners();
        initComponents();
        this.pack();
    }

    public void initComponents() {
        aplicarButton.addActionListener(e -> {


        });
    }

    public void initActionListeners() {
        cancelarButton.addActionListener(e -> this.setVisible(false));
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        var filter = new FilterGUI(100, 200);
    }

}
