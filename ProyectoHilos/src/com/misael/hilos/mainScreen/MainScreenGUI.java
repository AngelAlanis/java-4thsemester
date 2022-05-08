package com.misael.hilos.mainScreen;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.misael.hilos.alarma.InterfazAlarma;
import com.misael.hilos.cronometro.Cronometro;
import com.misael.hilos.cronometro.InterfazCronometro;
import com.misael.hilos.empleados.LauncherEmpleados;
import com.misael.hilos.sincronizados.InterfazCuatroHilos;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Locale;
import java.util.Objects;

public class MainScreenGUI extends JFrame {

    private JPanel    panelMain;
    private JLabel    labelCronometro;
    private JLabel    labelTitle;
    private JLabel    labelAlarm;
    private JLabel    labelThreads;
    private JLabel    labelEmpleados;
    private ImageIcon iconAlarm;
    private ImageIcon iconThreads;
    private ImageIcon iconEmpleados;
    private ImageIcon iconCronometro;

    public MainScreenGUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(540, 760);
        this.setContentPane(panelMain);
        loadImages();
        initActionListeners();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initComponents() {
        labelCronometro.setIcon(iconCronometro);
        labelAlarm.setIcon(iconAlarm);
        labelThreads.setIcon(iconThreads);
        labelEmpleados.setIcon(iconEmpleados);
    }

    public void initActionListeners() {
        labelAlarm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var interfaz = new InterfazAlarma();
            }
        });

        labelCronometro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var interfazCronometro = new InterfazCronometro();
            }
        });

        labelThreads.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var interfazCuatroHilos = new InterfazCuatroHilos();
            }
        });

        labelEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var launcher = new LauncherEmpleados();
            }
        });
    }

    public void loadImages() {
        iconCronometro = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/misc/icon_stopwatch.png")));
        iconAlarm      = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/misc/icon_fire.png")));
        iconThreads    = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/misc/icon_threads.png")));
        iconEmpleados  = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/misc/icon_empleados.png")));
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
        panelMain.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        labelCronometro = new JLabel();
        Font labelCronometroFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 20, labelCronometro.getFont());
        if (labelCronometroFont != null) labelCronometro.setFont(labelCronometroFont);
        labelCronometro.setForeground(new Color(-1));
        labelCronometro.setHorizontalTextPosition(0);
        labelCronometro.setText("Cronómetro");
        labelCronometro.setVerticalTextPosition(3);
        panelMain.add(labelCronometro, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelAlarm = new JLabel();
        Font labelAlarmFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 20, labelAlarm.getFont());
        if (labelAlarmFont != null) labelAlarm.setFont(labelAlarmFont);
        labelAlarm.setForeground(new Color(-1));
        labelAlarm.setHorizontalTextPosition(0);
        labelAlarm.setText("Alarma de Fuego");
        labelAlarm.setVerticalTextPosition(3);
        panelMain.add(labelAlarm, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelThreads = new JLabel();
        Font labelThreadsFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 20, labelThreads.getFont());
        if (labelThreadsFont != null) labelThreads.setFont(labelThreadsFont);
        labelThreads.setForeground(new Color(-1));
        labelThreads.setHorizontalTextPosition(0);
        labelThreads.setText("Hilos sincronizados");
        labelThreads.setVerticalTextPosition(3);
        panelMain.add(labelThreads, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelEmpleados = new JLabel();
        Font labelEmpleadosFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 20, labelEmpleados.getFont());
        if (labelEmpleadosFont != null) labelEmpleados.setFont(labelEmpleadosFont);
        labelEmpleados.setForeground(new Color(-1));
        labelEmpleados.setHorizontalTextPosition(0);
        labelEmpleados.setText("Empleados");
        labelEmpleados.setVerticalTextPosition(3);
        panelMain.add(labelEmpleados, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelTitle = new JLabel();
        Font labelTitleFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 22, labelTitle.getFont());
        if (labelTitleFont != null) labelTitle.setFont(labelTitleFont);
        labelTitle.setForeground(new Color(-1));
        labelTitle.setText("Label");
        panelMain.add(labelTitle, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
