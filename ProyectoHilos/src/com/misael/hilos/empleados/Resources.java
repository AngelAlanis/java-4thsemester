package com.misael.hilos.empleados;

import com.misael.hilos.SetupFile;

import java.awt.Image;


public class Resources {

    SetupFile setup = new SetupFile();

    Image stone;
    Image geode;
    Image iron;

    int totalStone, totalGeode, totalIron;

    public Resources() {
        readImages();
    }

    private void readImages() {
        geode = setup.image("/resources/misc/geode.png");
        iron  = setup.image("/resources/misc/iron.png");
        stone = setup.image("/resources/misc/stone.png");
    }

}
