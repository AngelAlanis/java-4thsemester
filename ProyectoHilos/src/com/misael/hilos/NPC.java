package com.misael.hilos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class NPC extends Entidad {

    Image work1, work2, interactButton;
    Image      image;
    GamePanel  gp;
    KeyHandler keyHandler;
    boolean    isWorking = false;

    public NPC(GamePanel gp, KeyHandler keyHandler) {
        this.gp         = gp;
        this.keyHandler = keyHandler;
        hitbox          = new Rectangle(x, y, width, height);
        image           = idle;

        try {
            interactButton = ImageIO.read(new File("ProyectoHilos/src/resources/interactbutton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSize(int width, int height) {
        this.width  = width;
        this.height = height;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void work() {

        if (image == idle) {
            Random random = new Random();
            int    action = random.nextInt(2);

            if (action == 0) {
                image = work1;
            } else if (action == 1 && work2 != null) {
                image = work2;
            }
        }


    }

    public void rest() {
        image = idle;
    }

    public void update() {
        if (isWorking) {
            work();
        } else {
            rest();
        }
    }

    public void draw(Graphics2D g2) {
        hitbox.x      = x;
        hitbox.y      = y;
        hitbox.width  = width;
        hitbox.height = height;

        if (collisionOn) {
            int center = (x + (width / 2) - 45);
            g2.drawImage(interactButton, center, y - 100, 90, 90, null);
        }
//
//        g2.setColor(Color.RED);
//        g2.draw(hitbox);
        g2.drawImage(image, x, y, width, height, null);

    }

}
