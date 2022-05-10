package com.misael.hilos.cronometro;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.misael.hilos.SetupFile;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    SetupFile setupFile = new SetupFile();

    public InterfazCronometro() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(540, 960);
        this.setContentPane(panelMain);
        loadImages();
        initActionListeners();
        initComponents();
        startThread();
        loadStoppedScreen();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void loadStoppedScreen() {
        labelPlay.setVisible(true);
        labelPause.setVisible(false);
    }

    public void loadRunningScreen() {
        labelPlay.setVisible(false);
        labelPause.setVisible(true);
    }

    public void initComponents() {
        labelPlay.setIcon(playButton);
        labelPause.setIcon(pauseButton);
        labelStop.setIcon(stopButton);
    }

    public void loadImages() {
        playButton  = setupFile.imageIcon("/resources/misc/play.png");
        pauseButton = setupFile.imageIcon("/resources/misc/pause.png");
        stopButton  = setupFile.imageIcon("/resources/misc/stop.png");
    }

    public void initActionListeners() {
        labelPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cronometro.resumeCronometro();
                loadRunningScreen();
            }
        });

        labelPause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cronometro.pauseCronometro();
                loadStoppedScreen();

            }
        });

        labelStop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cronometro.stopCronometro();
                loadStoppedScreen();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cronometro.stopThread();
            }
        });
    }

    public void update() {
        String time = "";

        if (cronometro.horas > 0) {
            if (cronometro.horas < 10) {
                time += "0" + cronometro.horas + ":";
            } else {
                time += cronometro.horas + ":";
            }
        }

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
