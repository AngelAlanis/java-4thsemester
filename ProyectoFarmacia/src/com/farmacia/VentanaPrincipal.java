package com.farmacia;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class VentanaPrincipal extends JFrame {

    ImageIcon iconoCambiarUsuario = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/usuario.png")));
    ImageIcon iconoVentas = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/ventas.png")));
    ImageIcon iconoInventario = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/inventario.png")));
    ImageIcon iconoRegistro = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/registro.png")));
    ImageIcon iconoBuscar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/buscar.png")));
    ImageIcon iconoAgregar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/agregar.png")));
    ImageIcon iconoEliminar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/eliminar.png")));
    ImageIcon iconoModificar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/modificar.png")));
    ImageIcon iconoCobrar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/cobrar.png")));
    ImageIcon iconoCancelar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/cancelar.png")));
    ImageIcon imagenLogo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/logoFarmacia.png")));

    JCheckBox checkBoxFiltro = new JCheckBox();

    JTabbedPane tabbedPane = new JTabbedPane();

    JPanel panelPrincipal = new JPanel();
    JPanel panelHora = new JPanel();
    JPanel panelUsuario = new JPanel();
    JPanel panelEncabezado = new JPanel();
    JPanel panelTablaVentas = new JPanel();
    JPanel panelTablaInventario = new JPanel();
    JPanel panelTablaRegistro = new JPanel();
    JPanel panelPie = new JPanel();


    DefaultTableModel tableModelVentas = new DefaultTableModel();
    DefaultTableModel tableModelInventario = new DefaultTableModel();
    DefaultTableModel tableModelRegistro = new DefaultTableModel();

    JTable tableVenta = new JTable(tableModelVentas);
    JTable tableInventario = new JTable(tableModelInventario);
    JTable tableRegistro = new JTable(tableModelRegistro);

    JTextField tfBuscar = new JTextField(20);

    JScrollPane scrollVenta = new JScrollPane(tableVenta);
    JScrollPane scrollInventario = new JScrollPane(tableInventario);
    JScrollPane scrollRegistro = new JScrollPane(tableRegistro);

    String fechaActual = "03-03-2022";
    String horaActual = "14:54:44";

    JLabel labelLogo = new JLabel(imagenLogo);
    JLabel labelFecha = new JLabel(fechaActual);
    JLabel labelHora = new JLabel(horaActual);
    JLabel labelPrecioTotal = new JLabel("$0");
    JLabel labelNumProductos = new JLabel("X productos en la venta actual");

    JButton btnUsuario = new JButton("Admin", iconoCambiarUsuario);
    JButton btnBuscar = new JButton("Buscar");
    JButton btnModificar = new JButton("Modificar");
    JButton btnEliminar = new JButton("Eliminar");
    JButton btnCobrar = new JButton("Cobrar");
    JButton btnAgregar = new JButton("Agregar");
    JButton btnSalir = new JButton("Salir");

    File file = new File("ProyectosSemestre4/ProyectoFarmacia/src/resources/vesa_pharmacy.xlsx");

    FileInputStream inputStream = new FileInputStream(file);
    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);


    public VentanaPrincipal() throws Exception {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1366, 728);
        setLocationRelativeTo(null);
        setResizable(true);
        //setExtendedState(MAXIMIZED_BOTH);
        //setUndecorated(true);
        setContentPane(panelPrincipal);
        configurarLayout();
        configurarComponentes();
        setVisible(true);
        leerInventario();
    }

    public void configurarLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        //panelEncabezado
        labelLogo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panelUsuario.setLayout(new GridBagLayout());
        panelUsuario.setPreferredSize(new Dimension(150, 100));
        panelUsuario.setBorder(BorderFactory.createEtchedBorder());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelUsuario.add(new JLabel("Le atiende:"), gbc);
        gbc.gridy = 1;
        panelUsuario.add(btnUsuario, gbc);

        panelHora.setLayout(new BoxLayout(panelHora, BoxLayout.Y_AXIS));
        panelHora.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelFecha.setFont(new Font("Arial", Font.PLAIN, 14));
        labelHora.setFont(new Font("Arial", Font.PLAIN, 14));
        panelHora.add(labelFecha);
        panelHora.add(labelHora);

        panelEncabezado.setLayout(new BorderLayout());
        panelEncabezado.setBorder(BorderFactory.createEtchedBorder());
        JPanel panelContenido = new JPanel();
        panelContenido.add(panelHora);
        panelContenido.add(panelUsuario);
        panelEncabezado.add(labelLogo, BorderLayout.WEST);
        panelEncabezado.add(panelContenido, BorderLayout.EAST);


        //panelTabla

        tabbedPane.add("Ventas", panelTablaVentas);
        tabbedPane.add("Inventario", panelTablaInventario);
        tabbedPane.add("Registro de ventas", panelTablaRegistro);

        tabbedPane.setIconAt(0, iconoVentas);
        tabbedPane.setIconAt(1, iconoInventario);
        tabbedPane.setIconAt(2, iconoRegistro);

        panelTablaVentas.setLayout(new GridLayout());
        panelTablaVentas.setBorder(BorderFactory.createEtchedBorder());

        panelTablaInventario.setLayout(new GridLayout());
        panelTablaInventario.setBorder(BorderFactory.createEtchedBorder());

        panelTablaRegistro.setLayout(new GridLayout());
        panelTablaRegistro.setBorder(BorderFactory.createEtchedBorder());

        tableVenta.setShowHorizontalLines(true);
        tableVenta.setShowVerticalLines(true);
        tableVenta.setCellSelectionEnabled(false);
        tableVenta.setRowSelectionAllowed(true);
        tableVenta.getTableHeader().setReorderingAllowed(false);
        tableVenta.getTableHeader().setResizingAllowed(false);

        tableInventario.setShowHorizontalLines(true);
        tableInventario.setShowVerticalLines(true);
        tableInventario.setCellSelectionEnabled(false);
        tableInventario.setRowSelectionAllowed(true);
        tableInventario.getTableHeader().setReorderingAllowed(false);
        tableInventario.getTableHeader().setResizingAllowed(false);

        tableRegistro.setShowHorizontalLines(true);
        tableRegistro.setShowVerticalLines(true);
        tableRegistro.setCellSelectionEnabled(false);
        tableRegistro.setRowSelectionAllowed(true);
        tableRegistro.getTableHeader().setReorderingAllowed(false);
        tableRegistro.getTableHeader().setResizingAllowed(false);

        tableModelVentas.addColumn("Folio");
        tableModelVentas.addColumn("Descripción del producto");
        tableModelVentas.addColumn("Precio de venta");
        tableModelVentas.addColumn("Cantidad");
        tableModelVentas.addColumn("Importe");
        tableModelVentas.addColumn("Existencia");

        tableModelInventario.addColumn("Folio");
        tableModelInventario.addColumn("Descripción del producto");
        tableModelInventario.addColumn("Precio de venta");
        tableModelInventario.addColumn("Existencia");

        tableModelRegistro.addColumn("Folio");
        tableModelRegistro.addColumn("Descripción del producto");
        tableModelRegistro.addColumn("Precio de venta");
        tableModelRegistro.addColumn("Cantidad");
        tableModelRegistro.addColumn("Existencia");

        panelTablaVentas.add(scrollVenta);
        panelTablaInventario.add(scrollInventario);
        panelTablaRegistro.add(scrollRegistro);

        //panelPie

        panelPie.setLayout(new BorderLayout());
        panelPie.setBorder(BorderFactory.createEtchedBorder());
        cargarPanelVentas();

        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.add(panelEncabezado, BorderLayout.NORTH);
        panelPrincipal.add(tabbedPane, BorderLayout.CENTER);
        panelPrincipal.add(panelPie, BorderLayout.SOUTH);


    }

    public void configurarComponentes() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ISO_DATE;
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        labelHora.setText(formatoHora.format(now));
        labelFecha.setText(formatoFecha.format(now));

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        tableInventario.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        tableInventario.getColumnModel().getColumn(2).setCellRenderer(dtcr);
        tableInventario.getColumnModel().getColumn(3).setCellRenderer(dtcr);
        tableInventario.getColumnModel().getColumn(0).setMaxWidth(100);

        btnAgregar.addActionListener(e -> {
            VentanaAgregar ventanaAgregar = new VentanaAgregar();
        });

        btnBuscar.addActionListener(e -> {
            JFrame frameBusqueda = new JFrame();
            JPanel panelSuperior = new JPanel();
            JPanel panelTabla = new JPanel();
            JPanel panelBotones = new JPanel();
            JTextField tfBusqueda = new JTextField();
            JButton btnAgregar = new JButton("Agregar");
            JButton btnCancelar = new JButton("Cancelar");
            JTextField tfCantidad = new JTextField();

            frameBusqueda.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frameBusqueda.setLayout(new BorderLayout());
            frameBusqueda.setSize(500, 600);
            frameBusqueda.setResizable(false);

            panelSuperior.add(new JLabel("Búsqueda de productos"));
            panelSuperior.add(tfBusqueda);
            tfBusqueda.setPreferredSize(new Dimension(150, 30));

            panelTabla.add(scrollInventario);
            panelTabla.setLayout(new GridLayout());

            panelBotones.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridy = 0;
            gbc.gridx = 0;
            panelBotones.add(new JLabel("Cantidad"), gbc);
            gbc.gridx = 1;
            tfCantidad.setPreferredSize(new Dimension(120, 30));
            panelBotones.add(tfCantidad, gbc);
            btnAgregar.setIcon(iconoAgregar);
            btnCancelar.setIcon(iconoCancelar);
            btnAgregar.setPreferredSize(new Dimension(120, 70));
            btnCancelar.setPreferredSize(new Dimension(120, 70));
            gbc.gridy = 1;
            gbc.gridx = 0;
            panelBotones.add(btnAgregar, gbc);
            gbc.gridx = 1;
            panelBotones.add(btnCancelar, gbc);

            frameBusqueda.add(panelSuperior, BorderLayout.NORTH);
            frameBusqueda.add(panelTabla, BorderLayout.CENTER);
            frameBusqueda.add(panelBotones, BorderLayout.SOUTH);

            btnAgregar.addActionListener(e1 -> {
                try {
                    String folio = tableInventario.getValueAt(tableInventario.getSelectedRow(), 0).toString();
                    String descripcion = tableInventario.getValueAt(tableInventario.getSelectedRow(), 1).toString();
                    float precioVenta = Float.parseFloat(tableInventario.getValueAt(tableInventario.getSelectedRow(), 2).toString().replace("$", ""));
                    int stock = Integer.parseInt(tableInventario.getValueAt(tableInventario.getSelectedRow(), 3).toString().replace("$", ""));
                    if (Integer.parseInt(tfCantidad.getText()) > stock) {
                        JOptionPane.showMessageDialog(null, "No se pueden agregar más productos de los que están disponibles");
                    } else if (Integer.parseInt(tfCantidad.getText()) < 0) {
                        JOptionPane.showMessageDialog(null, "No se pueden introducir números negativos.");
                    } else {
                        int cantidad = Integer.parseInt(tfCantidad.getText());
                        float importe = (cantidad * precioVenta);
                        ingresarProductoVentas(folio, descripcion, precioVenta, cantidad, importe, stock);
                        float nuevoPrecio = Float.parseFloat(labelPrecioTotal.getText().replace("$", "")) + importe;
                        labelPrecioTotal.setText("$" + nuevoPrecio);
                        frameBusqueda.dispose();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Solo se pueden introducir números.");
                }
            });

            btnEliminar.addActionListener(e12 -> {
                if(tableVenta.getSelectedRow() >= 0){
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el producto de la venta actual?", "", JOptionPane.YES_NO_OPTION);
                    if(respuesta == 0) {
                        float importe = Float.parseFloat(tableVenta.getValueAt(tableVenta.getSelectedRow(), 4).toString().replace("$", ""));
                        float nuevoPrecio = Float.parseFloat(labelPrecioTotal.getText().replace("$", "")) - importe;
                        tableModelVentas.removeRow(tableVenta.getSelectedRow());
                        labelPrecioTotal.setText("$" + nuevoPrecio);
                    }
                }
            });

            btnCancelar.addActionListener(e13 -> {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar?", "", JOptionPane.YES_NO_OPTION);
                if (respuesta == 0) {
                    frameBusqueda.dispose();
                }
            });

            frameBusqueda.setLocationRelativeTo(null);
            frameBusqueda.setVisible(true);
        });

        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frameModificar = new JFrame();
                JTextField tfDescripcion = new JTextField();
                JTextField tfPrecio = new JTextField();
                JTextField tfStock = new JTextField();
                JLabel labelTitulo = new JLabel("Modificar producto");
                JButton btnAceptar = new JButton("Aceptar");
                JButton btnCancelar = new JButton("Cancelar");

                frameModificar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frameModificar.setLayout(null);
                frameModificar.setLocationRelativeTo(null);
                frameModificar.setSize(500, 600);
                frameModificar.setResizable(false);

                labelTitulo.setBounds(20, 50, 100, 30);

                frameModificar.add(labelTitulo);


                frameModificar.add(new JLabel("Modificar producto"));
                frameModificar.add(new JLabel("Descripción"));
                frameModificar.add(tfDescripcion);
                frameModificar.add(new JLabel("Precio de venta"));
                frameModificar.add(tfPrecio);
                frameModificar.add(new JLabel("Cantidad en inventario"));
                frameModificar.add(tfStock);
                frameModificar.add(btnAceptar);
                frameModificar.add(btnCancelar);

                frameModificar.setVisible(true);
            }
        });


        tabbedPane.addChangeListener(e -> {
            switch (tabbedPane.getSelectedIndex()) {
                case 0 -> {
                    panelPie.removeAll();
                    panelPie.revalidate();
                    panelPie.repaint();
                    cargarPanelVentas();
                }
                case 1 -> {
                    panelPie.removeAll();
                    panelPie.revalidate();
                    panelPie.repaint();
                    cargarPanelInventario();
                }
                case 2 -> System.out.println("Frame registro");

//                        panelPie.removeAll();
//                        panelPie.revalidate();
//                        panelPie.repaint();
//                        cargarPanelRegistro();
            }
        });

    }

    public void cargarPanelVentas() {
        JPanel panelCobrarPie = new JPanel();
        panelCobrarPie.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnCobrar.setPreferredSize(new Dimension(120, 70));
        btnCobrar.setIcon(iconoCobrar);
        panelCobrarPie.add(btnCobrar);
        labelPrecioTotal.setFont(new Font("Arial", Font.BOLD, 28));
        labelPrecioTotal.setForeground(new Color(17, 151, 219));
        labelPrecioTotal.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        panelCobrarPie.add(labelPrecioTotal);

        JPanel panelBotonesPie = new JPanel();
        panelBotonesPie.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnBuscar.setPreferredSize(new Dimension(120, 70));
        btnBuscar.setIcon(iconoBuscar);
//        btnModificar.setPreferredSize(new Dimension(120, 70));
//        btnModificar.setIcon(iconoModificar);
        btnEliminar.setPreferredSize(new Dimension(120, 70));
        btnEliminar.setIcon(iconoEliminar);
        panelBotonesPie.add(btnBuscar);
//        panelBotonesPie.add(btnModificar);
        panelBotonesPie.add(btnEliminar);
        labelNumProductos.setText("X productos en la venta actual");
        labelNumProductos.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        labelNumProductos.setFont(new Font("Arial", Font.ITALIC, 14));
        panelBotonesPie.add(labelNumProductos);

        panelPie.add(panelBotonesPie, BorderLayout.WEST);
        panelPie.add(panelCobrarPie, BorderLayout.EAST);
    }

    public void cargarPanelInventario() {
        JPanel panelBuscar = new JPanel();
        panelBuscar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelBuscar.add(new JLabel("Solo mostrar disponibles"));
        panelBuscar.add(checkBoxFiltro);
        checkBoxFiltro.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        panelBuscar.add(new JLabel("Buscar"));
        panelBuscar.add(tfBuscar);

        panelTablaInventario.add(scrollInventario);

        JPanel panelBotonesPie = new JPanel();
        panelBotonesPie.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnAgregar.setPreferredSize(new Dimension(120, 70));
        btnModificar.setPreferredSize(new Dimension(120, 70));
        btnEliminar.setPreferredSize(new Dimension(120, 70));
        btnAgregar.setIcon(iconoAgregar);
        btnModificar.setIcon(iconoModificar);
        btnEliminar.setIcon(iconoEliminar);

        panelBotonesPie.add(btnAgregar);
        panelBotonesPie.add(btnModificar);
        panelBotonesPie.add(btnEliminar);
        labelNumProductos.setText("X productos disponibles en inventario");
        labelNumProductos.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        labelNumProductos.setFont(new Font("Arial", Font.ITALIC, 14));
        panelBotonesPie.add(labelNumProductos);

        panelPie.add(panelBotonesPie, BorderLayout.WEST);
        panelPie.add(panelBuscar, BorderLayout.EAST);
    }

    public void cargarPanelRegistro() {

    }

    public void ingresarProductoVentas(String folio, String descripcion, float precioVenta, int cantidad, float importe, int stock) {
        String[] opciones = new String[6];
        opciones[0] = folio;
        opciones[1] = descripcion;
        opciones[2] = "$" + precioVenta;
        opciones[3] = String.valueOf(cantidad);
        opciones[4] = "$" + importe;
        opciones[5] = String.valueOf(stock);
        tableModelVentas.addRow(opciones);
    }

    public void ingresarInventarioTabla(String folio, String descripcion, float precioVenta, int stock) throws IOException {
        String[] opciones = new String[4];
        opciones[0] = folio;
        opciones[1] = descripcion;
        opciones[2] = "$" + precioVenta;
        opciones[3] = String.valueOf(stock);
        tableModelInventario.addRow(opciones);
    }

    public void escribirInventario(String folio, String descripcion, float precioVenta, int stock) throws IOException {
        System.out.println("entrada");
        XSSFSheet sheet = workbook.getSheetAt(1);
        int rowsInFile = sheet.getLastRowNum();

        XSSFRow row = sheet.createRow(++rowsInFile);

        XSSFCell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue(folio);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(descripcion);
        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue(precioVenta);
        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue(stock);

        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();

        ingresarInventarioTabla(folio, descripcion, precioVenta, stock);
    }

    public void leerInventario() {
        XSSFSheet sheet = workbook.getSheetAt(1);
        try {
            int rowNum = sheet.getLastRowNum();

            String folio, descripcion;
            float precioVenta;
            int stock;

            for (int r = 1; r <= rowNum; r++) {
                XSSFRow rows = sheet.getRow(r);
                folio = rows.getCell(0).getStringCellValue();
                descripcion = rows.getCell(1).getStringCellValue();
                precioVenta = (float) rows.getCell(2).getNumericCellValue();
                stock = (int) rows.getCell(3).getNumericCellValue();
                ingresarInventarioTabla(String.valueOf(folio), descripcion, precioVenta, stock);
            }

        } catch (NullPointerException e) {
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
