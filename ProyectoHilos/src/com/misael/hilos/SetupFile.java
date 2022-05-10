package com.misael.hilos;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import java.util.Objects;

public class SetupFile {

    public Image image(String URL) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(URL))).getImage();
    }

    public ImageIcon imageIcon(String URL) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(URL)));
    }

    public File file(String URL) {
        return new File(Objects.requireNonNull(getClass().getResource(URL)).getPath());
    }

}
