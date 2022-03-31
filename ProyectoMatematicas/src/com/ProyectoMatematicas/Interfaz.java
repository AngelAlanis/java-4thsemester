package com.ProyectoMatematicas;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import com.misael.Mathematics.BiseccionModel;
import com.misael.Mathematics.Mathematics;
import com.misael.Mathematics.NewtonRaphsonModel;
import com.misael.Mathematics.ReglaFalsaModel;
import com.misael.Mathematics.SecanteModel;

public class Interfaz extends JFrame {
    private JPanel      panelPrincipal;
    private JPanel      panelEncabezado;
    private JPanel      panelInferior;
    private JPanel      panelTabla;
    private JPanel      panelInputs;
    private JTextField  tfFx;
    private JButton     btnResolver;
    private JLabel      labelLogo;
    private JLabel      labelfx;
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
    private JLabel      labelBiseccion;
    private JLabel      labelReglaFalsa;
    private JLabel      labelSecante;
    private JLabel      sideNavLabel;
    private JPanel      panelNewtonRaphson;
    private JScrollPane scrollNewtonRaphson;
    private JTable      tableNewtonRaphson;
    private JLabel      labelNewtonRaphson;
    private JButton     btnLimpiar;
    private JLabel      labelTeoremaBolzano;
    private JLabel      labelSalir;
    private ImageIcon   iconoBiseccionOFF;
    private ImageIcon   iconoReglaFalsaOFF;
    private ImageIcon   iconoSecanteOFF;
    private ImageIcon   iconoBiseccionON;
    private ImageIcon   iconoReglaFalsaON;
    private ImageIcon   iconoSecanteON;
    private ImageIcon   iconoNewtonON;
    private ImageIcon   iconoNewtonOFF;
    private ImageIcon   iconoLogo;
    private ImageIcon   iconoSalir;
    private int         selectedCardLayout = 0;
    private double      resultBiseccion;
    private double      resultReglaFalsa;
    private double      resultSecante;
    private double      resultNewton;
    private double      errorBiseccion;
    private double      errorReglaFalsa;
    private double      errorSecante;
    private double      errorNewton;

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
        crearIconos();
        configurarComponentes();
        initActionListeners();
        this.setSize(1280, 720);
        this.setUndecorated(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void configurarComponentes() {
        actualizarIconos();
        labelLogo.setIcon(iconoLogo);
        labelSalir.setIcon(iconoSalir);
        labelTeoremaBolzano.setVisible(false);
        labelTeoremaBolzano.setText("<html><p style=\"width:250px\">" + "La ecuación no cumple con el teorema de Bolzano" + "</p></html>");

        tableBiseccion.setModel(new BiseccionModel());
        tableReglaFalsa.setModel(new ReglaFalsaModel());
        tableSecante.setModel(new SecanteModel());
        tableNewtonRaphson.setModel(new NewtonRaphsonModel());

        DefaultTableCellRenderer rendererBiseccion     = (DefaultTableCellRenderer) tableBiseccion.getDefaultRenderer(Double.class);
        DefaultTableCellRenderer rendererReglaFalsa    = (DefaultTableCellRenderer) tableReglaFalsa.getDefaultRenderer(Double.class);
        DefaultTableCellRenderer rendererSecante       = (DefaultTableCellRenderer) tableSecante.getDefaultRenderer(Double.class);
        DefaultTableCellRenderer rendererNewtonRaphson = (DefaultTableCellRenderer) tableNewtonRaphson.getDefaultRenderer(Double.class);

        rendererBiseccion.setHorizontalAlignment(SwingConstants.CENTER);
        rendererReglaFalsa.setHorizontalAlignment(SwingConstants.CENTER);
        rendererSecante.setHorizontalAlignment(SwingConstants.CENTER);
        rendererNewtonRaphson.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void actualizarIconos() {
        labelBiseccion.setIcon(iconoBiseccionOFF);
        labelSecante.setIcon(iconoSecanteOFF);
        labelReglaFalsa.setIcon(iconoReglaFalsaOFF);
        labelNewtonRaphson.setIcon(iconoNewtonOFF);

        switch (selectedCardLayout) {
            case 0 -> labelBiseccion.setIcon(iconoBiseccionON);
            case 1 -> labelReglaFalsa.setIcon(iconoReglaFalsaON);
            case 2 -> labelSecante.setIcon(iconoSecanteON);
            case 3 -> labelNewtonRaphson.setIcon(iconoNewtonON);

        }
    }

    private void crearIconos() {
        try {
            sideNavLabel.setIcon(new ImageIcon(ImageIO.read(new File("ProyectoMatematicas/src/resources/icons/sidenav.png"))));

            BufferedImage imagenSalir = ImageIO.read(new File("ProyectoMatematicas/src/resources/icons/salir.png"));
            iconoSalir = new ImageIcon(imagenSalir);

            BufferedImage imagenBiseccionOFF = ImageIO.read(new File("ProyectoMatematicas/src/resources/icons/Biseccion_OFF.png"));
            iconoBiseccionOFF = new ImageIcon(imagenBiseccionOFF.getScaledInstance(70, 70, Image.SCALE_SMOOTH));

            BufferedImage imagenSecanteOFF = ImageIO.read(new File("ProyectoMatematicas/src/resources/icons/Secante_OFF.png"));
            iconoSecanteOFF = new ImageIcon(imagenSecanteOFF.getScaledInstance(70, 70, Image.SCALE_SMOOTH));

            BufferedImage imagenReglaFalsaOFF = ImageIO.read(new File("ProyectoMatematicas/src/resources/icons/ReglaFalsa_OFF.png"));
            iconoReglaFalsaOFF = new ImageIcon(imagenReglaFalsaOFF.getScaledInstance(70, 70, Image.SCALE_SMOOTH));

            BufferedImage imagenNewtonOFF = ImageIO.read(new File("ProyectoMatematicas/src/resources/icons/Newton_OFF.png"));
            iconoNewtonOFF = new ImageIcon(imagenNewtonOFF.getScaledInstance(70, 70, Image.SCALE_SMOOTH));

            BufferedImage imagenBiseccionON = ImageIO.read(new File("ProyectoMatematicas/src/resources/icons/Biseccion_ON.png"));
            iconoBiseccionON = new ImageIcon(imagenBiseccionON.getScaledInstance(70, 70, Image.SCALE_SMOOTH));

            BufferedImage imagenSecanteON = ImageIO.read(new File("ProyectoMatematicas/src/resources/icons/Secante_ON.png"));
            iconoSecanteON = new ImageIcon(imagenSecanteON.getScaledInstance(70, 70, Image.SCALE_SMOOTH));

            BufferedImage imagenReglaFalsaON = ImageIO.read(new File("ProyectoMatematicas/src/resources/icons/ReglaFalsa_ON.png"));
            iconoReglaFalsaON = new ImageIcon(imagenReglaFalsaON.getScaledInstance(70, 70, Image.SCALE_SMOOTH));

            BufferedImage imagenNewtonON = ImageIO.read(new File("ProyectoMatematicas/src/resources/icons/Newton_ON.png"));
            iconoNewtonON = new ImageIcon(imagenNewtonON.getScaledInstance(70, 70, Image.SCALE_SMOOTH));

            BufferedImage imagenLogo = ImageIO.read(new File("ProyectoMatematicas/src/resources/icons/logoMathematics.png"));
            iconoLogo = new ImageIcon(imagenLogo.getScaledInstance(237, 60, Image.SCALE_SMOOTH));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void limpiarInterfaz() {
        tableBiseccion.setModel(new BiseccionModel());
        tableReglaFalsa.setModel(new ReglaFalsaModel());
        tableSecante.setModel(new SecanteModel());
        tableNewtonRaphson.setModel(new NewtonRaphsonModel());

        resultBiseccion = 0;
        resultReglaFalsa = 0;
        resultSecante = 0;
        resultNewton = 0;

        errorBiseccion = 0;
        errorReglaFalsa = 0;
        errorSecante = 0;
        errorNewton = 0;

        tfFx.setText("");
        labelErrorInfo.setText("Error = 0.0");
        labelSolucionInfo.setText("Solución = 0.0");
        labelTeoremaBolzano.setVisible(false);
    }

    public void initActionListeners() {

        labelSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "", JOptionPane.YES_NO_OPTION) == 0) {
                    System.exit(0);
                }
            }
        });

