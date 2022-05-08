package com.misael.hilos.empleados;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;

public class Sound {

    ArrayList<Clip> clip = new ArrayList<>();
    File[]          soundURL = new File[30];

    public Sound() {
        soundURL[0] = new File("ProyectoHilos/src/resources/sound_effects/axchop.wav");
        soundURL[1] = new File("ProyectoHilos/src/resources/sound_effects/bug_cave.wav");
        soundURL[2] = new File("ProyectoHilos/src/resources/sound_effects/dialogueCharacter.wav");
        soundURL[3] = new File("ProyectoHilos/src/resources/sound_effects/hammer.wav");
        soundURL[4] = new File("ProyectoHilos/src/resources/sound_effects/stoneStep.wav");
        soundURL[5] = new File("ProyectoHilos/src/resources/sound_effects/newArtifact.wav");
        soundURL[6] = new File("ProyectoHilos/src/resources/sound_effects/give_gift.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip.add(AudioSystem.getClip());
            clip.get(clip.size() - 1).open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.get(clip.size() - 1).start();
    }

    public void loop() {
        clip.get(clip.size() - 1).loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.iterator().next().close();
    }

}
