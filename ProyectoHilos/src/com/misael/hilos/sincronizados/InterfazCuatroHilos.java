package com.misael.hilos.sincronizados;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.Locale;

public class InterfazCuatroHilos extends JFrame {
    private JPanel        panelMain;
    public  JList<String> listaHilo1;
    public  JList<String> listaHilo2;
    public  JList<String> listaHilo3;
    public  JList<String> listaHilo4;
    private JScrollPane   spHilo1;
    private JScrollPane   spLista2;
    private JScrollPane   spLista3;
    private JScrollPane   spLista4;
    private JLabel        labelHilo1;
    private JLabel        labelHilo2;
    private JLabel        labelHilo3;
    private JLabel        labelHilo4;
    private JButton       btnCorrer;
    private JButton       btnLimpiar;

    public InterfazCuatroHilos() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1024, 576);
        this.setContentPane(panelMain);
        initComponentes();
        initActionListeners();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void startThread() {
        HilosSincronizados hilo1 = new HilosSincronizados("Hilo 1", this);
        HilosSincronizados hilo2 = new HilosSincronizados("Hilo 2", this);
        HilosSincronizados hilo3 = new HilosSincronizados("Hilo 3", this);
        HilosSincronizados hilo4 = new HilosSincronizados("Hilo 4", this);

        try {
            hilo1.hilo.join();
            hilo2.hilo.join();
            hilo3.hilo.join();
            hilo4.hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void initComponentes() {

    }

    public void initActionListeners() {
        btnCorrer.addActionListener(e -> startThread());
        btnLimpiar.addActionListener(e -> {
            listaHilo1.setModel(new DefaultListModel<>());
            listaHilo2.setModel(new DefaultListModel<>());
            listaHilo3.setModel(new DefaultListModel<>());
            listaHilo4.setModel(new DefaultListModel<>());
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        spHilo1 = new JScrollPane();
        panelMain.add(spHilo1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        spHilo1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-15236482)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        listaHilo1 = new JList();
        Font listaHilo1Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 28, listaHilo1.getFont());
        if (listaHilo1Font != null) listaHilo1.setFont(listaHilo1Font);
        spHilo1.setViewportView(listaHilo1);
        spLista2 = new JScrollPane();
        panelMain.add(spLista2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        spLista2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-15236482)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        listaHilo2 = new JList();
        Font listaHilo2Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 28, listaHilo2.getFont());
        if (listaHilo2Font != null) listaHilo2.setFont(listaHilo2Font);
        spLista2.setViewportView(listaHilo2);
        spLista3 = new JScrollPane();
        panelMain.add(spLista3, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        spLista3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-15236482)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        listaHilo3 = new JList();
        Font listaHilo3Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 28, listaHilo3.getFont());
        if (listaHilo3Font != null) listaHilo3.setFont(listaHilo3Font);
        spLista3.setViewportView(listaHilo3);
        spLista4 = new JScrollPane();
        panelMain.add(spLista4, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        spLista4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-15236482)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        listaHilo4 = new JList();
        Font listaHilo4Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 28, listaHilo4.getFont());
        if (listaHilo4Font != null) listaHilo4.setFont(listaHilo4Font);
        spLista4.setViewportView(listaHilo4);
        labelHilo1 = new JLabel();
        Font labelHilo1Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 26, labelHilo1.getFont());
        if (labelHilo1Font != null) labelHilo1.setFont(labelHilo1Font);
        labelHilo1.setForeground(new Color(-16728149));
        labelHilo1.setText("Hilo 1");
        panelMain.add(labelHilo1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelHilo2 = new JLabel();
        Font labelHilo2Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 26, labelHilo2.getFont());
        if (labelHilo2Font != null) labelHilo2.setFont(labelHilo2Font);
        labelHilo2.setForeground(new Color(-16728149));
        labelHilo2.setText("Hilo 2");
        panelMain.add(labelHilo2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelHilo3 = new JLabel();
        Font labelHilo3Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 26, labelHilo3.getFont());
        if (labelHilo3Font != null) labelHilo3.setFont(labelHilo3Font);
        labelHilo3.setForeground(new Color(-16728149));
        labelHilo3.setText("Hilo 3");
        panelMain.add(labelHilo3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelHilo4 = new JLabel();
        Font labelHilo4Font = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 26, labelHilo4.getFont());
        if (labelHilo4Font != null) labelHilo4.setFont(labelHilo4Font);
        labelHilo4.setForeground(new Color(-16728149));
        labelHilo4.setText("Hilo 4");
        panelMain.add(labelHilo4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnCorrer = new JButton();
        btnCorrer.setText("Correr");
        panelMain.add(btnCorrer, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnLimpiar = new JButton();
        btnLimpiar.setText("Limpiar");
        panelMain.add(btnLimpiar, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return panelMain;
    }

}