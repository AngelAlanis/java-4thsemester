package com.misael.hilos;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfazAlarma extends JFrame {
    private FondoFuego panelPrincipal;
    private JLabel     labelFireStatus;
    private JLabel     toggleFire;
    private JLabel     smokeDetector;
    private ImageIcon  fireAlarmImage;
    private ImageIcon  smokeDetectorActive;
    private ImageIcon  smokeDetectorInactive;
    private File       sfxAlarm;

    public InterfazAlarma() {
        this.setSize(640, 480);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        configurarComponentes();
        initActionListeners();
        this.setContentPane(panelPrincipal);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void configurarComponentes() {
        try {
            BufferedImage imagenAlarma        = ImageIO.read(new File("ProyectoHilos/src/resources/fireAlarm.png"));
            Image         imagenSmokeActive   = new ImageIcon("ProyectoHilos/src/resources/smoke_detector_animated.gif").getImage();
            BufferedImage imagenSmokeInactive = ImageIO.read(new File("ProyectoHilos/src/resources/smoke_detector_normal.png"));

            fireAlarmImage        = new ImageIcon(imagenAlarma);
            smokeDetectorInactive = new ImageIcon(imagenSmokeInactive);
            smokeDetectorActive   = new ImageIcon(imagenSmokeActive);

//            sfxAlarm = new File("ProyectoHilos/src/resources/fire-alarm-sound-effect.mp3");
//            Clip clip = AudioSystem.getClip();
//            clip.open(AudioSystem.getAudioInputStream(sfxAlarm));
//            clip.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        panelPrincipal  = new FondoFuego();
        toggleFire      = new JLabel(fireAlarmImage);
        labelFireStatus = new JLabel("false");
        smokeDetector   = new JLabel(smokeDetectorInactive);

        panelPrincipal.setLayout(null);

        toggleFire.setBounds(0, 90, 150, 250);
        smokeDetector.setBounds(240, 0, 180, 100);

        panelPrincipal.add(toggleFire);
        panelPrincipal.add(smokeDetector);
        repaint();
    }

    private void initActionListeners() {

        Alarma alarma = new Alarma();
        Thread hilo   = new Thread(alarma);
        hilo.start();

        toggleFire.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Alarma.isOnFire) {
                    Alarma.isOnFire           = false;
                    panelPrincipal.imageInUse = panelPrincipal.imageNormal;
                    smokeDetector.setIcon(smokeDetectorInactive);
                } else {
                    Alarma.isOnFire           = true;
                    panelPrincipal.imageInUse = panelPrincipal.imageFire;
                    smokeDetector.setIcon(smokeDetectorActive);
                }

                labelFireStatus.setText(String.valueOf(Alarma.isOnFire));
                panelPrincipal.repaint();
            }
        });

    }
}
