package com.misael.escuelabd;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainGUI extends JFrame {

    private FilterGUI filterGUI;
    private ImageIcon add;
    private ImageIcon delete;
    private ImageIcon edit;
    private ImageIcon filter;
    private ImageIcon group;
    private ImageIcon home;
    private ImageIcon logo;
    private ImageIcon money;
    private ImageIcon mysql;
    private ImageIcon search;
    private ImageIcon sidebar;
    private ImageIcon student;
    private ImageIcon teacher;

    private JButton btnAlumnos;
    private JButton btnBajaAlumno;
    private JButton btnConsultar;
    private JButton btnFiltro;
    private JButton btnFinanzas;
    private JButton btnGrupos;
    private JButton btnMenuPrincipal;
    private JButton btnModificarAlumno;
    private JButton btnModificarTutor;
    private JButton btnNewAlumno;
    private JButton btnNuevoTutor;
    private JButton btnOtrasConsultas;
    private JButton btnTutores;

    private JLabel labelLogo;
    private JLabel labelTitulo;
    private JLabel labelTituloFinanzas;
    private JLabel labelTituloGrupos;
    private JLabel labelTituloTutores;
    private JLabel lbTituloConsultas;

    private JPanel panelAlumnos;
    private JPanel panelFinanzas;
    private JPanel panelGrupos;
    private JPanel panelInformacion;
    private JPanel panelMain;
    private JPanel panelOtrasConsultas;
    private JPanel panelSidebar;
    private JPanel panelTutores;

    private JScrollPane spConsulta;
    private JScrollPane spFinanzas;
    private JScrollPane spGrupos;
    private JScrollPane spTableAlumnos;
    private JScrollPane spTutores;

    private JTable tableConsulta;
    private JTable tableFinanzas;
    private JTable tableGrupos;
    private JTable tableTutores;

    private JTextField tfBusquedaAlumnos;
    private JTextField tfBusquedaTutores;
    private JTextField tfCustomQuery;
    private JTextField tfFinanzas;

    public  JTable tableAlumnos;
    private JLabel labelTotalesFinanzas;

    private float totalGanacias, totalPorPagar, totalPagado;

    Conectar conectar;

    public MainGUI() {
        conectar = new Conectar();
        this.setSize(1024, 728);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        initComponents();
        initActionListeners();
        connectToDatabase();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initComponents() {
        filterGUI = new FilterGUI(this);
        readIcons();
        setIcons();
        setupTables();
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
            filterGUI.setLocation(btnFiltro.getX() + btnFiltro.getWidth() * 3, btnFiltro.getY() + btnFiltro.getHeight() * 2);
            filterGUI.setVisible(!filterGUI.isVisible());
        });
    }

    private void initTabsListeners() {
        btnAlumnos.addActionListener(e -> cargarPanelAlumno());
        btnTutores.addActionListener(e -> cargarPanelTutores());
        btnGrupos.addActionListener(e -> cargarPanelGrupos());
        btnFinanzas.addActionListener(e -> cargarPanelFinanzas());
        btnOtrasConsultas.addActionListener(e -> cargarPanelConsultas());
    }

    public void connectToDatabase() {
        conectar.registro = conectar.connect();
        refreshTable();
    }

    public void refreshTable() {
        tableAlumnos.setModel(conectar.fillTable("SELECT alumno.id_alumno, alumno.matricula, alumno.nombre, grado.nivel, grado.grado, alumno.genero, alumno.fecha_nacimiento, alumno.telefono, tutor.nombre \n" +
                                                         "FROM alumno, tutor, alumno_tutor, grado, inscripcion\n" +
                                                         "WHERE alumno.id_alumno = alumno_tutor.id_alumno AND alumno_tutor.id_tutor = tutor.id_tutor AND inscripcion.id_alumno = alumno.id_alumno AND grado.id_grado = inscripcion.id_grado;"));
        tableTutores.setModel(conectar.fillTable("SELECT id_tutor, nombre, rfc, telefono FROM tutor"));
        tableGrupos.setModel(conectar.fillTable("SELECT id_grado, clave, nivel, grado FROM grado"));

        tableFinanzas.setModel(conectar.fillTable("SELECT inscripcion.id_inscripcion, inscripcion.id_alumno, alumno.matricula, alumno.nombre, grado.nivel, grado.grado, monto, pagado FROM inscripcion, alumno, grado WHERE inscripcion.id_alumno = alumno.id_alumno AND inscripcion.id_grado = grado.id_grado"));

        totalPorPagar = 0;
        totalPagado   = 0;
        totalGanacias = 0;

        for (int i = 0; i < tableFinanzas.getRowCount(); i++) {
            totalPorPagar += Float.parseFloat(String.valueOf(tableFinanzas.getValueAt(i, 6)));
            totalPagado += Float.parseFloat(String.valueOf(tableFinanzas.getValueAt(i, 7)));
        }

        totalPorPagar = (totalPagado - totalPorPagar) * -1;
        totalGanacias = totalPagado;

        String resumenFinanzas = "Por pagar: $" + totalPorPagar + "    Pagado: $" + totalPagado + "     Ganancias: $" + totalGanacias;

        labelTotalesFinanzas.setText(resumenFinanzas);
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

    private void cargarPanelConsultas() {
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
            JOptionPane.showMessageDialog(null, "No seleccion?? un tutor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        var editTutor = new EditTutorGUI(this, idTutor);
    }

    private void disableAlumno() {
        int idAlumno = 0;
        try {
            idAlumno = (int) tableAlumnos.getValueAt(tableAlumnos.getSelectedRow(), 0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No seleccion?? ning??n alumno", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(null, "??Est?? seguro que desea eliminar este alumno?", "Confirmar", JOptionPane.YES_NO_OPTION);
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
            JOptionPane.showMessageDialog(null, "No seleccion?? ning??n alumno", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        var editAlumno = new EditAlumnoGUI(this, idAlumno);
    }

    private void setupTables() {
        Utilities.setupTable(tableAlumnos);
        Utilities.setupTable(tableConsulta);
        Utilities.setupTable(tableFinanzas);
        Utilities.setupTable(tableGrupos);
        Utilities.setupTable(tableTutores);
    }

    private void readIcons() {
        add     = Utilities.setupIcon("add.png", 30, 30, Color.white);
        delete  = Utilities.setupIcon("delete.png", 30, 30, Color.white);
        edit    = Utilities.setupIcon("edit.png", 30, 30, Color.white);
        filter  = Utilities.setupIcon("filter.png", 30, 30, Color.white);
        group   = Utilities.setupIcon("group.png", 30, 30, Color.white);
        home    = Utilities.setupIcon("home.png", 30, 30, Color.white);
        logo    = Utilities.setupImage("school_logo.png", 197, 45);
        money   = Utilities.setupIcon("money.png", 30, 30, Color.white);
        mysql   = Utilities.setupIcon("mysql.png", 30, 30, Color.white);
        search  = Utilities.setupIcon("search.png", 30, 30, Color.white);
        sidebar = Utilities.setupIcon("sidebar.png", 30, 30, Color.white);
        student = Utilities.setupIcon("student.png", 30, 30, Color.white);
        teacher = Utilities.setupIcon("teacher.png", 30, 30, Color.white);
    }

    private void setIcons() {
        btnBajaAlumno.setIcon(delete);
        btnConsultar.setIcon(search);
        btnFiltro.setIcon(filter);
        btnModificarAlumno.setIcon(edit);
        btnModificarTutor.setIcon(edit);
        btnNewAlumno.setIcon(add);
        btnNuevoTutor.setIcon(add);
        btnAlumnos.setIcon(student);
        btnFinanzas.setIcon(money);
        btnGrupos.setIcon(group);
        labelLogo.setIcon(logo);
        btnMenuPrincipal.setIcon(home);
        btnOtrasConsultas.setIcon(mysql);
        btnTutores.setIcon(teacher);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        var launcher = new MainGUI();
    }

}
