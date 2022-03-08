import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import com.formdev.flatlaf.FlatLightLaf;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;

public class PracticaTrece extends JFrame {

    String[] puestos = {"Gerente", "Supervisor", "Operador"};

    final String rutaArchivo = "ProyectosSemestre4/PracticaTrece/datos_trabajadores.xlsx";
    FileInputStream inputStream = new FileInputStream(rutaArchivo);
    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
    XSSFSheet sheet = workbook.getSheetAt(0);

    JPanel panelPrincipal = new JPanel();

    DefaultTableModel tableModel = new DefaultTableModel();
    JTable tableTrabajadores = new JTable(tableModel);
    JScrollPane jScrollPane = new JScrollPane(tableTrabajadores);

    JSeparator jSeparator = new JSeparator();

    JLabel labelEncabezado = new JLabel("Mi empresa S.A de C.V");
    JLabel labelLogo = new JLabel();
    JLabel labelHoras = new JLabel("Horas:");
    JLabel labelNombre = new JLabel("Nombre:");
    JLabel labelPuesto = new JLabel("Puesto:");

    JTextField tfNombre = new JTextField();
    JTextField tfHoras = new JTextField();
    JComboBox<String> cBPuestos = new JComboBox<>(puestos);

    JButton botonCalcular = new JButton("Agregar");
    JButton botonLimpiar = new JButton("Limpiar");

    public PracticaTrece() throws Exception {
        super("Nómina");
        configurarVentana();
        configurarComponentes();
        configurarLayout();
        setResizable(true);
        leerDatos();
        setVisible(true);
    }

    public void configurarLayout() {
        panelPrincipal.setLayout(null);

        labelEncabezado.setBounds(130, 10, 400, 20);

        labelLogo.setBounds(40, 10, 50, 50);

        labelNombre.setBounds(40, 70, 100, 20);
        tfNombre.setBounds(100, 70, 250, 20);

        labelPuesto.setBounds(40, 100, 100, 20);
        cBPuestos.setBounds(100, 100, 80, 20);

        labelHoras.setBounds(200, 100, 100, 20);
        tfHoras.setBounds(250, 100, 100, 20);

        botonCalcular.setBounds(250, 160, 100, 20);
        botonLimpiar.setBounds(100, 160, 100, 20);

        jSeparator.setBounds(0, 210, 400, 20);

        jScrollPane.setBounds(0, 240, 400, 150);

        panelPrincipal.add(jScrollPane);
        panelPrincipal.add(labelEncabezado);
        panelPrincipal.add(labelNombre);
        panelPrincipal.add(tfNombre);
        panelPrincipal.add(labelPuesto);
        panelPrincipal.add(cBPuestos);
        panelPrincipal.add(labelHoras);
        panelPrincipal.add(tfHoras);
        panelPrincipal.add(botonCalcular);
        panelPrincipal.add(jSeparator);
        panelPrincipal.add(botonLimpiar);
        panelPrincipal.add(labelLogo);
    }

    public void configurarComponentes() throws IOException {
        tableTrabajadores.setPreferredScrollableViewportSize(new Dimension(400, 150));
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Puesto");
        tableModel.addColumn("Horas trabajadas");
        tableModel.addColumn("Sueldo");

        labelEncabezado.setFont(new Font("Arial", Font.BOLD, 16));

        BufferedImage img = ImageIO.read(new URL("https://i.imgur.com/M22D3n5.png"));
        Image dimg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        labelLogo.setIcon(new javax.swing.ImageIcon(dimg));

        botonLimpiar.addActionListener(e -> {
            tfNombre.setText("");
            tfHoras.setText("");
            cBPuestos.setSelectedIndex(0);
        });

        botonCalcular.addActionListener(new ActionListener() {
            String nombreIngresado;
            int horasIngresadas;
            String puestoSeleccionado;
            int sueldoTotal;

            public void actionPerformed(ActionEvent e) {
                try {
                    nombreIngresado = tfNombre.getText();
                    horasIngresadas = Integer.parseInt(tfHoras.getText());
                    puestoSeleccionado = String.valueOf(cBPuestos.getSelectedItem());
                    if (nombreIngresado.trim().isEmpty()) {
                        throw new Exception();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Revise la información ingresada");
                    return;
                }

                sueldoTotal = horasIngresadas * 50;
                switch (cBPuestos.getSelectedIndex()) {
                    case 0 -> sueldoTotal += 150;
                    case 1 -> sueldoTotal += 100;
                    case 2 -> sueldoTotal += 50;
                }

                try {
                    ingresarDatos(nombreIngresado, puestoSeleccionado, horasIngresadas, sueldoTotal);
                    escribirDatos(nombreIngresado, puestoSeleccionado, horasIngresadas, sueldoTotal);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        FlatLightLaf.setup();
        PracticaTrece launcher = new PracticaTrece();
    }

    public void leerDatos() {

        try {
            int rowNum = sheet.getLastRowNum();

            String nombre, puesto;
            int horasTrabajadas, sueldo;

            for (int r = 1; r <= rowNum; r++) {
                XSSFRow rows = sheet.getRow(r);
                nombre = rows.getCell(0).getStringCellValue();
                puesto = rows.getCell(1).getStringCellValue();
                horasTrabajadas = (int) rows.getCell(2).getNumericCellValue();
                sueldo = (int) rows.getCell(3).getNumericCellValue();
                ingresarDatos(nombre, puesto, horasTrabajadas, sueldo);
            }

        } catch (NullPointerException e) {
            return;
        }
    }

    public void escribirDatos(String nombre, String puesto, int horasTrabajadas, int sueldo) throws IOException {
        int rowsInFile = sheet.getLastRowNum();

        XSSFRow row = sheet.createRow(++rowsInFile);

        XSSFCell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue(nombre);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(puesto);
        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue(horasTrabajadas);
        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue(sueldo);

        FileOutputStream outputStream = new FileOutputStream(rutaArchivo);
        workbook.write(outputStream);
        outputStream.close();
    }

    public void ingresarDatos(String nombre, String puesto, int horasTrabajadas, int sueldo) {
        String[] opciones = new String[4];
        opciones[0] = nombre;
        opciones[1] = puesto;
        opciones[2] = String.valueOf(horasTrabajadas);
        opciones[3] = String.valueOf(sueldo);
        tableModel.addRow(opciones);
    }

    public void configurarVentana() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setSize(450, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setContentPane(panelPrincipal);
    }


}
