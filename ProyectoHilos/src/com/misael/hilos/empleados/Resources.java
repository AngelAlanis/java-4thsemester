package com.misael.hilos.empleados;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Resources {

    Image stone;
    Image geode;
    Image iron;

    int totalStone, totalGeode, totalIron;

    public Resources() {
        readImages();
    }

    private void readImages() {
        try {
            geode = ImageIO.read(new File("ProyectoHilos/src/resources/misc/geode.png"));
            iron  = ImageIO.read(new File("ProyectoHilos/src/resources/misc/iron.png"));
            stone = ImageIO.read(new File("ProyectoHilos/src/resources/misc/stone.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
