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


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(8, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        spListaContactoss = new JScrollPane();
        panelMain.add(spListaContactoss, new GridConstraints(1, 1, 6, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableContactos = new JTable();
        spListaContactoss.setViewportView(tableContactos);
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelBotones, new GridConstraints(7, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnNuevo = new JButton();
        btnNuevo.setText("Nuevo");
        panelBotones.add(btnNuevo, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnBorrar = new JButton();
        btnBorrar.setText("Borrar");
        panelBotones.add(btnBorrar, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnEditar = new JButton();
        btnEditar.setText("Editar");
        panelBotones.add(btnEditar, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnGuardar = new JButton();
        btnGuardar.setText("Guardar");
        panelBotones.add(btnGuardar, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnCancelar = new JButton();
        btnCancelar.setText("Cancelar");
        panelBotones.add(btnCancelar, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelNombre = new JLabel();
        labelNombre.setText("Nombre:");
        panelMain.add(labelNombre, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbTelefono = new JLabel();
        lbTelefono.setText("Teléfono:");
        panelMain.add(lbTelefono, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbCorreo = new JLabel();
        lbCorreo.setText("Correo:");
        panelMain.add(lbCorreo, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfNombre = new JTextField();
        panelMain.add(tfNombre, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfTelefono = new JTextField();
        panelMain.add(tfTelefono, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfCorreo = new JTextField();
        panelMain.add(tfCorreo, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cbCategoria = new JComboBox();
        panelMain.add(cbCategoria, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfCumpleaños = new JTextField();
        panelMain.add(tfCumpleaños, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDireccion = new JTextField();
        panelMain.add(tfDireccion, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lbCategoria = new JLabel();
        lbCategoria.setText("Categoría:");
        panelMain.add(lbCategoria, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbDireccion = new JLabel();
        lbDireccion.setText("Direccion:");
        panelMain.add(lbDireccion, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbCumpleaños = new JLabel();
        lbCumpleaños.setText("Cumpleaños:");
        panelMain.add(lbCumpleaños, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfBusqueda = new JTextField();
        panelMain.add(tfBusqueda, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lbBuscar = new JLabel();
        lbBuscar.setText("Buscar");
        panelMain.add(lbBuscar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}