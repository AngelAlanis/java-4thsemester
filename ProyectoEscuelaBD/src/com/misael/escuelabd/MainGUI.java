package com.misael.escuelabd;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;


public class MainGUI extends JFrame {
    private JPanel      panelMain;
    private JPanel      panelSidebar;
    private JLabel      labelAlumnos;
    private JLabel      labelProfesores;
    private JLabel      labelTutores;
    private JLabel      labelGrupos;
    private JLabel      labelSidebarIcon;
    private JLabel      labelLogo;
    private JPanel      panelInformacion;
    private JPanel      panelAlumnos;
    private JPanel      panelprofesores;
    private JPanel      panelTutores;
    private JPanel      panelGrupos;
    private JTable      tableAlumnos;
    private JLabel      labelTitulo;
    private JTextField  tfBusqueda;
    private JButton     btnNewAlumno;
    private JScrollPane spTableAlumnos;
    private JLabel      labelMenuPrincipal;

    private Conectar conectar;

    public MainGUI() {
        conectar = new Conectar();
        this.setSize(1024, 728);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        initActionListeners();
        //connectToDatabase();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initActionListeners() {
        btnNewAlumno.addActionListener(e -> {
            var newAlumno = new NewAlumnoGUI();
        });
    }

    public void connectToDatabase() {
        conectar.registro = conectar.conexion();
        tableAlumnos.setModel(conectar.fillTable("SELECT id_alumno, matricula, nombre, genero, fecha_nacimiento, telefono from alumno"));
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        var launcher = new MainGUI();
    }

}
