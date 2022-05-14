package AgendaDB;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Conectar {

    Connection connection;
    String[]   datosTabla;
    Interfaz   interfaz;
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

    public DefaultTableModel verTabla(String numContacto) {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Nombre");
        defaultTableModel.addColumn("Teléfono");
        defaultTableModel.addColumn("Correo");
        defaultTableModel.addColumn("Categoría");

        datosTabla = new String[4];

        if (numContacto.equals("")) {
            sqlQuery = "SELECT * FROM contactos";
        } else {
            sqlQuery = "SELECT * FROM contactos WHERE identificador='" + numContacto + "'";
        }

        try {
            Statement sentencia = registro.createStatement();
            ResultSet resultSet = sentencia.executeQuery(sqlQuery);

            while (resultSet.next()) {
                datosTabla[0] = resultSet.getString(2);
                datosTabla[1] = resultSet.getString(3);
                datosTabla[2] = resultSet.getString(4);
                datosTabla[3] = resultSet.getString(5);
                defaultTableModel.addRow(datosTabla);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return defaultTableModel;
    }


}
