package com.misael.hilos.cronometro;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class InterfazCronometro extends JFrame {
    private JPanel     panelMain;
    private JLabel     labelTime;
    private JLabel     labelStop;
    private JLabel     labelPlay;
    private JLabel     labelPause;
    private ImageIcon  playButton;
    private ImageIcon  pauseButton;
    private ImageIcon  stopButton;
    private Cronometro cronometro;

    public InterfazCronometro() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(540, 960);
        this.setContentPane(panelMain);
        loadImages();
        initActionListeners();
        initComponents();
        startThread();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void initComponents() {
        labelPlay.setIcon(playButton);
        labelPause.setIcon(pauseButton);
        labelStop.setIcon(stopButton);
    }

    public void loadImages() {
        try {
            playButton  = new ImageIcon(ImageIO.read(new File("ProyectoHilos/src/resources/misc/play.png")));
            pauseButton = new ImageIcon(ImageIO.read(new File("ProyectoHilos/src/resources/misc/pause.png")));
            stopButton  = new ImageIcon(ImageIO.read(new File("ProyectoHilos/src/resources/misc/stop.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initActionListeners() {
        labelPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        labelPause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        labelStop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    public void update() {
        String time = "";

        if (cronometro.minutos < 10) {
            time += "0" + cronometro.minutos + ":";
        } else {
            time += cronometro.minutos + ":";
        }

        if (cronometro.segundos < 10) {
            time += "0" + cronometro.segundos;
        } else {
            time += cronometro.segundos;
        }

        if (cronometro.milisegundos < 10) {
            time += ".0" + cronometro.milisegundos;
        } else {
            time += "." + cronometro.milisegundos;
        }


        labelTime.setText(time);
    }

    public void startThread() {
        cronometro = new Cronometro(this);
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
        panelMain.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        labelTime = new JLabel();
        Font labelTimeFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 48, labelTime.getFont());
        if (labelTimeFont != null) labelTime.setFont(labelTimeFont);
        labelTime.setForeground(new Color(-1));
        labelTime.setText("00:00.00");
        panelMain.add(labelTime, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelStop = new JLabel();
        labelStop.setText("");
        panelMain.add(labelStop, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelPlay = new JLabel();
        labelPlay.setText("");
        panelMain.add(labelPlay, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelPause = new JLabel();
        labelPause.setText("");
        panelMain.add(labelPause, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
