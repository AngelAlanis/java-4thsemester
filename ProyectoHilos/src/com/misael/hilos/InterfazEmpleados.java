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

    private GamePanel panelPrincipal;
    private JLabel    labelClint;
    private JLabel     labelRobin;
    private JLabel     btnSupervisor;

    private ImageIcon boton_Supervisar;

    private ImageIcon clint_Idle;
    private ImageIcon clint_Work1;
    private ImageIcon clint_Work2;

    private ImageIcon robin_Idle;
    private ImageIcon robin_Work;
    private ImageIcon robin_Scared;

    private Random random;

    public InterfazEmpleados() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        importarImagenes();
        configurarComponentes();
        initActionListeners();
        initThreads();
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initThreads() {
    }

    public void importarImagenes() {
        try {
            BufferedImage boton_Image = ImageIO.read(new File("ProyectoHilos/src/resources/botonSupervisar.png"));

            BufferedImage clintIdle_Image  = ImageIO.read(new File("ProyectoHilos/src/resources/clintIdle.png"));
            Image         clintWork1_Image = new ImageIcon("ProyectoHilos/src/resources/clintowork1.gif").getImage();
            Image         clintWork2_Image = new ImageIcon("ProyectoHilos/src/resources/clintwork2.gif").getImage();

            Image robinScared_Image = new ImageIcon("ProyectoHilos/src/resources/robin_scared.gif").getImage();
            Image robinIdle_Image   = new ImageIcon("ProyectoHilos/src/resources/robin_idle.gif").getImage();
            Image robinWork_Image   = new ImageIcon("ProyectoHilos/src/resources/robin_work.gif").getImage();

            boton_Supervisar = new ImageIcon(boton_Image);

            clint_Idle  = new ImageIcon(clintIdle_Image);
            clint_Work1 = new ImageIcon(clintWork1_Image);
            clint_Work2 = new ImageIcon(clintWork2_Image);

            robin_Idle   = new ImageIcon(robinIdle_Image);
            robin_Scared = new ImageIcon(robinScared_Image);
            robin_Work   = new ImageIcon(robinWork_Image);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void configurarComponentes() {
        random         = new Random();
        panelPrincipal = new GamePanel();
        labelClint     = new JLabel(clint_Idle);
        labelRobin     = new JLabel(robin_Idle);
        btnSupervisor  = new JLabel(boton_Supervisar);

        labelClint.setBounds(150, 250, 250, 310);
        labelRobin.setBounds(900, 280, 150, 250);
        btnSupervisor.setBounds(500, 20, 260, 80);

        panelPrincipal.add(labelClint);
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

                    TrabajoEmpleados.isSupervisorHere = true;


                } else {
                    labelClint.setIcon(clint_Idle);
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
