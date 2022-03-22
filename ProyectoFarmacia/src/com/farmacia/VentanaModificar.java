package com.farmacia;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class VentanaModificar extends JFrame {

    JTextField tfDescripcion    = new JTextField();
    JTextField tfPrecio         = new JTextField();
    JTextField tfStock          = new JTextField();
    JLabel     labelTitulo      = new JLabel("Modificar producto");
    JLabel     labelDescripcion = new JLabel("Descripción");
    JLabel     labelPrecio      = new JLabel("Precio");
    JLabel     labelStock       = new JLabel("Productos en existencia");
    JButton    btnAceptar       = new JButton("Aceptar");
    JButton    btnCancelar      = new JButton("Cancelar");

    ImageIcon iconoAceptar  = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/aceptar.png")));
    ImageIcon iconoCancelar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/cancelar.png")));

    private final JTable tableInventario;
    private final File   fileRoute;
    private final int    selectedRow;

    String folio;

    public VentanaModificar(JTable tableInventario, File fileRoute) {
        this.tableInventario = tableInventario;
        this.fileRoute       = fileRoute;
        this.selectedRow     = tableInventario.getSelectedRow();
        this.setSize(450, 550);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        setTextFieldContent();
        configureComponents();
        initActionListeners();
        this.setVisible(true);
    }

    public void setTextFieldContent() {
        labelTitulo.setFont(new Font("Myriad Pro", Font.BOLD, 20));
        tfDescripcion.setText(tableInventario.getValueAt(tableInventario.getSelectedRow(), 1).toString());
        tfPrecio.setText(tableInventario.getValueAt(tableInventario.getSelectedRow(), 2).toString().replace("$", ""));
        tfStock.setText(tableInventario.getValueAt(tableInventario.getSelectedRow(), 3).toString());
        folio = tableInventario.getValueAt(tableInventario.getSelectedRow(), 0).toString();
    }

    public void configureComponents() {
        btnAceptar.setIcon(iconoAceptar);
        btnCancelar.setIcon(iconoCancelar);

        labelTitulo.setBounds(60, 50, 200, 30);
        labelDescripcion.setBounds(60, 100, 300, 30);
        tfDescripcion.setBounds(60, 140, 300, 30);
        labelPrecio.setBounds(60, 180, 300, 30);
        tfPrecio.setBounds(60, 220, 300, 30);
        labelStock.setBounds(60, 260, 300, 30);
        tfStock.setBounds(60, 300, 300, 30);
        btnAceptar.setBounds(60, 380, 150, 30);
        btnCancelar.setBounds(240, 380, 150, 30);

        this.add(labelTitulo);
        this.add(tfDescripcion);
        this.add(tfPrecio);
        this.add(tfStock);
        this.add(btnAceptar);
        this.add(btnCancelar);
        this.add(labelDescripcion);
        this.add(labelPrecio);
        this.add(labelStock);
    }

    public void initActionListeners() {
        btnAceptar.addActionListener(e -> {

            String descripcion = tfDescripcion.getText();
            float  precioVenta = Float.parseFloat(tfPrecio.getText());
            int    stock       = Integer.parseInt(tfStock.getText());

            if (isDataValid(precioVenta, stock)) {
                try {
                    writeDataInExcel(folio, descripcion, precioVenta, stock);
                } catch (IOException | InvalidFormatException ex) {
                    ex.printStackTrace();
                }
            }

        });

        btnCancelar.addActionListener(e13 -> {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar?", "", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {
                dispose();
            }
        });
    }

    public boolean isDataValid(float precioVenta, int stock) {
        if (precioVenta == 0) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "Está a punto de poner el precio del producto en $0, ¿Está seguro?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmacion == 1) {
                return true;
            }
        } else if (precioVenta < 0 || stock < 0) {
            JOptionPane.showMessageDialog(null, "No se pueden ingresar valores negativos", "", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void writeDataInExcel(String folio, String descripcion, float precioVenta, int stock) throws IOException, InvalidFormatException {
        FileInputStream inputStream = new FileInputStream(fileRoute);
        XSSFWorkbook    workbook    = new XSSFWorkbook(inputStream);

        XSSFSheet sheet = workbook.getSheetAt(1);

        XSSFRow row = sheet.getRow(selectedRow + 1);

        XSSFCell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue(folio);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(descripcion);
        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue(precioVenta);
        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue(stock);

        try {
            FileOutputStream outputStream = new FileOutputStream(fileRoute);
            workbook.write(outputStream);
            outputStream.close();
            dispose();

            writeDataInTable(descripcion, precioVenta, stock);

            JOptionPane.showMessageDialog(null, "Dato actualizado correctamente!");

        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
    }

    public void writeDataInTable(String descripcion, float precioVenta, int stock) {
        tableInventario.getModel().setValueAt(descripcion, selectedRow, 1);
        tableInventario.getModel().setValueAt("$" + precioVenta, selectedRow, 2);
        tableInventario.getModel().setValueAt(stock, selectedRow, 3);
    }

}
