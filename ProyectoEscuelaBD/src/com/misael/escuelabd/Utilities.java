package com.misael.escuelabd;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.Objects;

public class Utilities {

    public static String validate(String string) {
        if (string == null && string.isBlank()) {
            throw new NullPointerException("El string no es válido");
        } else {
            return string;
        }
    }

    public static int validate(int index) {
        if (index == 0) {
            throw new NumberFormatException("El número no es válido");
        } else {
            return index;
        }
    }

    public static ImageIcon setupIcon(String url, int width, int height, Color color) {
        BufferedImage bufferedImage = null;

        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(Utilities.class.getClassLoader().getResource(url)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert bufferedImage != null;
        BufferedImage coloredImage = colorImage(bufferedImage, r, g, b);
        Image         image        = coloredImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(Objects.requireNonNull(image));
    }

    public static ImageIcon setupImage(String url, int width, int height) {
        Image image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(Utilities.class.getClassLoader().getResource(url)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public static BufferedImage colorImage(BufferedImage image, int r, int g, int b) {
        int width  = image.getWidth();
        int height = image.getHeight();

        WritableRaster raster = image.getRaster();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int[] pixels = raster.getPixel(x, y, (int[]) null);
                pixels[0] = r;
                pixels[1] = g;
                pixels[2] = b;
                raster.setPixel(x, y, pixels);
            }
        }

        return image;
    }

}
