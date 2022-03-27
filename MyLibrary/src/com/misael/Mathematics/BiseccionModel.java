package com.misael.Mathematics;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BiseccionModel extends AbstractTableModel {

    private String[] columnNames = {
            "i",
            "a",
            "b",
            "xi",
            "error",
            "f(a)",
            "f(xi)"
    };

    private ArrayList<Biseccion> values;

    public BiseccionModel() {
        values = new ArrayList<Biseccion>();
    }

    public BiseccionModel(ArrayList<Biseccion> values) {
        this.values = values;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return values.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Biseccion biseccion = getBiseccion(rowIndex);

        switch (columnIndex) {
            case 0 -> {
                return biseccion.getI();
            }
            case 1 -> {
                return biseccion.getA();
            }
            case 2 -> {
                return biseccion.getB();
            }
            case 3 -> {
                return biseccion.getXi();
            }
            case 4 -> {
                return biseccion.getError();
            }
            case 5 -> {
                return biseccion.getFa();
            }
            case 6 -> {
                return biseccion.getFxi();
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Biseccion biseccion = getBiseccion(rowIndex);

        switch (columnIndex) {
            case 0 -> biseccion.setI((Integer) value);
            case 1 -> biseccion.setA((Double) value);
            case 2 -> biseccion.setB((Double) value);
            case 3 -> biseccion.setXi((Double) value);
            case 4 -> biseccion.setError((Double) value);
            case 5 -> biseccion.setFa((Double) value);
            case 6 -> biseccion.setFxi((Double) value);
        }

        fireTableCellUpdated(rowIndex, columnIndex);

    }

    public Biseccion getBiseccion(int row) {
        return values.get(row);
    }
}
