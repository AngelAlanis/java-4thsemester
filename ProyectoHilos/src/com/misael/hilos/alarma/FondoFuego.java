package com.misael.hilos.alarma;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FondoFuego extends JPanel {

    Image imageFire;
    Image imageNormal;
    Image imageInUse;

    public FondoFuego() {
        imageFire   = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/backgrounds/wooden_wall_fire.gif"))).getImage();
        imageNormal = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/backgrounds/wooden_wall.jpg"))).getImage();

        imageInUse = imageNormal;

        this.setPreferredSize(new Dimension(640, 480));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(imageInUse, 0, 0, this);
    }

}
