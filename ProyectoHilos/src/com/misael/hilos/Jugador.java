package com.misael.hilos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Jugador extends Entidad {

    GamePanel  gp;
    KeyHandler keyHandler;

    Image image, work1, work2, up, left, down, right, up_stopped, left_stopped, down_stopped, right_stopped;

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

        if (keyIsBeingPressed()) {
            gp.collisionChecker.checkCollision();

            stopWhenHittingWall();

            gp.playSFX(4);

            if (keyHandler.upPressed) {
                direction = "up";
                y -= speed;
                image     = up;
            } else if (keyHandler.leftPressed) {
                direction = "left";
                x -= speed;
                image     = left;
            } else if (keyHandler.downPressed) {
                direction = "down";
                y += speed;
                image     = down;
            } else if (keyHandler.rightPressed) {
                direction = "right";
                x += speed;
                image     = right;
            } else if (keyHandler.ePressed && gp.clint.collisionOn) {
                gp.clint.isWorking     = !gp.clint.isWorking;
                gp.keyHandler.ePressed = false;
            } else if (keyHandler.ePressed && gp.robin.collisionOn) {
                gp.robin.isWorking     = !gp.robin.isWorking;
                gp.keyHandler.ePressed = false;
            }
        } else {
            switch (direction) {
                case "up" -> image = up_stopped;
                case "left" -> image = left_stopped;
                case "down" -> image = down_stopped;
                case "right" -> image = right_stopped;
            }
        }
    }

    private boolean keyIsBeingPressed() {
        return keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed || keyHandler.ePressed;
    }

    private void stopWhenHittingWall() {
        if (y <= Fondo.bottomCoordinates) {
            keyHandler.upPressed = false;
        }
        if (y >= 480) {
            keyHandler.downPressed = false;
        }
        if (x <= 0) {
            keyHandler.leftPressed = false;
        }
        if (x >= 1100) {
            keyHandler.rightPressed = false;
        }
    }

    public void getPlayerImage() {
        try {
            up_stopped    = ImageIO.read(new File("ProyectoHilos/src/resources/eliot_up_stopped.png"));
            left_stopped  = ImageIO.read(new File("ProyectoHilos/src/resources/eliot_left_stopped.png"));
            down_stopped  = ImageIO.read(new File("ProyectoHilos/src/resources/eliot_down_stopped.png"));
            right_stopped = ImageIO.read(new File("ProyectoHilos/src/resources/eliot_right_stopped.png"));

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
        drawHitbox(g2);
        g2.drawImage(image, x, y, width, height, null);

    }

    private void drawHitbox(Graphics2D g2) {
        hitbox.x      = x;
        hitbox.y      = y;
        hitbox.width  = width;
        hitbox.height = height;
//
//        g2.setColor(Color.RED);
//        g2.draw(hitbox);
    }

}
