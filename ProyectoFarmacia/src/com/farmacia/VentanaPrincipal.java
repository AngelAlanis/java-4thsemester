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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VentanaPrincipal extends JFrame {

    ImageIcon iconoCambiarUsuario = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/usuario.png")));
    ImageIcon iconoVentas         = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/ventas.png")));
    ImageIcon iconoInventario     = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/inventario.png")));
    ImageIcon iconoRegistro       = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/registro.png")));
    ImageIcon iconoBuscar         = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/buscar.png")));
    ImageIcon iconoSalir          = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/salir.png")));
    ImageIcon iconoAgregar        = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/agregar.png")));
    ImageIcon iconoEliminar       = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/eliminar.png")));
    ImageIcon iconoModificar      = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/modificar.png")));
    ImageIcon iconoCobrar         = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/cobrar.png")));
    ImageIcon imagenLogo          = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/logoFarmacia.png")));

    JTabbedPane tabbedPane = new JTabbedPane();

    ArrayList<Producto> productos    = new ArrayList<>();
    ArrayList<Integer>  posiciones   = new ArrayList<>();
    ArrayList<Integer>  numProductos = new ArrayList<>();

    JPanel panelPrincipal       = new JPanel();
    JPanel panelHora            = new JPanel();
    JPanel panelUsuario         = new JPanel();
    JPanel panelEncabezado      = new JPanel();
    JPanel panelTablaVentas     = new JPanel();
    JPanel panelTablaInventario = new JPanel();
    JPanel panelTablaRegistro   = new JPanel();
    JPanel panelPie             = new JPanel();

    int permisos;

    int productosActuales;

    DefaultTableModel tableModelVentas     = new DefaultTableModel();
    DefaultTableModel tableModelInventario = new DefaultTableModel();

    JTable        tableVenta            = new JTable(tableModelVentas);
    JTable        tableInventario       = new JTable(tableModelInventario);
    FileTestModel fileTestModelRegistro = new FileTestModel(getFiles("ProyectoFarmacia/src/resources/tickets"));
    JTable        tableRegistro         = new JTable(fileTestModelRegistro);

    JTextField tfBuscar = new JTextField(20);

    JScrollPane scrollVenta      = new JScrollPane(tableVenta);
    JScrollPane scrollInventario = new JScrollPane(tableInventario);
    JScrollPane scrollRegistro   = new JScrollPane(tableRegistro);

    String fechaActual = "03-03-2022";
    String horaActual  = "14:54:44";

    JLabel labelLogo         = new JLabel(imagenLogo);
    JLabel labelFecha        = new JLabel(fechaActual);
    JLabel labelHora         = new JLabel(horaActual);
    JLabel labelPrecioTotal  = new JLabel("$0");
    JLabel labelNumProductos = new JLabel("0 productos en la venta actual");

    JButton btnUsuario            = new JButton("Cajero", iconoCambiarUsuario);
    JButton btnBuscar             = new JButton("Buscar");
    JButton btnModificar          = new JButton("Modificar");
    JButton btnEliminarVenta      = new JButton("Eliminar");
    JButton btnEliminarInventario = new JButton("Eliminar");
    JButton btnCobrar             = new JButton("Cobrar");
    JButton btnAgregar            = new JButton("Agregar");
    JButton btnSalir              = new JButton("Salir");

    File fileRoute = new File("ProyectoFarmacia/src/resources/vesa_pharmacy.xlsx");

    FileInputStream inputStream = new FileInputStream(fileRoute);
    XSSFWorkbook    workbook    = new XSSFWorkbook(inputStream);

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
        initActionListeners();
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
        DateTimeFormatter formatoHora  = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime     now          = LocalDateTime.now();

        labelHora.setText(formatoHora.format(now));
        labelFecha.setText(formatoFecha.format(now));

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        tableInventario.getColumnModel().getColumn(0).setCellRenderer(dtcr);
        tableInventario.getColumnModel().getColumn(2).setCellRenderer(dtcr);
        tableInventario.getColumnModel().getColumn(3).setCellRenderer(dtcr);
        tableInventario.getColumnModel().getColumn(0).setMaxWidth(100);
    }

    public void initActionListeners() {
        btnSalir.addActionListener(e -> salirDelPrograma());

        btnAgregar.addActionListener(e -> new VentanaAgregar());

        btnBuscar.addActionListener(e -> new VentanaBusqueda(tableInventario, scrollInventario));

        btnCobrar.addActionListener(e -> cobrarVenta());

        btnEliminarVenta.addActionListener(e -> eliminarVenta());

        btnEliminarInventario.addActionListener(e -> eliminarDeInventario());

        btnModificar.addActionListener(e -> new VentanaModificar(tableInventario, fileRoute));

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
                    cargarPanelRegistro();
                }
            }
        });
    }

    private void salirDelPrograma() {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (respuesta == 0) {
            System.exit(0);
        }
    }

    private void cobrarVenta() {
        FrameCobrar frameCobrar = null;
        try {
            frameCobrar = new FrameCobrar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        float total = Float.parseFloat(labelPrecioTotal.getText().replace("$", ""));

        if (frameCobrar != null) {
            frameCobrar.pedirCantidad(productos, total);
        }

        try {
            descontarInventario(posiciones, numProductos);
            posiciones.clear();
            numProductos.clear();
            labelNumProductos.setText("0 productos en la venta actual");
            labelPrecioTotal.setText("$0");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void eliminarDeInventario() {
        if (tableInventario.getSelectedRow() >= 0) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el producto del inventario?\nEsta acción no se puede deshacer", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (respuesta == 0) {
                XSSFSheet sheet = workbook.getSheetAt(1);
                XSSFRow   row   = sheet.getRow(tableInventario.getSelectedRow() + 1);
                if (sheet.getLastRowNum() > 0) {
                    sheet.removeRow(row);
                    sheet.shiftRows(tableInventario.getSelectedRow() + 2, sheet.getLastRowNum(), -1);
                }

                tableModelInventario.removeRow(tableInventario.getSelectedRow());

                try {
                    FileOutputStream outputStream = new FileOutputStream(fileRoute);
                    workbook.write(outputStream);
                    outputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Producto eliminado del inventario");
            }
        }
    }

    private void eliminarVenta() {
        if (tableVenta.getSelectedRow() >= 0) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el producto de la venta actual?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (respuesta == 0) {
                float importe        = Float.parseFloat(tableVenta.getValueAt(tableVenta.getSelectedRow(), 4).toString().replace("$", ""));
                int   productosMenos = Integer.parseInt(tableVenta.getValueAt(tableVenta.getSelectedRow(), 3).toString().replace("$", ""));
                float nuevoPrecio    = Float.parseFloat(labelPrecioTotal.getText().replace("$", "")) - importe;
                tableModelVentas.removeRow(tableVenta.getSelectedRow());
                labelPrecioTotal.setText("$" + nuevoPrecio);
                productosActuales -= productosMenos;
                labelNumProductos.setText(productosActuales + " productos en la venta actual.");
            }
        }
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
        panelBuscar.add(new JLabel("Buscar"));
        panelBuscar.add(tfBuscar);

        tfBuscar.setText("");

        tfBuscar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String query = tfBuscar.getText();
                filtrarLista(query, tableInventario);
            }
        });

        filtrarLista("", tableInventario);

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
        getFiles("ProyectoFarmacia/src/resources/tickets");

        tableRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableRegistro.getSelectedRow();
                if (row >= 0 && e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    String archivo = String.valueOf(tableRegistro.getValueAt(row, 2));
                    System.out.println(archivo);
                    try {
                        Desktop.getDesktop().open(new File(archivo));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void ingresarProductoVentas(String folio, String descripcion, float precioVenta, int cantidad, float importe, int stock) {
        String[] columnas = new String[6];
        columnas[0] = folio;
        columnas[1] = descripcion;
        columnas[2] = "$" + precioVenta;
        columnas[3] = String.valueOf(cantidad);
        columnas[4] = "$" + importe;
        columnas[5] = String.valueOf(stock);
        tableModelVentas.addRow(columnas);
        productos.add(new Producto(folio, descripcion, precioVenta, cantidad, importe, stock));
        productosActuales += cantidad;
        labelNumProductos.setText(productosActuales + " productos en la venta actual.");
    }

    public void ingresarInventarioTabla(String folio, String descripcion, float precioVenta, int stock) throws IOException {
        String[] columnas = new String[4];
        columnas[0] = folio;
        columnas[1] = descripcion;
        columnas[2] = "$" + precioVenta;
        columnas[3] = String.valueOf(stock);
        tableModelInventario.addRow(columnas);
    }

    public List<ArchivoContenido> getFiles(String ruta) {
        List<ArchivoContenido> list = new ArrayList<>();
        try (Stream<Path> pathStream = Files.walk(Paths.get(ruta))) {
            list = pathStream.filter(Files::isRegularFile).map(Path::toFile).map(file -> new ArchivoContenido(file.getName(), new Date(file.lastModified()), file.getAbsolutePath())).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void escribirInventario(String folio, String descripcion, float precioVenta, int stock) throws IOException {
        XSSFSheet sheet      = workbook.getSheetAt(1);
        int       rowsInFile = sheet.getLastRowNum();

        XSSFRow row = sheet.createRow(++rowsInFile);

        XSSFCell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue(folio);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(descripcion);
        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue(precioVenta);
        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue(stock);

        FileOutputStream outputStream = new FileOutputStream(fileRoute);
        workbook.write(outputStream);
        outputStream.close();

        ingresarInventarioTabla(folio, descripcion, precioVenta, stock);
    }

    public void leerInventario() {
        XSSFSheet sheet = workbook.getSheetAt(1);
        try {
            int rowNum = sheet.getLastRowNum();

            String folio, descripcion;
            float  precioVenta;
            int    stock;

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

    public void descontarInventario(ArrayList<Integer> posiciones, ArrayList<Integer> numCompras) throws IOException {
        XSSFSheet sheet = workbook.getSheetAt(1);

        for (int i = 0; i < posiciones.size(); i++) {
            XSSFRow  row  = sheet.getRow(posiciones.get(i) + 1);
            XSSFCell cell = row.getCell(3);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(cell.getNumericCellValue() - numCompras.get(i));

            int newStock = Integer.parseInt(String.valueOf(tableModelInventario.getValueAt(posiciones.get(i), 3)));
            newStock -= numCompras.get(i);

            tableModelInventario.setValueAt(newStock, posiciones.get(i), 3);
        }

        FileOutputStream outputStream = new FileOutputStream(fileRoute);
        workbook.write(outputStream);
        outputStream.close();
    }

    public void filtrarLista(String busqueda, JTable table) {
        DefaultTableModel                 tableModel = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> trs        = new TableRowSorter<>(tableModel);
        table.setRowSorter(trs);

        trs.setRowFilter(RowFilter.regexFilter("(?i)" + busqueda));
    }

}
