package com.misael.hilos;


import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
    private JLabel     btnToggleFire;
    private ImageIcon  fireAlarmImage;
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
            BufferedImage imagenAlarma = ImageIO.read(new File("ProyectoHilos/src/resources/fireAlarm.png"));
            fireAlarmImage = new ImageIcon(imagenAlarma);
//            sfxAlarm = new File("ProyectoHilos/src/resources/fire-alarm-sound-effect.mp3");
//            Clip clip = AudioSystem.getClip();
//            clip.open(AudioSystem.getAudioInputStream(sfxAlarm));
//            clip.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        panelPrincipal  = new FondoFuego();
        btnToggleFire   = new JLabel(fireAlarmImage);
        labelFireStatus = new JLabel("false");

        panelPrincipal.add(btnToggleFire);
        repaint();
    }

    private void initActionListeners() {

        Alarma alarma = new Alarma();
        Thread hilo   = new Thread(alarma);
        hilo.start();

        btnToggleFire.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Alarma.isOnFire) {
                    Alarma.isOnFire           = false;
                    panelPrincipal.imageInUse = panelPrincipal.imageNormal;
                } else {
                    Alarma.isOnFire           = true;
                    panelPrincipal.imageInUse = panelPrincipal.imageFire;

                }

                labelFireStatus.setText(String.valueOf(Alarma.isOnFire));
                panelPrincipal.repaint();
            }
        });

    }
}
