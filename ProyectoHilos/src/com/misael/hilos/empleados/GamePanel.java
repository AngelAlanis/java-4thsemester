package com.misael.hilos.empleados;

import com.misael.hilos.SetupFile;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    Thread           gameThread;
    Resources        resources        = new Resources();
    KeyHandler       keyHandler       = new KeyHandler();
    Jugador          jugador          = new Jugador(this, keyHandler);
    MinesBackground  minesBackground  = new MinesBackground();
    Sound            sound            = new Sound();
    CollisionChecker collisionChecker = new CollisionChecker(this, keyHandler);
    SetupFile        setup            = new SetupFile();

    NPC clint;
    NPC robin;

    boolean isRunning = true;

    int FPS = 45;

    public GamePanel() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setDoubleBuffered(true);
        createNPCs();
        startGameThread();
        setupGame();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.setLayout(null);
    }

    public void setupGame() {
        playMusic(1);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopThread() {
        isRunning = false;
    }

    public void update() {
        jugador.update();
        clint.update();
        robin.update();
    }

    public void createNPCs() {
        clint = new NPC(this, "Clint", keyHandler);
        robin = new NPC(this, "Robin", keyHandler);

        clint.setSize(240, 300);
        clint.setLocation(150, 250);

        robin.setSize(120, 232);
        robin.setLocation(900, 280);

        clint.idle  = setup.image("/resources/characters/clint/clint_idle.png");
        clint.work1 = setup.image("/resources/characters/clint/clint_work1.gif");
        clint.work2 = setup.image("/resources/characters/clint/clint_work2.gif");

        clint.material1 = resources.totalGeode;
        clint.material2 = resources.totalIron;

        robin.idle  = setup.image("/resources/characters/robin/robin_idle.gif");
        robin.work1 = setup.image("/resources/characters/robin/robin_work.gif");
        robin.work2 = setup.image("/resources/characters/robin/robin_work.gif");

        robin.material1 = resources.totalStone;

        robin.image = robin.idle;
        clint.image = clint.idle;

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        minesBackground.draw(g2);
        clint.draw(g2);
        robin.draw(g2);
        jugador.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSFX(int i) {
        sound.setFile(i);
        sound.play();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta        = 0;
        long   lastTime     = System.nanoTime();
        long   currentTime;
        long   timer        = 0;
        int    drawCount    = 0;

        while (isRunning) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                //System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer     = 0;
            }

        }
    }
}
