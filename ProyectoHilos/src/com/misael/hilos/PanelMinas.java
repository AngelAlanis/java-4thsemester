package com.misael.hilos;

import javax.swing.*;
import java.awt.*;

public class PanelMinas extends JPanel {

    Image imageInUse;

    public PanelMinas() {
        imageInUse = new ImageIcon("ProyectoHilos/src/resources/minesBackground.jpg").getImage();
        this.setPreferredSize(new Dimension(1280, 720));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(imageInUse, 0, 0, this);
    }

}
