package com.misael.hilos;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    Thread     gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Jugador    jugador    = new Jugador(this, keyHandler);

    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setDoubleBuffered(true);
        startGameThread();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.setLayout(null);

    }

//    @Override
//    public void run() {
//
//        double drawInterval = 1000000000 / FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread != null) {
//            update();
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000;
//
//                if (remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        jugador.update();
    }


    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2 = (Graphics2D) g;

        jugador.draw(g2);

        g2.dispose();
    }


    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta        = 0;
        long   lastTime     = System.nanoTime();
        long   currentTime;
        long   timer        = 0;
        int    drawCount    = 0;

        while (gameThread != null) {
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
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer     = 0;
            }

        }
    }
}
