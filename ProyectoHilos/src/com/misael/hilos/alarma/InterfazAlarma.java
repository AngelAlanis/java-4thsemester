package com.misael.hilos.alarma;


import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfazAlarma extends JFrame {

    private FondoFuego panelPrincipal;
    private JLabel     toggleFire;
    private JLabel     smokeDetector;
    private ImageIcon  fireAlarmImage;
    private ImageIcon  smokeDetectorActive;
    private ImageIcon  smokeDetectorInactive;
    private File       sfxAlarm;
    private File       sfxFire;
    private File       sfxExtinguisher;
    private Clip       alarmSound;
    private Clip       fireSound;
    private Clip       extinguisherSound;

    public InterfazAlarma() {
        this.setSize(640, 480);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        startThread();
        configurarComponentes();
        initActionListeners();
        initSoundEffects();
        this.setContentPane(panelPrincipal);
        this.setResizable(false);
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

        } catch (IOException e) {
            e.printStackTrace();
        }

        panelPrincipal = new FondoFuego();
        toggleFire     = new JLabel(fireAlarmImage);
        smokeDetector  = new JLabel(smokeDetectorInactive);

        panelPrincipal.setLayout(null);

        toggleFire.setBounds(0, 90, 150, 250);
        smokeDetector.setBounds(240, 0, 180, 100);

        panelPrincipal.add(toggleFire);
        panelPrincipal.add(smokeDetector);
        repaint();
    }

    private void initSoundEffects() {
        sfxAlarm        = new File("ProyectoHilos/src/resources/sound_effects/fire-alarm-sound-effect.wav");
        sfxFire         = new File("ProyectoHilos/src/resources/sound_effects/fire-sound-effect.wav");
        sfxExtinguisher = new File("ProyectoHilos/src/resources/sound_effects/fire-extinguisher-sound-effect-hd.wav");

        try {
            alarmSound        = AudioSystem.getClip();
            fireSound         = AudioSystem.getClip();
            extinguisherSound = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void playSounds() {
        try {
            extinguisherSound.close();

            alarmSound.open(AudioSystem.getAudioInputStream(sfxAlarm));
            fireSound.open(AudioSystem.getAudioInputStream(sfxFire));

            alarmSound.loop(Clip.LOOP_CONTINUOUSLY);
            fireSound.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopSounds() {
        alarmSound.close();
        fireSound.close();

        try {
            extinguisherSound.open(AudioSystem.getAudioInputStream(sfxExtinguisher));
            extinguisherSound.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initActionListeners() {

        toggleFire.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (Alarma.isOnFire) {
                    stopFire();
                } else {
                    startFire();
                }

                panelPrincipal.repaint();
            }
        });

    }

    private void startThread() {
        Alarma alarma = new Alarma();
        Thread hilo   = new Thread(alarma);
        hilo.start();
    }

    private void startFire() {
        Alarma.isOnFire = true;
        playSounds();
        panelPrincipal.imageInUse = panelPrincipal.imageFire;
        smokeDetector.setIcon(smokeDetectorActive);
    }

    private void stopFire() {
        stopSounds();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        panelPrincipal.imageInUse = panelPrincipal.imageNormal;
        smokeDetector.setIcon(smokeDetectorInactive);
        Alarma.isOnFire = false;
    }
}