        btnResolver.addActionListener(e -> {

            String input = tfFx.getText();

            if (!input.trim().isEmpty()) {
                Mathematics biseccion  = new Mathematics();
                Mathematics reglaFalsa = new Mathematics();
                Mathematics secante    = new Mathematics();
                Mathematics newton     = new Mathematics();

                resultBiseccion = Mathematics.metodoBiseccion(input, 0.0001);
                resultReglaFalsa = Mathematics.metodoReglaFalsa(input, 0.0001);
                resultSecante = Mathematics.metodoSecante(input, 0.0001);
                resultNewton = Mathematics.metodoNewtonRaphson(input, 0.0001);


                if (!Double.isNaN(resultBiseccion)) {
                    labelTeoremaBolzano.setVisible(false);
                    tableBiseccion.setModel(new BiseccionModel(biseccion.getTablaBiseccion()));
                    tableReglaFalsa.setModel(new ReglaFalsaModel(reglaFalsa.getTablaReglaFalsa()));
                    tableSecante.setModel(new SecanteModel(secante.getTablaSecante()));
                    tableNewtonRaphson.setModel(new NewtonRaphsonModel(newton.getTablaNewtonRaphson()));

                    errorBiseccion = (double) tableBiseccion.getValueAt(tableBiseccion.getRowCount() - 1, 4);
                    errorReglaFalsa = (double) tableBiseccion.getValueAt(tableBiseccion.getRowCount() - 1, 4);
                    errorSecante = (double) tableBiseccion.getValueAt(tableBiseccion.getRowCount() - 1, 4);
                    errorNewton = (double) tableBiseccion.getValueAt(tableBiseccion.getRowCount() - 1, 4);
                } else {
                    limpiarInterfaz();
                    labelTeoremaBolzano.setVisible(true);
                    tfFx.setText(input);
                }

                actualizarLabelsInfo();
            }
        });

