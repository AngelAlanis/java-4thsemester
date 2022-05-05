package com.misael.hilos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Jugador {

    int width;
    int height;
    int x;
    int y;
    int speed;

    GamePanel  gp;
    KeyHandler keyHandler;

    BufferedImage idle, work1, work2, up, left, down, right;
    String direction;

    public Jugador(GamePanel gp, KeyHandler keyHandler) {
        this.gp         = gp;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        width     = 120;
        height    = 250;
        x         = 500;
        y         = 350;
        speed     = 8;
        direction = "up";
    }

    public void update() {
        if (keyHandler.upPressed) {
            direction = "up";
            y -= speed;
        } else if (keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
        } else if (keyHandler.downPressed) {
            direction = "down";
            y += speed;
        } else if (keyHandler.rightPressed) {
            direction = "right";
            x += speed;
        }

        System.out.println("x: " + x + " y: " + y);
    }

    public void getPlayerImage() {
        try {
            idle  = ImageIO.read(new File("ProyectoHilos/src/resources/eliot_idle.png"));
            work1 = new ImageIcon("ProyectoHilos/src/resources/eliott_work.gif").getImage();
            work2 = new ImageIcon("ProyectoHilos/src/resources/eliot_work2.gif").getImage();
            up    = new ImageIcon("ProyectoHilos/src/resources/eliot_up.gif").getImage();
            left  = new ImageIcon("ProyectoHilos/src/resources/eliot_left.gif").getImage();
            down  = new ImageIcon("ProyectoHilos/src/resources/eliot_down.gif").getImage();
            right = new ImageIcon("ProyectoHilos/src/resources/eliot_right.gif").getImage();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {

        Image image = null;

        switch (direction) {
            case "up" -> image = up;
            case "left" -> image = left;
            case "down" -> image = down;
            case "right" -> image = right;
        }

        g2.drawImage(image, x, y, width, height, null);

    }

}
