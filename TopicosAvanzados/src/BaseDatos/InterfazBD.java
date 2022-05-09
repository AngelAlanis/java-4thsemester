package BaseDatos;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InterfazBD extends JFrame {
    private JPanel      panelPrincipal;
    private JTextField  tfNombre;
    private JTextField  tfPuesto;
    private JTextField  tfDepartamento;
    private JLabel      lbNombre;
    private JLabel      lbPuesto;
    private JLabel      lbDepartamento;
    private JButton     btnGuardar;
    private JTable      tablaEmpleado;
    private JButton     btnSalir;
    private JScrollPane spEmpleado;
    private JTextField  tfTarjetaQuery;
    private JButton     btnTarjetaQuery;
    private JLabel      lbTarjetaQuery;
    private JButton     btnCargarRegistro;
    private JButton     btnModificar;
    private JButton     btnTodos;
    private JButton     btnEliminar;
    private Conectar    conectar;
    private Connection  registro;
    private String[]    datosTabla;
    private String      sqlQuery;

    public InterfazBD() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 400);
        conectarBaseDatos();
        initActionListeners();
        verTabla("");
        this.setContentPane(panelPrincipal);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        InterfazBD interfazBD = new InterfazBD();
    }

    public void initActionListeners() {
        btnGuardar.addActionListener(e -> guardarUsuario());

        btnSalir.addActionListener(e -> System.exit(0));

        btnTarjetaQuery.addActionListener(e -> consultarTarjeta());

        btnCargarRegistro.addActionListener(e -> cargarRegistroAModificar(tfTarjetaQuery.getText()));

        btnModificar.addActionListener(e -> modificarUsuario());

        btnTodos.addActionListener(e -> verTabla(""));

        btnEliminar.addActionListener(e -> eliminarRegistro());
    }

    private void eliminarRegistro() {
        try {
            int confirmation = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea borrar el registro?", "Borrar registro", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                PreparedStatement ps = registro.prepareStatement("DELETE FROM empleado WHERE tarjeta='" + tfTarjetaQuery.getText() + "'");
                ps.executeUpdate();
                verTabla("");
                JOptionPane.showMessageDialog(null, "Modificación realizada");
                limpiar();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void consultarTarjeta() {
        String numTarjeta = tfTarjetaQuery.getText();

        if (numTarjeta.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo está vacío");
        } else {
            verTabla(numTarjeta);
        }
    }

    private void guardarUsuario() {
        String nombre       = tfNombre.getText();
        String puesto       = tfPuesto.getText();
        String departamento = tfDepartamento.getText();

        sqlQuery = "INSERT INTO empleado (nombre,puesto,departamento) VALUES (?,?,?)";

        try {
            PreparedStatement preparedStatement = registro.prepareStatement(sqlQuery);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, puesto);
            preparedStatement.setString(3, departamento);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro guardado.");
            limpiar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        verTabla("");
    }

    private void modificarUsuario() {
        try {
            PreparedStatement ps = registro.prepareStatement("UPDATE empleado SET nombre = '" + tfNombre.getText() + "', puesto = '" + tfPuesto.getText() + "', departamento = '" + tfDepartamento.getText() + "' WHERE tarjeta='" + tfTarjetaQuery.getText() + "'");
            ps.executeUpdate();
            verTabla("");
            JOptionPane.showMessageDialog(null, "Modificación realizada");
            limpiar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void conectarBaseDatos() {
        conectar = new Conectar();
        registro = conectar.conexion();
    }

    private void limpiar() {
        tfNombre.setText("");
        tfPuesto.setText("");
        tfDepartamento.setText("");
        tfTarjetaQuery.setText("");
    }

    public void cargarRegistroAModificar(String numTarjeta) {
        sqlQuery = "SELECT * FROM empleado WHERE tarjeta='" + numTarjeta + "'";
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        try {
            Statement sentencia = registro.createStatement();
            ResultSet resultSet = sentencia.executeQuery(sqlQuery);

            while (resultSet.next()) {
                datosTabla[0] = resultSet.getString(1);
                datosTabla[1] = resultSet.getString(2);
                datosTabla[2] = resultSet.getString(3);
                datosTabla[3] = resultSet.getString(4);
                defaultTableModel.addRow(datosTabla);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tfNombre.setText(datosTabla[1]);
        tfPuesto.setText(datosTabla[2]);
        tfDepartamento.setText(datosTabla[3]);

    }

    public void verTabla(String numTarjeta) {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Tarjeta");
        defaultTableModel.addColumn("Nombre");
        defaultTableModel.addColumn("Puesto");
        defaultTableModel.addColumn("Departamento");
        tablaEmpleado.setModel(defaultTableModel);

        datosTabla = new String[4];

        if (numTarjeta.equals("")) {
            sqlQuery = "SELECT * FROM empleado";
        } else {
            sqlQuery = "SELECT * FROM empleado WHERE tarjeta='" + numTarjeta + "'";
        }

        try {
            Statement sentencia = registro.createStatement();
            ResultSet resultSet = sentencia.executeQuery(sqlQuery);

            while (resultSet.next()) {
                datosTabla[0] = resultSet.getString(1);
                datosTabla[1] = resultSet.getString(2);
                datosTabla[2] = resultSet.getString(3);
                datosTabla[3] = resultSet.getString(4);
                defaultTableModel.addRow(datosTabla);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayoutManager(9, 5, new Insets(0, 0, 0, 0), -1, -1));
        panelPrincipal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tfNombre = new JTextField();
        panelPrincipal.add(tfNombre, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfPuesto = new JTextField();
        panelPrincipal.add(tfPuesto, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDepartamento = new JTextField();
        panelPrincipal.add(tfDepartamento, new GridConstraints(2, 1, 2, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lbNombre = new JLabel();
        lbNombre.setText("Nombre:");
        panelPrincipal.add(lbNombre, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbPuesto = new JLabel();
        lbPuesto.setText("Puesto:");
        panelPrincipal.add(lbPuesto, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbDepartamento = new JLabel();
        lbDepartamento.setText("Departamento:");
        panelPrincipal.add(lbDepartamento, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnGuardar = new JButton();
        btnGuardar.setText("Guardar");
        panelPrincipal.add(btnGuardar, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        spEmpleado = new JScrollPane();
        panelPrincipal.add(spEmpleado, new GridConstraints(7, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tablaEmpleado = new JTable();
        spEmpleado.setViewportView(tablaEmpleado);
        btnSalir = new JButton();
        btnSalir.setText("Salir");
        panelPrincipal.add(btnSalir, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbTarjetaQuery = new JLabel();
        lbTarjetaQuery.setText("Consultar tarjeta:");
        panelPrincipal.add(lbTarjetaQuery, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfTarjetaQuery = new JTextField();
        panelPrincipal.add(tfTarjetaQuery, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        btnTarjetaQuery = new JButton();
        btnTarjetaQuery.setText("Consultar");
        panelPrincipal.add(btnTarjetaQuery, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnCargarRegistro = new JButton();
        btnCargarRegistro.setText("Cargar registro");
        panelPrincipal.add(btnCargarRegistro, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnModificar = new JButton();
        btnModificar.setText("Modificar");
        panelPrincipal.add(btnModificar, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnTodos = new JButton();
        btnTodos.setText("Todos");
        panelPrincipal.add(btnTodos, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnEliminar = new JButton();
        btnEliminar.setText("Eliminar");
        panelPrincipal.add(btnEliminar, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelPrincipal;
    }

}
