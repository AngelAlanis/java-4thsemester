package com.misael.hilos.empleados;

import com.misael.hilos.SetupFile;

import java.awt.*;
import java.awt.event.KeyEvent;

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
        direction = DIRECTION_DOWN;
    }

    public void update() {

        if (keyIsBeingPressed()) {

            gp.collisionChecker.checkCollision();

            stopWhenHittingWall();

            if (keyHandler.upPressed) {
                goUp();
            } else if (keyHandler.leftPressed) {
                goLeft();
            } else if (keyHandler.downPressed) {
                goDown();
            } else if (keyHandler.rightPressed) {
                goRight();
            } else if (keyHandler.ePressed) {
                changeNPCWorkState();
            }

        } else {
            switch (direction) {
                case DIRECTION_UP -> image = up_stopped;
                case DIRECTION_LEFT -> image = left_stopped;
                case DIRECTION_DOWN -> image = down_stopped;
                case DIRECTION_RIGHT -> image = right_stopped;
            }
        }
    }

    private void goRight() {
        direction = DIRECTION_RIGHT;
        x += speed;
        image     = right;
    }

    private void goDown() {
        direction = DIRECTION_DOWN;
        y += speed;
        image     = down;
    }

    private void goLeft() {
        direction = DIRECTION_LEFT;
        x -= speed;
        image     = left;
    }

    private void goUp() {
        direction = DIRECTION_UP;
        y -= speed;
        image     = up;
    }

    private boolean keyIsBeingPressed() {
        return keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed || keyHandler.ePressed;
    }

    private void changeNPCWorkState() {
        for (int i = 0; i < gp.npcs.length; i++) {

            if (gp.npcs[i].collisionOn) {
                gp.playSFX(2);
                gp.npcs[i].isWorking = !gp.npcs[i].isWorking;
            }
        }

        gp.keyHandler.ePressed = false;
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
            g2.draw(gp.npcs[0].hitbox);
            g2.draw(gp.npcs[1].hitbox);
            g2.fillRect(MinesBackground.hitbox.x, MinesBackground.hitbox.y, MinesBackground.hitbox.width, MinesBackground.hitbox.height);
        }

        g2.drawImage(image, x, y, width, height, null);
    }


}
