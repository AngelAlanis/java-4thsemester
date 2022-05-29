package com.misael.escuelabd;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGUI extends JFrame {
    private JPanel      panelMain;
    private JPanel      panelSidebar;
    private JLabel      labelAlumnos;
    private JLabel      labelOtrasConsultas;
    private JLabel      labelTutores;
    private JLabel      labelGrupos;
    private JLabel      labelSidebarIcon;
    private JLabel      labelLogo;
    private JPanel      panelInformacion;
    private JPanel      panelAlumnos;
    private JPanel      panelOtrasConsultas;
    private JPanel      panelTutores;
    private JPanel      panelGrupos;
    private JTable      tableAlumnos;
    private JLabel      labelTitulo;
    private JTextField  tfBusquedaAlumnos;
    private JButton     btnNewAlumno;
    private JScrollPane spTableAlumnos;
    private JLabel      labelMenuPrincipal;
    private JButton     btnModificarAlumno;
    private JButton     btnBajaAlumno;
    private JTable      tableTutores;
    private JTextField  tfBusquedaTutores;
    private JButton     btnNuevoTutor;
    private JButton     btnModificarTutor;
    private JLabel      labelTituloTutores;
    private JScrollPane spTutores;
    private JTable      tableConsulta;
    private JTextField  tfCustomQuery;
    private JButton     btnConsultar;
    private JScrollPane spConsulta;
    private JLabel      lbTituloConsultas;
    private JTable      tableGrupos;
    private JLabel      labelTituloGrupos;
    private JScrollPane spGrupos;
    private JLabel      labelFinanzas;
    private JPanel      panelFinanzas;
    private JTable      tableFinanzas;
    private JLabel      labelTituloFinanzas;
    private JTextField  tfFinanzas;
    private JScrollPane spFinanzas;
    private JButton     btnFiltro;

    private ImageIcon logo;
    private ImageIcon add;
    private ImageIcon delete;
    private ImageIcon edit;
    private ImageIcon group;
    private ImageIcon home;
    private ImageIcon mysql;
    private ImageIcon money;
    private ImageIcon search;
    private ImageIcon student;
    private ImageIcon teacher;
    private ImageIcon sidebar;
    private ImageIcon filter;
    private FilterGUI filterGUI;

    Conectar conectar;

    public MainGUI() {
        conectar = new Conectar();
        this.setSize(1024, 728);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        initComponents();
        initActionListeners();
        //connectToDatabase();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initActionListeners() {

        initTabsListeners();

        btnNewAlumno.addActionListener(e -> {
            var newAlumno = new NewAlumnoGUI(this);
        });

        btnNuevoTutor.addActionListener(e -> {
            var newTutor = new NewTutorGUI(this);
        });

        btnModificarAlumno.addActionListener(e -> modifyAlumno());

        btnBajaAlumno.addActionListener(e -> disableAlumno());


        btnModificarTutor.addActionListener(e -> modifyTutor());

        btnConsultar.addActionListener(e -> tableConsulta.setModel(conectar.fillTable(tfCustomQuery.getText().trim())));

        tfBusquedaAlumnos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Utilities.filtrarLista(tfBusquedaAlumnos.getText(), tableAlumnos);
            }
        });

        tfBusquedaTutores.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Utilities.filtrarLista(tfBusquedaTutores.getText(), tableTutores);
            }
        });

        tfFinanzas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Utilities.filtrarLista(tfFinanzas.getText(), tableFinanzas);
            }
        });

        btnFiltro.addActionListener(e -> {
            filterGUI.setVisible(!filterGUI.isVisible());
        });
    }

    public void initComponents() {
        readIcons();
        setIcons();
        setupTables();

        filterGUI = new FilterGUI(btnFiltro.getX() + btnFiltro.getWidth() * 3, btnFiltro.getY() + btnFiltro.getHeight() * 2);
    }


    private void initTabsListeners() {
        labelAlumnos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarPanelAlumno();
            }
        });

        labelTutores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarPanelTutores();
            }
        });

        labelGrupos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarPanelGrupos();
            }
        });

        labelOtrasConsultas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarPanelProfesores();
            }
        });

        labelFinanzas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarPanelFinanzas();
            }
        });
    }

    public void connectToDatabase() {
        conectar.registro = conectar.conexion();
        refreshTable();
    }

    public void refreshTable() {
        tableAlumnos.setModel(conectar.fillTable("SELECT alumno.id_alumno, alumno.matricula, alumno.nombre, grado.nivel, grado.grado, alumno.genero, alumno.fecha_nacimiento, alumno.telefono, tutor.nombre \n" +
                                                         "FROM alumno, tutor, alumno_tutor, grado, inscripcion\n" +
                                                         "WHERE alumno.id_alumno = alumno_tutor.id_alumno AND alumno_tutor.id_tutor = tutor.id_tutor AND inscripcion.id_alumno = alumno.id_alumno AND grado.id_grado = inscripcion.id_grado;"));
        tableTutores.setModel(conectar.fillTable("SELECT id_tutor, nombre, rfc, telefono FROM tutor"));
        tableGrupos.setModel(conectar.fillTable("SELECT id_grado, clave, nivel, grado FROM grado"));
        tableFinanzas.setModel(conectar.fillTable("SELECT inscripcion.id_inscripcion, inscripcion.id_alumno, alumno.matricula, alumno.nombre, grado.nivel, grado.grado, monto, pagado FROM inscripcion, alumno, grado WHERE inscripcion.id_alumno = alumno.id_alumno AND inscripcion.id_grado = grado.id_grado"));
    }

    private void cargarPanelAlumno() {
        panelInformacion.removeAll();
        panelInformacion.add(panelAlumnos);
        panelInformacion.revalidate();
        panelInformacion.repaint();
    }

    private void cargarPanelTutores() {
        panelInformacion.removeAll();
        panelInformacion.add(panelTutores);
        panelInformacion.revalidate();
        panelInformacion.repaint();
    }

    private void cargarPanelGrupos() {
        panelInformacion.removeAll();
        panelInformacion.add(panelGrupos);
        panelInformacion.revalidate();
        panelInformacion.repaint();
    }

    private void cargarPanelProfesores() {
        panelInformacion.removeAll();
        panelInformacion.add(panelOtrasConsultas);
        panelInformacion.revalidate();
        panelInformacion.repaint();
    }

    private void cargarPanelFinanzas() {
        panelInformacion.removeAll();
        panelInformacion.add(panelFinanzas);
        panelInformacion.revalidate();
        panelInformacion.repaint();
    }

    private void modifyTutor() {
        int idTutor;
        try {
            idTutor = (int) tableTutores.getValueAt(tableTutores.getSelectedRow(), 0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No seleccionó un tutor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        var editTutor = new EditTutorGUI(this, idTutor);
    }

    private void disableAlumno() {
        int idAlumno = 0;
        try {
            idAlumno = (int) tableAlumnos.getValueAt(tableAlumnos.getSelectedRow(), 0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No seleccionó ningún alumno", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este alumno?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            conectar.executeQuery("DELETE FROM alumno WHERE id_alumno = " + idAlumno);
            refreshTable();
        }
    }

    private void modifyAlumno() {
        int idAlumno = 0;
        try {
            idAlumno = (int) tableAlumnos.getValueAt(tableAlumnos.getSelectedRow(), 0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No seleccionó ningún alumno", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        var editAlumno = new EditAlumnoGUI(this, idAlumno);
    }

    private void setupTables() {
        Utilities.setupTable(tableAlumnos);
        Utilities.setupTable(tableTutores);
        Utilities.setupTable(tableFinanzas);
        Utilities.setupTable(tableGrupos);
        Utilities.setupTable(tableConsulta);
    }

    private void readIcons() {
        add     = Utilities.setupIcon("add.png", 30, 30, Color.white);
        delete  = Utilities.setupIcon("delete.png", 30, 30, Color.white);
        group   = Utilities.setupIcon("group.png", 30, 30, Color.white);
        home    = Utilities.setupIcon("home.png", 30, 30, Color.white);
        mysql   = Utilities.setupIcon("mysql.png", 30, 30, Color.white);
        money   = Utilities.setupIcon("money.png", 30, 30, Color.white);
        search  = Utilities.setupIcon("search.png", 30, 30, Color.white);
        student = Utilities.setupIcon("student.png", 30, 30, Color.white);
        teacher = Utilities.setupIcon("teacher.png", 30, 30, Color.white);
        edit    = Utilities.setupIcon("edit.png", 30, 30, Color.white);
        sidebar = Utilities.setupIcon("sidebar.png", 30, 30, Color.white);
        filter  = Utilities.setupIcon("filter.png", 30, 30, Color.white);
        logo    = Utilities.setupImage("school_logo.png", 197, 45);
    }

    private void setIcons() {
        labelLogo.setIcon(logo);
        labelSidebarIcon.setIcon(sidebar);
        btnFiltro.setIcon(filter);
        labelMenuPrincipal.setIcon(home);
        labelGrupos.setIcon(group);
        labelOtrasConsultas.setIcon(mysql);
        labelAlumnos.setIcon(student);
        labelTutores.setIcon(teacher);
        labelFinanzas.setIcon(money);
        btnNewAlumno.setIcon(add);
        btnModificarAlumno.setIcon(edit);
        btnBajaAlumno.setIcon(delete);
        btnNuevoTutor.setIcon(add);
        btnModificarTutor.setIcon(edit);
        btnConsultar.setIcon(search);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        var launcher = new MainGUI();
    }

}
