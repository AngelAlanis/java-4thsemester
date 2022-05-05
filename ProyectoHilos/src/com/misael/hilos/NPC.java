package com.misael.hilos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class NPC extends Entidad {

    String name;
    Image  work1, work2, interactButton;
    Image   image;
    boolean isInteracted = false;

    public NPC(String name) {
        this.name = name;
        hitbox    = new Rectangle(x, y, width, height);
        image     = idle;

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

    public void update() {
        if (isInteracted) {

            Random random = new Random();
            int    action = random.nextInt(2);

            if (action == 0 && isInteracted) {
                image = work1;
            } else if (action == 1 && work2 != null && isInteracted) {
                image = work2;
            }

        } else {
            image = idle;
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

        g2.setColor(Color.RED);
        g2.draw(hitbox);
        g2.drawImage(image, x, y, width, height, null);

    }

}
