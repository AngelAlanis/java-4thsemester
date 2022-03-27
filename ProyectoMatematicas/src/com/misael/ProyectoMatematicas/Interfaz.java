package com.misael.ProyectoMatematicas;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import com.misael.Mathematics.BiseccionModel;
import com.misael.Mathematics.Mathematics;
import com.misael.Mathematics.ReglaFalsaModel;
import com.misael.Mathematics.SecanteModel;

public class Interfaz extends JFrame {
    private JPanel      panelPrincipal;
    private JPanel      panelEncabezado;
    private JPanel      panelInferior;
    private JPanel      panelTabla;
    private JPanel      panelInputs;
    private JTextField  tfFx;
    private JComboBox   cbMetodos;
    private JButton     btnResolver;
    private JButton     btnSidenav;
    private JLabel      labelLogo;
    private JLabel      labelfx;
    private JLabel      labelMetodo;
    private JLabel      labelSolucionInfo;
    private JLabel      labelErrorInfo;
    private JPanel      panelBiseccion;
    private JPanel      panelReglaFalsa;
    private JPanel      panelSecante;
    private JScrollPane scrollBiseccion;
    private JTable      tableBiseccion;
    private JScrollPane scrollReglaFalsa;
    private JTable      tableReglaFalsa;
    private JScrollPane scrollSecante;
    private JTable      tableSecante;
    private JScrollPane sPReglaFalsa;

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */

    public Interfaz() {
        super("Matemáticas");
        $$$setupUI$$$();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(panelPrincipal);
        configurarComponentes();
        initActionListeners();
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void configurarComponentes() {
        tableBiseccion.setModel(new BiseccionModel());
        tableReglaFalsa.setModel(new ReglaFalsaModel());
        tableSecante.setModel(new SecanteModel());
    }

    public void initActionListeners() {

        cbMetodos.addActionListener(e -> {
            switch (cbMetodos.getSelectedIndex()) {
                case 0 -> {
                    panelTabla.removeAll();
                    panelTabla.add(panelBiseccion);
                    panelTabla.repaint();
                    panelTabla.revalidate();
                }
                case 1 -> {
                    panelTabla.removeAll();
                    panelTabla.add(panelReglaFalsa);
                    panelTabla.repaint();
                    panelTabla.revalidate();
                }
                case 2 -> {
                    panelTabla.removeAll();
                    panelTabla.add(panelSecante);
                    panelTabla.repaint();
                    panelTabla.revalidate();
                }
            }
        });

        btnResolver.addActionListener(e -> {
            String input = tfFx.getText();
            switch (cbMetodos.getSelectedIndex()) {
                case 0 -> {
                    Mathematics biseccion = new Mathematics();
                    double      result    = biseccion.metodoBiseccion(input, 0.0001);
                    tableBiseccion.setModel(new BiseccionModel(biseccion.getTablaBiseccion()));
                    labelSolucionInfo.setText("Solucion = " + result);
                }
                case 1 -> {
                    Mathematics reglaFalsa = new Mathematics();
                    double      result     = reglaFalsa.metodoReglaFalsa(input, 0.0001);
                    tableReglaFalsa.setModel(new ReglaFalsaModel(reglaFalsa.getTablaReglaFalsa()));
                    labelSolucionInfo.setText("Solucion = " + result);
                }
                case 2 -> {
                    Mathematics secante = new Mathematics();
                    double      result  = secante.metodoSecante(input, 0.0001);
                    tableSecante.setModel(new SecanteModel(secante.getTablaSecante()));
                    labelSolucionInfo.setText("Solucion = " + result);
                }
            }

        });
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelPrincipal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        panelEncabezado = new JPanel();
        panelEncabezado.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelPrincipal.add(panelEncabezado, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelEncabezado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final Spacer spacer1 = new Spacer();
        panelEncabezado.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        labelLogo = new JLabel();
        labelLogo.setText("Label");
        panelEncabezado.add(labelLogo, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnSidenav = new JButton();
        btnSidenav.setText("Button");
        panelEncabezado.add(btnSidenav, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelPrincipal.add(panelInferior, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelTabla = new JPanel();
        panelTabla.setLayout(new CardLayout(0, 0));
        panelInferior.add(panelTabla, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelBiseccion = new JPanel();
        panelBiseccion.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTabla.add(panelBiseccion, "Card1");
        scrollBiseccion = new JScrollPane();
        panelBiseccion.add(scrollBiseccion, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableBiseccion = new JTable();
        scrollBiseccion.setViewportView(tableBiseccion);
        panelReglaFalsa = new JPanel();
        panelReglaFalsa.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTabla.add(panelReglaFalsa, "Card2");
        scrollReglaFalsa = new JScrollPane();
        panelReglaFalsa.add(scrollReglaFalsa, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableReglaFalsa = new JTable();
        scrollReglaFalsa.setViewportView(tableReglaFalsa);
        panelSecante = new JPanel();
        panelSecante.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTabla.add(panelSecante, "Card3");
        scrollSecante = new JScrollPane();
        panelSecante.add(scrollSecante, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableSecante = new JTable();
        scrollSecante.setViewportView(tableSecante);
        panelInputs = new JPanel();
        panelInputs.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelInferior.add(panelInputs, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelfx = new JLabel();
        labelfx.setText("f(x) = ");
        panelInputs.add(labelfx, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfFx = new JTextField();
        panelInputs.add(tfFx, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelMetodo = new JLabel();
        labelMetodo.setText("Método");
        panelInputs.add(labelMetodo, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbMetodos = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Bisección");
        defaultComboBoxModel1.addElement("Regla Falsa");
        defaultComboBoxModel1.addElement("Secante");
        cbMetodos.setModel(defaultComboBoxModel1);
        panelInputs.add(cbMetodos, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnResolver = new JButton();
        btnResolver.setText("Resolver");
        panelInputs.add(btnResolver, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelSolucionInfo = new JLabel();
        labelSolucionInfo.setText("Solución =");
        panelInputs.add(labelSolucionInfo, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelErrorInfo = new JLabel();
        labelErrorInfo.setText("Error = ");
        panelInputs.add(labelErrorInfo, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panelPrincipal.add(separator1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelPrincipal;
    }

}
