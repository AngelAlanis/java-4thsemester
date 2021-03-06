package com.misael.Mathematics;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;

public class SecanteModel extends AbstractTableModel {

    private String[] columnNames = {
            "i",
            "xi",
            "error",
            "f(xi)"
    };

    private ArrayList<Secante> values;

    public SecanteModel() {
        values = new ArrayList<Secante>();
    }

    public SecanteModel(ArrayList<Secante> values) {
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
        Secante secante = getSecante(rowIndex);

        switch (columnIndex) {
            case 0 -> {
                return secante.getI();
            }
            case 1 -> {
                return secante.getXi();
            }
            case 2 -> {
                return secante.getError();
            }
            case 3 -> {
                return secante.getFxi();
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Secante secante = getSecante(rowIndex);

        switch (columnIndex) {
            case 0 -> secante.setI((Integer) value);
            case 1 -> secante.setXi((Double) value);
            case 2 -> secante.setError((Double) value);
            case 3 -> secante.setFxi((Double) value);
        }

        fireTableCellUpdated(rowIndex, columnIndex);

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }

    public Secante getSecante(int row) {
        return values.get(row);
    }



}
