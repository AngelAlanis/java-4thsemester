package com.farmacia;

import com.formdev.flatlaf.FlatClientProperties;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class VentanaPrincipal extends JFrame {

    ImageIcon iconoCambiarUsuario = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/usuario.png")));
    ImageIcon iconoVentas = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/ventas.png")));
    ImageIcon iconoAceptar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/aceptar.png")));
    ImageIcon iconoInventario = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/inventario.png")));
    ImageIcon iconoRegistro = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/registro.png")));
    ImageIcon iconoBuscar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/buscar.png")));
    ImageIcon iconoSalir = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/salir.png")));
    ImageIcon iconoAgregar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/agregar.png")));
    ImageIcon iconoEliminar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/eliminar.png")));
    ImageIcon iconoModificar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/modificar.png")));
    ImageIcon iconoCobrar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/cobrar.png")));
    ImageIcon iconoCancelar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/cancelar.png")));
    ImageIcon imagenLogo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/logoFarmacia.png")));

    JCheckBox checkBoxFiltro = new JCheckBox();

    JTabbedPane tabbedPane = new JTabbedPane();

    ArrayList<Producto> productos = new ArrayList<>();

    JPanel panelPrincipal = new JPanel();
    JPanel panelHora = new JPanel();
    JPanel panelUsuario = new JPanel();
    JPanel panelEncabezado = new JPanel();
    JPanel panelTablaVentas = new JPanel();
    JPanel panelTablaInventario = new JPanel();
    JPanel panelTablaRegistro = new JPanel();
    JPanel panelPie = new JPanel();

    int permisos;

    int productosActuales;

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
    JLabel labelNumProductos = new JLabel("0 productos en la venta actual");

    JButton btnUsuario = new JButton("Cajero", iconoCambiarUsuario);
    JButton btnBuscar = new JButton("Buscar");
    JButton btnModificar = new JButton("Modificar");
    JButton btnEliminarVenta = new JButton("Eliminar");
    JButton btnEliminarInventario = new JButton("Eliminar");
    JButton btnCobrar = new JButton("Cobrar");
    JButton btnAgregar = new JButton("Agregar");
    JButton btnSalir = new JButton("Salir");

    File file = new File("ProyectoFarmacia/src/resources/vesa_pharmacy.xlsx");

    FileInputStream inputStream = new FileInputStream(file);
    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

    public VentanaPrincipal(int permisos) throws Exception {
        this.permisos = permisos;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1366, 728);
        setLocationRelativeTo(null);
        setResizable(true);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
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
        tabbedPane.setIconAt(0, iconoVentas);
        if (permisos == 10) {
            tabbedPane.add("Inventario", panelTablaInventario);
            tabbedPane.add("Registro de ventas", panelTablaRegistro);
            tabbedPane.setIconAt(1, iconoInventario);
            tabbedPane.setIconAt(2, iconoRegistro);
            btnUsuario.setText("Admin");
        }
        JToolBar trailing = new JToolBar();
        trailing.setFloatable(false);
        trailing.setBorder(null);
        btnSalir.setIcon(iconoSalir);
        trailing.add(Box.createHorizontalGlue());
        trailing.add(btnSalir);

        tabbedPane.putClientProperty(FlatClientProperties.TABBED_PANE_TRAILING_COMPONENT, trailing);

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

        btnSalir.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (respuesta == 0) {
                System.exit(0);
            }
        });

        btnAgregar.addActionListener(e -> new VentanaAgregar());

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

            btnCancelar.addActionListener(e13 -> {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar?", "", JOptionPane.YES_NO_OPTION);
                if (respuesta == 0) {
                    frameBusqueda.dispose();
                }
            });

            frameBusqueda.setLocationRelativeTo(null);
            frameBusqueda.setVisible(true);
        });

        btnCobrar.addActionListener(e -> {
            FrameCobrar frameCobrar = new FrameCobrar();
            float total = Float.parseFloat(labelPrecioTotal.getText().replace("$", ""));

            frameCobrar.pedirCantidad(productos, total);

        });

        btnEliminarVenta.addActionListener(e -> {
            if (tableVenta.getSelectedRow() >= 0) {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el producto de la venta actual?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (respuesta == 0) {
                    float importe = Float.parseFloat(tableVenta.getValueAt(tableVenta.getSelectedRow(), 4).toString().replace("$", ""));
                    int productosMenos = Integer.parseInt(tableVenta.getValueAt(tableVenta.getSelectedRow(), 3).toString().replace("$", ""));
                    float nuevoPrecio = Float.parseFloat(labelPrecioTotal.getText().replace("$", "")) - importe;
                    tableModelVentas.removeRow(tableVenta.getSelectedRow());
                    labelPrecioTotal.setText("$" + nuevoPrecio);
                    productosActuales -= productosMenos;
                    labelNumProductos.setText(productosActuales + " productos en la venta actual.");
                }
            }
        });

        btnEliminarInventario.addActionListener(e -> {
            System.out.println("ping");
            if (tableInventario.getSelectedRow() >= 0) {
                System.out.println("pong");
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el producto del inventario?\nEsta acción no se puede deshacer", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (respuesta == 0) {
                    XSSFSheet sheet = workbook.getSheetAt(1);
                    XSSFRow row = sheet.getRow(tableInventario.getSelectedRow() + 1);
                    if (sheet.getLastRowNum() > 0) {
                        sheet.removeRow(row);
                        //sheet.shiftRows(tableInventario.getSelectedRow() + 1, sheet.getLastRowNum(), -1 );
                    }


                    tableModelInventario.removeRow(tableInventario.getSelectedRow());

                    try {
                        FileOutputStream outputStream = new FileOutputStream(file);
                        workbook.write(outputStream);
                        outputStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Producto eliminado del inventario");
                }
            }
        });

        btnModificar.addActionListener(e -> {
            JFrame frameModificar = new JFrame();
            JTextField tfDescripcion = new JTextField();
            JTextField tfPrecio = new JTextField();
            JTextField tfStock = new JTextField();
            JLabel labelTitulo = new JLabel("Modificar producto");
            JLabel labelDescripcion = new JLabel("Descripción");
            JLabel labelPrecio = new JLabel("Precio");
            JLabel labelStock = new JLabel("Productos en existencia");
            JButton btnAceptar = new JButton("Aceptar");
            JButton btnCancelar = new JButton("Cancelar");

            frameModificar.setSize(450, 550);
            frameModificar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frameModificar.setResizable(false);
            frameModificar.setLocationRelativeTo(null);
            frameModificar.setLayout(null);

            btnAceptar.setIcon(iconoAceptar);
            btnCancelar.setIcon(iconoCancelar);

            labelTitulo.setFont(new Font("Myriad Pro", Font.BOLD, 20));
            tfDescripcion.setText(tableInventario.getValueAt(tableInventario.getSelectedRow(), 1).toString());
            tfPrecio.setText(tableInventario.getValueAt(tableInventario.getSelectedRow(), 2).toString().replace("$", ""));
            tfStock.setText(tableInventario.getValueAt(tableInventario.getSelectedRow(), 3).toString());
            String folio = tableInventario.getValueAt(tableInventario.getSelectedRow(), 0).toString();
            int selectedRow = tableInventario.getSelectedRow();


            labelTitulo.setBounds(60, 50, 200, 30);
            labelDescripcion.setBounds(60, 100, 300, 30);
            tfDescripcion.setBounds(60, 140, 300, 30);
            labelPrecio.setBounds(60, 180, 300, 30);
            tfPrecio.setBounds(60, 220, 300, 30);
            labelStock.setBounds(60, 260, 300, 30);
            tfStock.setBounds(60, 300, 300, 30);
            btnAceptar.setBounds(60, 380, 150, 30);
            btnCancelar.setBounds(240, 380, 150, 30);

            frameModificar.add(labelTitulo);
            frameModificar.add(tfDescripcion);
            frameModificar.add(tfPrecio);
            frameModificar.add(tfStock);
            frameModificar.add(btnAceptar);
            frameModificar.add(btnCancelar);
            frameModificar.add(labelDescripcion);
            frameModificar.add(labelPrecio);
            frameModificar.add(labelStock);

            frameModificar.setVisible(true);

            btnAceptar.addActionListener(e15 -> {
                String descripcion = tfDescripcion.getText();
                float precioVenta = Float.parseFloat(tfPrecio.getText());
                int stock = Integer.parseInt(tfStock.getText());

                if (precioVenta == 0) {
                    int confirmacion = JOptionPane.showConfirmDialog(null, "Está a punto de poner el precio del producto en $0, ¿Está seguro?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (confirmacion == 1) {
                        return;
                    }
                } else if (precioVenta < 0 || stock < 0) {
                    JOptionPane.showMessageDialog(null, "No se pueden ingresar valores negativos", "", JOptionPane.WARNING_MESSAGE);
                    return;
                }

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
                    FileOutputStream outputStream = new FileOutputStream(file);
                    workbook.write(outputStream);
                    outputStream.close();
                    frameModificar.dispose();
                    JOptionPane.showMessageDialog(null, "Dato actualizado correctamente!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return;
                }

                tableModelInventario.setValueAt(descripcion, selectedRow, 1);
                tableModelInventario.setValueAt("$" + precioVenta, selectedRow, 2);
                tableModelInventario.setValueAt(stock, selectedRow, 3);


            });

            btnCancelar.addActionListener(e13 -> {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar?", "", JOptionPane.YES_NO_OPTION);
                if (respuesta == 0) {
                    frameModificar.dispose();
                }
            });
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
                case 2 -> {
                    panelPie.removeAll();
                    panelPie.revalidate();
                    panelPie.repaint();
                }
            }
        });

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
        btnEliminarVenta.setPreferredSize(new Dimension(120, 70));
        btnEliminarVenta.setIcon(iconoEliminar);
        panelBotonesPie.add(btnBuscar);
        panelBotonesPie.add(btnEliminarVenta);
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
        btnEliminarInventario.setPreferredSize(new Dimension(120, 70));
        btnAgregar.setIcon(iconoAgregar);
        btnModificar.setIcon(iconoModificar);
        btnEliminarInventario.setIcon(iconoEliminar);

        panelBotonesPie.add(btnAgregar);
        panelBotonesPie.add(btnModificar);
        panelBotonesPie.add(btnEliminarInventario);

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
        productos.add(new Producto(folio, descripcion, precioVenta, cantidad, importe, stock));
        productosActuales += cantidad;
        labelNumProductos.setText(productosActuales + " productos en la venta actual.");
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
