package com.farmacia;

import javax.swing.table.AbstractTableModel;
import java.util.List;

class FileTestModel extends AbstractTableModel {

    private static final String[] COLUMNS = { "Num. Ticket", "Fecha", "Ubicación" };

    private final List<ArchivoContenido> contents;

    FileTestModel(List<ArchivoContenido> contents) {
        this.contents = contents;
    }

    @Override
    public int getRowCount() { return contents.size(); }

    @Override
    public int getColumnCount() { return COLUMNS.length; }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> contents.get(rowIndex).getNombreArchivo();
            case 1 -> contents.get(rowIndex).getFecha();
            case 2 -> contents.get(rowIndex).getUbicacion();
            default -> null;
        };
    }

}