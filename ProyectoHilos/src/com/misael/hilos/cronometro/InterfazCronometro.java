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

}
