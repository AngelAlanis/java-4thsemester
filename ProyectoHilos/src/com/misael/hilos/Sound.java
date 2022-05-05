package com.misael.hilos;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class Sound {

    Clip   clip;
    File[] soundURL = new File[30];

    public Sound() {
        soundURL[0] = new File("ProyectoHilos/src/resources/sound_effects/axchop.wav");
        soundURL[1] = new File("ProyectoHilos/src/resources/sound_effects/bug_cave.wav");
        soundURL[2] = new File("ProyectoHilos/src/resources/sound_effects/dialogueCharacter.wav");
        soundURL[3] = new File("ProyectoHilos/src/resources/sound_effects/hammer.wav");
        soundURL[4] = new File("ProyectoHilos/src/resources/sound_effects/stoneStep.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}
