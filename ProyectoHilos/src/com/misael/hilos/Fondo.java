package com.misael.hilos;

import javax.swing.*;
import java.awt.*;

public class Fondo {

    Image background;
    Rectangle hitbox;

    public Fondo() {
        background = new ImageIcon("ProyectoHilos/src/resources/minesBackground.jpg").getImage();
        hitbox = new Rectangle(0, 0, 1280, 200);
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(background, 0, 0, 1280, 720, null);

        g2.setColor(new Color(255, 100, 100, 50));
        g2.fill(hitbox);

    }

}
