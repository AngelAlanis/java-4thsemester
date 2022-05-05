package com.misael.hilos.empleados;

import javax.swing.*;
import java.awt.*;

public class MinesBackground {

    Image background;
    static        int       bottomCoordinates;
    public static Rectangle hitbox;

    public MinesBackground() {
        background        = new ImageIcon("ProyectoHilos/src/resources/backgrounds/mines.jpg").getImage();
        hitbox            = new Rectangle(0, 0, 1280, 170);
        bottomCoordinates = hitbox.height;
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(background, 0, 0, 1280, 720, null);

//        g2.setColor(new Color(255, 100, 100, 50));
//        g2.fill(hitbox);
//
//        g2.setColor(Color.BLUE);
//        g2.drawLine(0, bottomCoordinates, hitbox.width, bottomCoordinates);

    }

}
