package AgendaDB;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Conectar {

    Connection connection;
    String[]   datosTabla;
    String     sqlQuery;
    Connection registro;

    public Connection conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tipo de conexión, servidor, base de datos, usuario, contraseña
            connection = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "");
            System.out.println("Conexión realizada.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problemas al conectar");
        }
        return connection;
    }

    public DefaultTableModel verTabla() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Identificador");
        defaultTableModel.addColumn("Nombre");
        defaultTableModel.addColumn("Teléfono");
        defaultTableModel.addColumn("Correo");
        defaultTableModel.addColumn("Categoría");

        datosTabla = new String[5];


        sqlQuery = "SELECT * FROM contactos";


        try {
            Statement sentencia = registro.createStatement();
            ResultSet resultSet = sentencia.executeQuery(sqlQuery);

            while (resultSet.next()) {
                datosTabla[0] = resultSet.getString(1);
                datosTabla[1] = resultSet.getString(2);
                datosTabla[2] = resultSet.getString(3);
                datosTabla[3] = resultSet.getString(4);
                datosTabla[4] = resultSet.getString(5);
                defaultTableModel.addRow(datosTabla);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return defaultTableModel;
    }

    public Contacto cargarContacto(int identificador) {

        datosTabla = new String[5];

        sqlQuery = "SELECT * FROM contactos WHERE identificador='" + identificador + "'";

        try {
            Statement sentencia = registro.createStatement();
            ResultSet resultSet = sentencia.executeQuery(sqlQuery);

            while (resultSet.next()) {
                datosTabla[0] = resultSet.getString(1);
                datosTabla[1] = resultSet.getString(2);
                datosTabla[2] = resultSet.getString(3);
                datosTabla[3] = resultSet.getString(4);
                datosTabla[4] = resultSet.getString(5);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Contacto(datosTabla[1], datosTabla[2], datosTabla[3], datosTabla[4]);

    }

    public void guardarContacto(Contacto contacto) {

        sqlQuery = "INSERT INTO contactos (nombre,teléfono,correo,categoría) VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = registro.prepareStatement(sqlQuery);
            preparedStatement.setString(1, contacto.getNombre());
            preparedStatement.setString(2, contacto.getTelefono());
            preparedStatement.setString(3, contacto.getCorreo());
            preparedStatement.setString(4, contacto.getCategoria());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro guardado.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        verTabla();
    }

    public void eliminarRegistro(int identificador) {


        try {
            int confirmation = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea borrar el registro?", "Borrar registro", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                PreparedStatement preparedStatement = registro.prepareStatement("DELETE FROM contactos WHERE identificador='" + identificador + "'");
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Modificación realizada");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void modificarUsuario(Contacto contacto) {
        try {
            PreparedStatement ps = registro.prepareStatement("UPDATE contactos SET nombre = '" + contacto.getNombre() + "', teléfono = '" + contacto.getTelefono() + "', correo = '" + contacto.getCorreo() + "', categoría = '" + contacto.getCategoria() + "' WHERE identificador='" + contacto.getIdentificador() + "'");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Modificación realizada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