        btnLimpiar.addActionListener(e -> limpiarInterfaz());

        labelBiseccion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                labelBiseccion.setIcon(iconoBiseccionON);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selectedCardLayout != 0) {
                    labelBiseccion.setIcon(iconoBiseccionOFF);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                cargarPanelBiseccion();
            }

        });

        labelReglaFalsa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                labelReglaFalsa.setIcon(iconoReglaFalsaON);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selectedCardLayout != 1) {
                    labelReglaFalsa.setIcon(iconoReglaFalsaOFF);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                cargarPanelReglaFalsa();
            }

        });

        labelSecante.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                labelSecante.setIcon(iconoSecanteON);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selectedCardLayout != 2) {
                    labelSecante.setIcon(iconoSecanteOFF);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                cargarPanelSecante();
            }

        });

        labelNewtonRaphson.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                labelNewtonRaphson.setIcon(iconoNewtonON);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selectedCardLayout != 3) {
                    labelNewtonRaphson.setIcon(iconoNewtonOFF);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                cargarPanelNewton();
            }

        });

    }

    private void actualizarLabelsInfo() {
        switch (selectedCardLayout) {
            case 0 -> {
                labelSolucionInfo.setText("Solución = " + resultBiseccion);
                labelErrorInfo.setText("Error = " + errorBiseccion);
            }
            case 1 -> {
                labelSolucionInfo.setText("Solución = " + resultReglaFalsa);
                labelErrorInfo.setText("Error = " + errorReglaFalsa);
            }
            case 2 -> {
                labelSolucionInfo.setText("Solución = " + resultSecante);
                labelErrorInfo.setText("Error = " + errorSecante);
            }
            case 3 -> {
                labelSolucionInfo.setText("Solución = " + resultNewton);
                labelErrorInfo.setText("Error = " + errorNewton);
            }
        }
    }

    private void cargarPanelBiseccion() {
        selectedCardLayout = 0;

        panelTabla.removeAll();
        panelTabla.add(panelBiseccion);
        panelTabla.repaint();
        panelTabla.revalidate();

        actualizarIconos();
        actualizarLabelsInfo();
    }

    private void cargarPanelReglaFalsa() {
        selectedCardLayout = 1;

        panelTabla.removeAll();
        panelTabla.add(panelReglaFalsa);
        panelTabla.repaint();
        panelTabla.revalidate();

        actualizarIconos();
        actualizarLabelsInfo();
    }

    private void cargarPanelSecante() {
        selectedCardLayout = 2;

        panelTabla.removeAll();
        panelTabla.add(panelSecante);
        panelTabla.repaint();
        panelTabla.revalidate();

        actualizarIconos();
        actualizarLabelsInfo();
    }

    private void cargarPanelNewton() {
        selectedCardLayout = 3;

        panelTabla.removeAll();
        panelTabla.add(panelNewtonRaphson);
        panelTabla.repaint();
        panelTabla.revalidate();

        actualizarIconos();
        actualizarLabelsInfo();
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
        panelPrincipal.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelEncabezado = new JPanel();
        panelEncabezado.setLayout(new GridLayoutManager(1, 12, new Insets(0, 0, 0, 0), -1, -1));
        panelEncabezado.setBackground(new Color(-5131346));
        panelPrincipal.add(panelEncabezado, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelEncabezado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final Spacer spacer1 = new Spacer();
        panelEncabezado.add(spacer1, new GridConstraints(0, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        labelBiseccion = new JLabel();
        labelBiseccion.setText("");
        panelEncabezado.add(labelBiseccion, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelReglaFalsa = new JLabel();
        labelReglaFalsa.setText("");
        panelEncabezado.add(labelReglaFalsa, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelSecante = new JLabel();
        labelSecante.setText("");
        panelEncabezado.add(labelSecante, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelEncabezado.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(50, -1), null, 0, false));
        sideNavLabel = new JLabel();
        sideNavLabel.setText("");
        panelEncabezado.add(sideNavLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelEncabezado.add(spacer3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(25, -1), null, 0, false));
        final Spacer spacer4 = new Spacer();
        panelEncabezado.add(spacer4, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(25, -1), null, 0, false));
        labelNewtonRaphson = new JLabel();
        labelNewtonRaphson.setText("");
        panelEncabezado.add(labelNewtonRaphson, new GridConstraints(0, 8, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panelEncabezado.add(spacer5, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(25, -1), null, 0, false));
        labelSalir = new JLabel();
        labelSalir.setText("");
        panelEncabezado.add(labelSalir, new GridConstraints(0, 11, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelLogo = new JLabel();
        labelLogo.setText("");
        panelEncabezado.add(labelLogo, new GridConstraints(0, 10, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelPrincipal.add(panelInferior, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelTabla = new JPanel();
        panelTabla.setLayout(new CardLayout(0, 0));
        panelInferior.add(panelTabla, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelBiseccion = new JPanel();
        panelBiseccion.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTabla.add(panelBiseccion, "Card1");
        scrollBiseccion = new JScrollPane();
        panelBiseccion.add(scrollBiseccion, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableBiseccion = new JTable();
        tableBiseccion.setPreferredScrollableViewportSize(new Dimension(700, 600));
        scrollBiseccion.setViewportView(tableBiseccion);
        panelReglaFalsa = new JPanel();
        panelReglaFalsa.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTabla.add(panelReglaFalsa, "Card2");
        scrollReglaFalsa = new JScrollPane();
        panelReglaFalsa.add(scrollReglaFalsa, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableReglaFalsa = new JTable();
        tableReglaFalsa.setPreferredScrollableViewportSize(new Dimension(700, 600));
        scrollReglaFalsa.setViewportView(tableReglaFalsa);
        panelSecante = new JPanel();
        panelSecante.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTabla.add(panelSecante, "Card3");
        scrollSecante = new JScrollPane();
        panelSecante.add(scrollSecante, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableSecante = new JTable();
        tableSecante.setPreferredScrollableViewportSize(new Dimension(700, 600));
        scrollSecante.setViewportView(tableSecante);
        panelNewtonRaphson = new JPanel();
        panelNewtonRaphson.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTabla.add(panelNewtonRaphson, "Card4");
        scrollNewtonRaphson = new JScrollPane();
        panelNewtonRaphson.add(scrollNewtonRaphson, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableNewtonRaphson = new JTable();
        tableNewtonRaphson.setPreferredScrollableViewportSize(new Dimension(700, 600));
        scrollNewtonRaphson.setViewportView(tableNewtonRaphson);
        panelInputs = new JPanel();
        panelInputs.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelInputs.setEnabled(true);
        panelInferior.add(panelInputs, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(400, -1), new Dimension(400, -1), 0, false));
        panelInputs.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        labelfx = new JLabel();
        Font labelfxFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 28, labelfx.getFont());
        if (labelfxFont != null) labelfx.setFont(labelfxFont);
        labelfx.setForeground(new Color(-15236482));
        labelfx.setText("f(x) = ");
        panelInputs.add(labelfx, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfFx = new JTextField();
        panelInputs.add(tfFx, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 35), null, 0, false));
        btnResolver = new JButton();
        btnResolver.setBackground(new Color(-15236482));
        Font btnResolverFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, btnResolver.getFont());
        if (btnResolverFont != null) btnResolver.setFont(btnResolverFont);
        btnResolver.setForeground(new Color(-1));
        btnResolver.setText("Resolver");
        panelInputs.add(btnResolver, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(130, 17), null, 0, false));
        labelSolucionInfo = new JLabel();
        Font labelSolucionInfoFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 24, labelSolucionInfo.getFont());
        if (labelSolucionInfoFont != null) labelSolucionInfo.setFont(labelSolucionInfoFont);
        labelSolucionInfo.setForeground(new Color(-15236482));
        labelSolucionInfo.setText("Solución = 0.0");
        labelSolucionInfo.setVerticalAlignment(0);
        labelSolucionInfo.setVerticalTextPosition(0);
        panelInputs.add(labelSolucionInfo, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelErrorInfo = new JLabel();
        Font labelErrorInfoFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 24, labelErrorInfo.getFont());
        if (labelErrorInfoFont != null) labelErrorInfo.setFont(labelErrorInfoFont);
        labelErrorInfo.setForeground(new Color(-15236482));
        labelErrorInfo.setText("Error = 0.0");
        labelErrorInfo.setVerticalAlignment(0);
        labelErrorInfo.setVerticalTextPosition(0);
        panelInputs.add(labelErrorInfo, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnLimpiar = new JButton();
        btnLimpiar.setBackground(new Color(-15236482));
        Font btnLimpiarFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, btnLimpiar.getFont());
        if (btnLimpiarFont != null) btnLimpiar.setFont(btnLimpiarFont);
        btnLimpiar.setForeground(new Color(-1));
        btnLimpiar.setText("Limpiar");
        panelInputs.add(btnLimpiar, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(130, 12), null, 0, false));
        labelTeoremaBolzano = new JLabel();
        labelTeoremaBolzano.setEnabled(true);
        Font labelTeoremaBolzanoFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 24, labelTeoremaBolzano.getFont());
        if (labelTeoremaBolzanoFont != null) labelTeoremaBolzano.setFont(labelTeoremaBolzanoFont);
        labelTeoremaBolzano.setForeground(new Color(-6810368));
        labelTeoremaBolzano.setHorizontalAlignment(10);
        labelTeoremaBolzano.setHorizontalTextPosition(10);
        labelTeoremaBolzano.setText("");
        panelInputs.add(labelTeoremaBolzano, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font    font             = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac            = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font    fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelPrincipal;
    }

}
