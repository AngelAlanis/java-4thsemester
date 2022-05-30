package com.misael.escuelabd;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.Objects;

public class Utilities {

    final static Color placeHolderColor = new Color(177, 179, 174);

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

        assert image != null;
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

    public static void setPlacerHolder(JTextField textField, String placeHolderText) {
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeHolderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().trim().equals(placeHolderText) || textField.getText().trim().isEmpty()) {
                    textField.setText(placeHolderText);
                    textField.setForeground(placeHolderColor);
                }
            }
        });
    }

    public static void setPlaceHolder(JComboBox comboBox, int minimumIndex) {
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (comboBox.getSelectedIndex() > minimumIndex) {
                    comboBox.setForeground(Color.BLACK);
                } else {
                    comboBox.setForeground(placeHolderColor);
                }
            }
        });

    }

    public static void filtrarLista(String busqueda, JTable table) {
        DefaultTableModel                 tableModel = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> trs        = new TableRowSorter<>(tableModel);
        table.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter("(?i)" + busqueda));
    }

    public static void setupTable(JTable table) {
        table.setFocusable(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(245, 127, 8));
        table.setShowVerticalLines(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(38, 153, 137));
        table.getTableHeader().setForeground(Color.WHITE);

    }
}
