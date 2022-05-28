package com.misael.escuelabd;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class Conectar {

    Connection connection;
    Connection registro;

    public Connection conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tipo de conexión, servidor, base de datos, usuario, contraseña
            connection = DriverManager.getConnection("jdbc:mysql://localhost/escuela?allowMultiQueries=true", "root", "");
            System.out.println("Conexión realizada a la base de datos escuela.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problemas al conectar");
            System.exit(0);
        }
        return connection;
    }

    public DefaultTableModel fillTable(String sqlQuery) {

        Vector<Vector<Object>> data        = new Vector<>();
        int                    columns;
        Vector<Object>         columnNames = new Vector<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            columns = resultSet.getMetaData().getColumnCount();

            for (int i = 1; i <= columns; i++) {
                columnNames.add(resultSet.getMetaData().getColumnName(i));
            }

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();

                for (int i = 1; i <= columns; i++) {
                    row.add(resultSet.getObject(i));
                }

                data.add(row);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DefaultTableModel(data, columnNames);

    }

    public void executeQuery(String sqlQuery) {

        try {
            PreparedStatement preparedStatement = registro.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Fallo durante la ejecución de la operación");
        }

    }

    public ArrayList<Object> readData(String sqlQuery) {

        ArrayList<Object> data = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            int columns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    data.add(resultSet.getObject(i));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;

    }

}
