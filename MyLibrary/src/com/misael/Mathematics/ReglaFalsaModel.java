package com.misael.Mathematics;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ReglaFalsaModel extends AbstractTableModel {

    private String[] columnNames = {
            "i",
            "a",
            "b",
            "f(a)",
            "f(b)",
            "xi",
            "error",
            "f(xi)"
    };

    private List<ReglaFalsa> values;

    public ReglaFalsaModel() {
        values = new ArrayList<ReglaFalsa>();
    }

    public ReglaFalsaModel(List<ReglaFalsa> values) {
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
        ReglaFalsa reglaFalsa = getReglaFalsa(rowIndex);

        switch (columnIndex) {
            case 0 -> {
                return reglaFalsa.getA();
            }
            case 1 -> {
                return reglaFalsa.getB();
            }
            case 2 -> {
                return reglaFalsa.getFa();
            }
            case 3 -> {
                return reglaFalsa.getFb();
            }
            case 4 -> {
                return reglaFalsa.getXi();
            }
            case 5 -> {
                return reglaFalsa.getError();
            }
            case 6 -> {
                return reglaFalsa.getFxi();
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex){
        ReglaFalsa reglaFalsa = getReglaFalsa(rowIndex);

        switch (columnIndex) {
            case 0 -> reglaFalsa.setA((Double) value);
            case 1 -> reglaFalsa.setB((Double) value);
            case 2 -> reglaFalsa.setFa((Double) value);
            case 3 -> reglaFalsa.setFb((Double) value);
            case 4 -> reglaFalsa.setXi((Double) value);
            case 5 -> reglaFalsa.setError((Double) value);
            case 6 -> reglaFalsa.setFxi((Double) value);
        }

        fireTableCellUpdated(rowIndex, columnIndex);

    }

    public ReglaFalsa getReglaFalsa(int row) {
        return values.get(row);
    }
}
