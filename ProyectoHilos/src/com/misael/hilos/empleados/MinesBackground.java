package com.misael.hilos.empleados;

import com.misael.hilos.SetupFile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class MinesBackground {

    Image     background;
    SetupFile setup = new SetupFile();
    static        int       bottomCoordinates;
    public static Rectangle hitbox;

    public MinesBackground() {
        background        = setup.image("/resources/backgrounds/mines.jpg");
        hitbox            = new Rectangle(0, 0, 1280, 170);
        bottomCoordinates = hitbox.height;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(background, 0, 0, 1280, 720, null);
    }

}
