package com.misael.escuelabd;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private JPanel      panelOtrasConsultas;
    private JPanel      panelTutores;
    private JPanel      panelGrupos;
    private JTable      tableAlumnos;
    private JLabel      labelTitulo;
    private JTextField  tfBusqueda;
    private JButton     btnNewAlumno;
    private JScrollPane spTableAlumnos;
    private JLabel      labelMenuPrincipal;
    private JButton     btnModificarAlumno;
    private JButton     btnBajaAlumno;
    private JTable      tableTutores;
    private JTextField  textField1;
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

    Conectar conectar;

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

        tabsListeners();

        btnNewAlumno.addActionListener(e -> {
            var newAlumno = new NewAlumnoGUI(this);
        });

        btnModificarAlumno.addActionListener(e -> {
            int idAlumno   = (int) tableAlumnos.getValueAt(tableAlumnos.getSelectedRow(), 0);
            var editAlumno = new EditAlumnoGUI(this, idAlumno);
        });

        btnBajaAlumno.addActionListener(e -> {
            int idAlumno = (int) tableAlumnos.getValueAt(tableAlumnos.getSelectedRow(), 0);
            int opcion   = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este alumno?", "Confirmar", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                conectar.executeQuery("DELETE FROM alumno WHERE id_alumno = " + idAlumno);
            }
        });

        btnNuevoTutor.addActionListener(e -> {
            var newTutor = new NewTutorGUI(this);
        });

        btnModificarTutor.addActionListener(e -> {
            int idTutor   = (int) tableTutores.getValueAt(tableTutores.getSelectedRow(), 0);
            var editTutor = new EditTutorGUI(this, idTutor);
        });

        btnConsultar.addActionListener(e -> {
            tableConsulta.setModel(conectar.fillTable(tfCustomQuery.getText().trim()));
        });
    }

    private void tabsListeners() {
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

        labelProfesores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarPanelProfesores();
            }
        });
    }

    public void connectToDatabase() {
        conectar.registro = conectar.conexion();
        refreshTable();
    }

    public void refreshTable() {
        tableAlumnos.setModel(conectar.fillTable("SELECT id_alumno, matricula, nombre, genero, fecha_nacimiento, telefono FROM alumno"));
        tableTutores.setModel(conectar.fillTable("SELECT id_tutor, nombre, rfc, telefono FROM tutor"));
        tableGrupos.setModel(conectar.fillTable("SELECT id_grado, clave, nivel, grado FROM grado"));
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

    public static void main(String[] args) {
        FlatLightLaf.setup();
        var launcher = new MainGUI();
    }

}
