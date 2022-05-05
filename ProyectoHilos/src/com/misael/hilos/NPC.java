package com.misael.hilos;

import java.awt.*;

public class NPC extends Entidad {

    String name;
    Image  work1, work2;
    boolean isInteracted = false;

    public NPC(String name) {
        this.name = name;
        hitbox    = new Rectangle(x, y, width, height);
    }

    public void setSize(int width, int height) {
        this.width  = width;
        this.height = height;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2) {
        hitbox.x      = x;
        hitbox.y      = y;
        hitbox.width  = width;
        hitbox.height = height;

        Image image = idle;

        g2.setColor(Color.RED);
        g2.draw(hitbox);
        g2.drawImage(image, x, y, width, height, null);

    }

}
