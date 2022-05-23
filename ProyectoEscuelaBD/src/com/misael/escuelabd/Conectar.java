package com.misael.escuelabd;

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
            connection = DriverManager.getConnection("jdbc:mysql://localhost/escuela", "root", "");
            System.out.println("Conexión realizada a la base de datos escuela.");
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
        defaultTableModel.addColumn("Dirección");
        defaultTableModel.addColumn("Cumpleaños");

        datosTabla = new String[7];


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
                datosTabla[5] = resultSet.getString(6);
                datosTabla[6] = resultSet.getString(7);
                defaultTableModel.addRow(datosTabla);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return defaultTableModel;
    }

}
