package com.misael.hilos;

import javax.swing.*;
import java.awt.*;

public class Fondo {

    Image background;

    public Fondo() {
        background = new ImageIcon("ProyectoHilos/src/resources/minesBackground.jpg").getImage();
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(background, 0, 0, 1280, 720, null);

    }

}
