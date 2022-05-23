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

}
