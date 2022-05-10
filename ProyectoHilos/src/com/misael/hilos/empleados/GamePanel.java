package com.misael.hilos.empleados;

import com.misael.hilos.SetupFile;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

    Thread           gameThread;
    Resources        resources        = new Resources();
    KeyHandler       keyHandler       = new KeyHandler();
    Jugador          jugador          = new Jugador(this, keyHandler);
    MinesBackground  minesBackground  = new MinesBackground();
    Sound            sound            = new Sound();
    CollisionChecker collisionChecker = new CollisionChecker(this, keyHandler);
    SetupFile        setup            = new SetupFile();
    NPC[]            npcs             = new NPC[2];

    boolean isRunning = true;

    final int FPS = 45;

    public GamePanel() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setDoubleBuffered(true);
        setupGame();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.setLayout(null);
    }

    public void setupGame() {
        createNPCs();
        startGameThread();
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

        for (int i = 0; i < npcs.length; i++) {
            npcs[i].update();
        }
    }

    public void createNPCs() {
        npcs[0] = new NPC(this, "Clint", keyHandler);
        npcs[1] = new NPC(this, "Robin", keyHandler);

        npcs[0].setSize(240, 300);
        npcs[0].setLocation(150, 250);

        npcs[1].setSize(120, 232);
        npcs[1].setLocation(900, 280);

        npcs[0].idle  = setup.image("/resources/characters/clint/clint_idle.png");
        npcs[0].work1 = setup.image("/resources/characters/clint/clint_work1.gif");
        npcs[0].work2 = setup.image("/resources/characters/clint/clint_work2.gif");

        npcs[0].material1 = resources.totalGeode;
        npcs[0].material2 = resources.totalIron;

        npcs[1].idle  = setup.image("/resources/characters/robin/robin_idle.gif");
        npcs[1].work1 = setup.image("/resources/characters/robin/robin_work.gif");
        npcs[1].work2 = setup.image("/resources/characters/robin/robin_work.gif");

        npcs[1].material1 = resources.totalStone;

        npcs[1].image = npcs[1].idle;
        npcs[0].image = npcs[0].idle;

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        minesBackground.draw(g2);

        for (int i = 0; i < npcs.length; i++) {
            npcs[i].draw(g2);
        }

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
