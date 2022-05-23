package AgendaDB;

import com.formdev.flatlaf.FlatDarkLaf;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Interfaz extends JFrame {
    private JPanel            panelMain;
    private JScrollPane       spListaContactoss;
    private JPanel            panelBotones;
    private JButton           btnNuevo;
    private JButton           btnBorrar;
    private JButton           btnEditar;
    private JButton           btnGuardar;
    private JButton           btnCancelar;
    private JTextField        tfNombre;
    private JTextField        tfTelefono;
    private JTextField        tfCorreo;
    private JComboBox<String> cbCategoria;
    private JLabel            labelNombre;
    private JLabel            lbTelefono;
    private JLabel            lbCategoria;
    private JLabel            lbCorreo;
    public  JTable            tableContactos;
    private JTextField        tfCumpleaños;
    private JTextField        tfDireccion;
    private JLabel     lbCumpleaños;
    private JLabel     lbDireccion;
    private JTextField tfBusqueda;
    private JLabel     lbBuscar;
    private Conectar   conectar;
    private String[]   categorias;
    private int               identificador;

    public Interfaz() {
        super("Agenda");
        conectar = new Conectar();
        configurarComponentes();
        this.setSize(1000, 300);
        this.setContentPane(panelMain);
        conectarBaseDatos();
        configurarComponentes();
        initActionListeners();
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void conectarBaseDatos() {
        conectar.registro = conectar.conexion();
        actualizarTabla();
    }

    private void actualizarTabla() {
        tableContactos.setModel(conectar.verTabla());
    }

    public void initActionListeners() {
        btnNuevo.addActionListener(e -> {
            guardarNuevo();
            limpiar();
            actualizarTabla();
        });

        btnBorrar.addActionListener(e -> {
            int identificador = Integer.parseInt(String.valueOf(tableContactos.getValueAt(tableContactos.getSelectedRow(), 0)));
            conectar.eliminarRegistro(identificador);
            actualizarTabla();
        });

        btnEditar.addActionListener(e -> cargarRegistro());

        btnGuardar.addActionListener(e -> {
            Contacto contacto = new Contacto(identificador, tfNombre.getText(), tfTelefono.getText(), tfCorreo.getText(), String.valueOf(cbCategoria.getSelectedItem()), tfDireccion.getText(), tfCumpleaños.getText());
            conectar.modificarUsuario(contacto);
            actualizarTabla();
            limpiar();
        });

        tfBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                filtrarLista(tfBusqueda.getText(), tableContactos);
            }
        });
    }

    private void limpiar() {
        tfNombre.setText("");
        tfTelefono.setText("");
        tfCorreo.setText("");
        cbCategoria.setSelectedIndex(0);
        tfDireccion.setText("");
        tfCumpleaños.setText("");
    }

    private void guardarNuevo() {
        String   nombre     = tfNombre.getText();
        String   telefono   = tfTelefono.getText();
        String   correo     = tfCorreo.getText();
        String   categoria  = String.valueOf(cbCategoria.getSelectedItem());
        String   direccion  = tfDireccion.getText();
        String   cumpleaños = tfCumpleaños.getText();
        Contacto contacto   = new Contacto(nombre, telefono, correo, categoria, direccion, cumpleaños);
        conectar.guardarContacto(contacto);
    }

    private void configurarComponentes() {
        categorias = new String[]{"Familiar", "Amistad", "Trabajo"};
        cbCategoria.setModel(new DefaultComboBoxModel<>(categorias));
        spListaContactoss.setPreferredSize(new Dimension(650, 200));
    }

    private void cargarRegistro() {
        identificador = Integer.parseInt(String.valueOf(tableContactos.getValueAt(tableContactos.getSelectedRow(), 0)));
        Contacto contacto = conectar.cargarContacto(identificador);
        tfNombre.setText(contacto.getNombre());
        tfTelefono.setText(contacto.getTelefono());
        tfCorreo.setText(contacto.getCorreo());
        cbCategoria.setSelectedIndex(cBSelectedIndex(contacto.getCategoria()));
        tfDireccion.setText(contacto.getDireccion());
        tfCumpleaños.setText(contacto.getCumpleaños());
    }

    private int cBSelectedIndex(String item) {
        switch (item) {
            case "Familiar" -> {
                return 0;
            }
            case "Amistad" -> {
                return 1;
            }
            case "Trabajo" -> {
                return 2;
            }
        }
        return -1;
    }

    public void filtrarLista(String busqueda, JTable table) {
        DefaultTableModel                 tableModel = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> trs        = new TableRowSorter<>(tableModel);
        table.setRowSorter(trs);

        trs.setRowFilter(RowFilter.regexFilter("(?i)" + busqueda));
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        var Interfaz = new Interfaz();
    }


}
