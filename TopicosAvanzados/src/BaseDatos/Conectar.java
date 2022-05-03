package BaseDatos;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conectar {

    Connection connection;

    public Connection conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tipo de conexión, servidor, base de datos, usuario, contraseña
            connection = DriverManager.getConnection("jdbc:mysql://localhost/trabajo", "root", "");
            System.out.println("Conexión realizada.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problemas al conectar");
        }
        return connection;
    }
}
