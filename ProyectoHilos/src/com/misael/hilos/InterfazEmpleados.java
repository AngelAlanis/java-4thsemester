package com.misael.hilos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class InterfazEmpleados extends JFrame {

    private PanelMinas panelPrincipal;
    private JLabel     labelClint;
    private JLabel     labelEliot;
    private JLabel     labelRobin;
    private JLabel     btnSupervisor;

    private ImageIcon boton_Supervisar;

    private ImageIcon clint_Idle;
    private ImageIcon clint_Work1;
    private ImageIcon clint_Work2;

    private ImageIcon eliot_Idle;
    private ImageIcon eliot_Work1;
    private ImageIcon eliot_Work2;

    private ImageIcon robin_Idle;
    private ImageIcon robin_Work;
    private ImageIcon robin_Scared;


    private Random random;

    public InterfazEmpleados() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        importarImagenes();
        configurarComponentes();
        initActionListeners();
        initThreads();
        this.setContentPane(panelPrincipal);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initThreads() {
        TrabajoEmpleados robin = new TrabajoEmpleados("Robin");
        TrabajoEmpleados clint = new TrabajoEmpleados("Clint");
        TrabajoEmpleados eliot = new TrabajoEmpleados("Eliot");
    }

    public void importarImagenes() {
        try {
            BufferedImage boton_Image = ImageIO.read(new File("ProyectoHilos/src/resources/botonSupervisar.png"));

            BufferedImage clintIdle_Image  = ImageIO.read(new File("ProyectoHilos/src/resources/clintIdle.png"));
            Image         clintWork1_Image = new ImageIcon("ProyectoHilos/src/resources/clintowork1.gif").getImage();
            Image         clintWork2_Image = new ImageIcon("ProyectoHilos/src/resources/clintwork2.gif").getImage();

            BufferedImage eliotIdle_Image  = ImageIO.read(new File("ProyectoHilos/src/resources/eliot_idle.png"));
            Image         eliotWork1_Image = new ImageIcon("ProyectoHilos/src/resources/eliott_work.gif").getImage();
            Image         eliotWork2_Image = new ImageIcon("ProyectoHilos/src/resources/eliot_work2.gif").getImage();

            Image robinScared_Image = new ImageIcon("ProyectoHilos/src/resources/robin_scared.gif").getImage();
            Image robinIdle_Image   = new ImageIcon("ProyectoHilos/src/resources/robin_idle.gif").getImage();
            Image robinWork_Image   = new ImageIcon("ProyectoHilos/src/resources/robin_work.gif").getImage();

            boton_Supervisar = new ImageIcon(boton_Image);

            clint_Idle  = new ImageIcon(clintIdle_Image);
            clint_Work1 = new ImageIcon(clintWork1_Image);
            clint_Work2 = new ImageIcon(clintWork2_Image);

            eliot_Idle  = new ImageIcon(eliotIdle_Image);
            eliot_Work1 = new ImageIcon(eliotWork1_Image);
            eliot_Work2 = new ImageIcon(eliotWork2_Image);

            robin_Idle   = new ImageIcon(robinIdle_Image);
            robin_Scared = new ImageIcon(robinScared_Image);
            robin_Work   = new ImageIcon(robinWork_Image);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void configurarComponentes() {
        random         = new Random();
        panelPrincipal = new PanelMinas();
        labelClint     = new JLabel(clint_Idle);
        labelEliot     = new JLabel(eliot_Idle);
        labelRobin     = new JLabel(robin_Idle);
        btnSupervisor  = new JLabel(boton_Supervisar);

        panelPrincipal.setLayout(null);

        labelClint.setBounds(150, 250, 250, 310);
        labelRobin.setBounds(900, 280, 150, 250);
        labelEliot.setBounds(500, 350, 120, 250);
        btnSupervisor.setBounds(500, 20, 260, 80);

        panelPrincipal.add(labelClint);
        panelPrincipal.add(labelEliot);
        panelPrincipal.add(labelRobin);
        panelPrincipal.add(btnSupervisor);

    }

    public void initActionListeners() {
        btnSupervisor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!TrabajoEmpleados.isSupervisorHere) {

                    labelRobin.setIcon(robin_Scared);
                    labelRobin.setIcon(robin_Work);


                    int clintAction = random.nextInt(2);
                    if (clintAction == 0) {
                        labelClint.setIcon(clint_Work1);
                    } else {
                        labelClint.setIcon(clint_Work2);
                    }

                    int eliotAction = random.nextInt(2);
                    if (eliotAction == 0) {
                        labelEliot.setIcon(eliot_Work1);
                    } else {
                        labelEliot.setIcon(eliot_Work2);
                    }

                    TrabajoEmpleados.isSupervisorHere = true;


                } else {
                    labelClint.setIcon(clint_Idle);
                    labelEliot.setIcon(eliot_Idle);
                    labelRobin.setIcon(robin_Idle);

                    TrabajoEmpleados.isSupervisorHere = false;
                }

            }
        });
    }

    public static void main(String[] args) {
        InterfazEmpleados interfazEmpleados = new InterfazEmpleados();
    }

}
