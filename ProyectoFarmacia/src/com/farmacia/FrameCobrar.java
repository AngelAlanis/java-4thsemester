package com.farmacia;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FrameCobrar extends JFrame {
    JLabel labelInformacion = new JLabel("Ingrese la cantidad del cliente recibida");
    JPanel panelCobrar = new JPanel();
    JTextField tfCantidad = new JTextField();
    GridBagConstraints gbc = new GridBagConstraints();
    JTextPane tPRecibo = new JTextPane();

    String fileRoute = "ProyectoFarmacia/src/resources/ticketnumber.txt";
    FileInputStream fileInputStream = new FileInputStream(fileRoute);

    String stringTicket;
    float cantidadIngresada;

    public FrameCobrar() throws FileNotFoundException {
        this.setLayout(new GridBagLayout());
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setUndecorated(true);
        this.setContentPane(panelCobrar);
        this.setSize(280, 120);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void pedirCantidad(ArrayList<Producto> listaProductos, float total) {
        labelInformacion.setFont(new Font("Arial", Font.BOLD, 12));
        tfCantidad.setPreferredSize(new Dimension(150, 30));
        panelCobrar.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCobrar.add(labelInformacion, gbc);
        gbc.gridy = 1;
        panelCobrar.add(tfCantidad, gbc);

        definirActionListeners(listaProductos, total);
    }

    public void generarTicket(ArrayList<Producto> listaProductos, float total) throws Exception {
        if (cantidadIngresada < total) {
            JOptionPane.showMessageDialog(null, "La cantidad ingresada es menor a la del costo total.");
            return;
        }

        JTextPane tpRecibo = new JTextPane();
        tpRecibo.setEditable(false);

        DateTimeFormatter formatoFecha = DateTimeFormatter.ISO_DATE;
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String horaActual = formatoHora.format(now);
        String fechaActual = formatoFecha.format(now);

        ImageIcon logoByN = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/logoRecibo.png")));
        JLabel labelLogo = new JLabel();
        labelLogo.setIcon(logoByN);

        tpRecibo.setText(tpRecibo.getText() + ("--------------------------------------------------------"));
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n\n%34s", "VesaPharmacy")));
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%41s", "Blvd. Felipe Pescador 1830")));
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%45s", "Nueva Vizcaya, 34080 Durango, Dgo.")));
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%36s", "+52 610 438 2356")));

        tpRecibo.setText(tpRecibo.getText() + ("\n\n--------------------------------------------------------"));
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%-5s %-20.20s %-12s %-12s", "Cant.", "Articulo", "Precio unit.", "Importe")));
        tpRecibo.setText(tpRecibo.getText() + ("\n--------------------------------------------------------"));

        for (Producto listaProducto : listaProductos) {
            tpRecibo.setText(tpRecibo.getText() + (String.format("\n%-5s %-20.20s $%-12.2f $%-12.2f",
                    listaProducto.getCantidad(),
                    listaProducto.getDescripcion(),
                    listaProducto.getPrecioVenta(),
                    listaProducto.getImporte())));
        }

        tpRecibo.setText(tpRecibo.getText() + ("\n--------------------------------------------------------"));

        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%s $%41.2f", "Total", total)));
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%s $%38.2f", "Recibido", cantidadIngresada)));
        float cambio = cantidadIngresada - total;
        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%s $%40.2f", "Cambio", cambio)));

        tpRecibo.setText(tpRecibo.getText() + ("\n--------------------------------------------------------"));


        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%15s %5s %10s %5s", "Hora:", horaActual, "Fecha:", fechaActual)));

        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%32s", "Venta # " + getTicketNumber())));

        tpRecibo.setText(tpRecibo.getText() + ("\n********************************************************"));

        tpRecibo.setText(tpRecibo.getText() + (String.format("\n%38s", "GRACIAS POR SU COMPRA")));

        tpRecibo.setText(tpRecibo.getText() + ("\n********************************************************"));

        tpRecibo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

        stringTicket = tpRecibo.getText();

        try {
            printPDF(stringTicket);
            JOptionPane.showMessageDialog(null, "Venta realizada con éxito\nGracias por su compra!");
            Launcher.loginScreen.ventanaPrincipal.tableModelVentas.setRowCount(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void definirActionListeners(ArrayList<Producto> listaProductos, float total) {
        tfCantidad.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 10) {
                    if (tfCantidad.getText().isEmpty()) {

                    } else if ((Float.parseFloat(tfCantidad.getText())) <= 0) {

                    } else {
                        cantidadIngresada = Float.parseFloat(tfCantidad.getText());
                        try {
                            generarTicket(listaProductos, total);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        dispose();
                    }
                } else if (e.getKeyChar() == 27) {
                    dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void printPDF(String stringTicket) throws Exception {
        PDDocument pdDocument = new PDDocument();
        PDPage pdPage = new PDPage();
        pdDocument.addPage(pdPage);
        pdPage.setMediaBox(new PDRectangle(500, 700));

        PDFont pdFont = PDType1Font.COURIER;

        int ticketNumber = getTicketNumber();

        String nombrePDF =  ticketNumber +".pdf";

        String[] lineas = stringTicket.split("\n");

        PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdPage);
        contentStream.beginText();
        contentStream.setFont(pdFont, 14);
        float nextLine = pdPage.getMediaBox().getHeight() - 32;
        contentStream.newLineAtOffset(10, nextLine);

        for (String line : lineas) {
            contentStream.newLineAtOffset(0, -16);
            contentStream.showText(line);

        }

        contentStream.endText();
        contentStream.close();

        pdDocument.save("ProyectoFarmacia/src/resources/tickets//" + nombrePDF);

        pdDocument.close();

        System.out.println("PDF creado");

        int newTicketNumber = ticketNumber + 1;
        setTicketNumber(newTicketNumber);
    }

    public int getTicketNumber() throws Exception {
        Scanner scanner = new Scanner(Paths.get(fileRoute));

        return scanner.nextInt();
    }

    public void setTicketNumber(int ticketNumber) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(fileRoute);

        byte[] bytes = String.valueOf(ticketNumber).getBytes();
        outputStream.write(bytes);
        outputStream.close();
    }
}
