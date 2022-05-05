package com.misael.hilos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Jugador extends Entidad {

    GamePanel  gp;
    KeyHandler keyHandler;

    Image work1, work2, up, left, down, right;

    public Jugador(GamePanel gp, KeyHandler keyHandler) {
        this.gp         = gp;
        this.keyHandler = keyHandler;

        hitbox = new Rectangle(x, y, width, height);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        width     = 104;
        height    = 232;
        x         = 500;
        y         = 350;
        speed     = 8;
        direction = "up";
    }

    public void update() {

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed || keyHandler.ePressed) {

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

            collisionOn = false;
            gp.collisionChecker.checkCollision();

            if (collisionOn) {
                if (keyHandler.ePressed) {
                    System.out.println("interacted");
                }
            }

        }


        //System.out.println("x: " + x + " y: " + y);
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
        hitbox.x      = x;
        hitbox.y      = y;
        hitbox.width  = width;
        hitbox.height = height;

        Image image = null;

        switch (direction) {
            case "up" -> image = up;
            case "left" -> image = left;
            case "down" -> image = down;
            case "right" -> image = right;
        }

        g2.setColor(Color.RED);
        g2.draw(hitbox);
        g2.drawImage(image, x, y, width, height, null);

    }

}
