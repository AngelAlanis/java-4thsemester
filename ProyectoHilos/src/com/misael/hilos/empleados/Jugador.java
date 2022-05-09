package com.misael.hilos.empleados;

import com.misael.hilos.SetupFile;

import java.awt.*;

public class Jugador extends Entity {

    GamePanel  gp;
    KeyHandler keyHandler;
    SetupFile  setup = new SetupFile();

    Image image, up, left, down, right, up_stopped, left_stopped, down_stopped, right_stopped;

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
                gp.playSFX(2);
                gp.clint.isWorking     = !gp.clint.isWorking;
                gp.keyHandler.ePressed = false;
            } else if (keyHandler.ePressed && gp.robin.collisionOn) {
                gp.playSFX(2);
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
        if (y <= MinesBackground.bottomCoordinates) {
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
        up_stopped    = setup.image("/resources/characters/eliot/eliot_up_stopped.png");
        left_stopped  = setup.image("/resources/characters/eliot/eliot_left_stopped.png");
        down_stopped  = setup.image("/resources/characters/eliot/eliot_down_stopped.png");
        right_stopped = setup.image("/resources/characters/eliot/eliot_right_stopped.png");

        up    = setup.image("/resources/characters/eliot/eliot_up.gif");
        left  = setup.image("/resources/characters/eliot/eliot_left.gif");
        down  = setup.image("/resources/characters/eliot/eliot_down.gif");
        right = setup.image("/resources/characters/eliot/eliot_right.gif");
    }

    public void draw(Graphics2D g2) {
        hitbox.x      = x;
        hitbox.y      = y;
        hitbox.width  = width;
        hitbox.height = height;

        if (keyHandler.hPressed) {
            g2.setColor(Color.RED);
            g2.draw(hitbox);
            g2.draw(gp.clint.hitbox);
            g2.draw(gp.robin.hitbox);
            g2.fillRect(MinesBackground.hitbox.x, MinesBackground.hitbox.y, MinesBackground.hitbox.width, MinesBackground.hitbox.height);
        }

        g2.drawImage(image, x, y, width, height, null);
    }


}
