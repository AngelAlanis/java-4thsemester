package com.misael.Mathematics;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class NewtonRaphsonModel extends AbstractTableModel {

    private String[] columnNames = {
            "i",
            "xi",
            "error",
            "f(xi)",
            "f'(xi)"
    };

    private ArrayList<NewtonRaphson> values;

    public NewtonRaphsonModel() {
        values = new ArrayList<NewtonRaphson>();
    }

    public NewtonRaphsonModel(ArrayList<NewtonRaphson> values) {
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
        NewtonRaphson newtonRaphson = getNewtonRaphson(rowIndex);

        switch (columnIndex) {
            case 0 -> {
                return newtonRaphson.getI();
            }
            case 1 -> {
                return newtonRaphson.getXi();
            }
            case 2 -> {
                return newtonRaphson.getError();
            }
            case 3 -> {
                return newtonRaphson.getFxi();
            }
            case 4 -> {
                return newtonRaphson.getDfxi();
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        NewtonRaphson newtonRaphson = getNewtonRaphson(rowIndex);

        switch (columnIndex) {
            case 0 -> newtonRaphson.setI((Integer) value);
            case 1 -> newtonRaphson.setXi((Double) value);
            case 2 -> newtonRaphson.setError((Double) value);
            case 3 -> newtonRaphson.setFxi((Double) value);
            case 4 -> newtonRaphson.setDfxi((Double) value);
        }

        fireTableCellUpdated(rowIndex, columnIndex);

    }

    public NewtonRaphson getNewtonRaphson(int row) {
        return values.get(row);
    }
}
