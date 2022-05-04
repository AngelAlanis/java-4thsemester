package com.misael.hilos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfazEmpleados extends JFrame {

    private PanelMinas panelPrincipal;
    private JLabel     labelClint;
    private JLabel     labelEliot;
    private JButton    btnSupervisor;
    private ImageIcon  clint_Idle;
    private ImageIcon  clint_Work1;
    private ImageIcon  clint_Work2;
    private ImageIcon  eliot_Idle;
    private ImageIcon  eliot_Work1;
    private ImageIcon  eliot_Work2;

    public InterfazEmpleados() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        importarImagenes();
        configurarComponentes();
        initActionListeners();
        this.setContentPane(panelPrincipal);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void importarImagenes() {
        try {
            BufferedImage ClintIdle_Image  = ImageIO.read(new File("ProyectoHilos/src/resources/clintIdle.png"));
            Image         ClintWork1_Image = new ImageIcon("ProyectoHilos/src/resources/clintowork1.gif").getImage();
            Image         ClintWork2_Image = new ImageIcon("ProyectoHilos/src/resources/clintwork2.gif").getImage();

            BufferedImage EliotIdle_Image  = ImageIO.read(new File("ProyectoHilos/src/resources/eliot_idle.png"));
            Image         EliotWork1_Image = new ImageIcon("ProyectoHilos/src/resources/eliott_work.gif").getImage();
            Image         EliotWork2_Image = new ImageIcon("ProyectoHilos/src/resources/eliott_work2.gif").getImage();

            clint_Idle  = new ImageIcon(ClintIdle_Image);
            clint_Work1 = new ImageIcon(ClintWork1_Image);
            clint_Work2 = new ImageIcon(ClintWork2_Image);

            eliot_Idle = new ImageIcon(EliotIdle_Image);
            eliot_Idle = new ImageIcon(EliotWork1_Image);
            eliot_Idle = new ImageIcon(EliotWork2_Image);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void configurarComponentes() {
        panelPrincipal = new PanelMinas();
        labelClint     = new JLabel(clint_Idle);
        labelEliot     = new JLabel(eliot_Idle);
        btnSupervisor  = new JButton("Supervisar");

        panelPrincipal.setLayout(null);

        labelClint.setBounds(150, 250, 250, 310);
        labelEliot.setBounds(200, 280, 110, 250);
        btnSupervisor.setBounds(600, 600, 100, 30);

        panelPrincipal.add(labelClint);
        panelPrincipal.add(labelEliot);
        panelPrincipal.add(btnSupervisor);

    }

    public void initActionListeners() {
        btnSupervisor.addActionListener(e -> {

            if (TrabajoEmpleados.isSupervisorHere) {
                labelClint.setIcon(clint_Work1);

            } else {
                labelClint.setIcon(clint_Idle);
            }

            TrabajoEmpleados.isSupervisorHere = !TrabajoEmpleados.isSupervisorHere;
        });
    }

    public static void main(String[] args) {
        InterfazEmpleados interfazEmpleados = new InterfazEmpleados();
    }

}
