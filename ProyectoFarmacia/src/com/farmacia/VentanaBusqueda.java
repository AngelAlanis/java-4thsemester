package com.farmacia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class VentanaBusqueda extends JFrame {

    JPanel panelSuperior = new JPanel();
    JPanel panelTabla = new JPanel();
    JPanel panelBotones = new JPanel();
    JTextField tfBusqueda = new JTextField();
    JButton btnAgregar = new JButton("Agregar");
    JButton btnCancelar = new JButton("Cancelar");
    JTextField tfCantidad = new JTextField();

    ImageIcon iconoAgregar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/agregar.png")));
    ImageIcon iconoCancelar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icons/cancelar.png")));

    private final JTable tableInventario;
    private final JScrollPane scrollInventario;

    public VentanaBusqueda(JTable tableInventario, JScrollPane scrollInventario) {
        this.tableInventario = tableInventario;
        this.scrollInventario = scrollInventario;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(500, 600);
        this.setResizable(false);
        configureComponents();
        initActionListeners();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initActionListeners() {
        btnAgregar.addActionListener(e1 -> {
            try {
                String folio = tableInventario.getValueAt(tableInventario.getSelectedRow(), 0).toString();
                String descripcion = tableInventario.getValueAt(tableInventario.getSelectedRow(), 1).toString();
                float precioVenta = Float.parseFloat(tableInventario.getValueAt(tableInventario.getSelectedRow(), 2).toString().replace("$", ""));
                int stock = Integer.parseInt(tableInventario.getValueAt(tableInventario.getSelectedRow(), 3).toString().replace("$", ""));

                if (isDataValid(stock)) {
                    int cantidad = Integer.parseInt(tfCantidad.getText());
                    float importe = (cantidad * precioVenta);
                    Launcher.loginScreen.ventanaPrincipal.ingresarProductoVentas(folio, descripcion, precioVenta, cantidad, importe, stock);
                    float nuevoPrecio = Float.parseFloat(Launcher.loginScreen.ventanaPrincipal.labelPrecioTotal.getText().replace("$", "")) + importe;
                    Launcher.loginScreen.ventanaPrincipal.labelPrecioTotal.setText("$" + nuevoPrecio);
                    this.dispose();

                    Launcher.loginScreen.ventanaPrincipal.posiciones.add(tableInventario.getSelectedRow());
                    Launcher.loginScreen.ventanaPrincipal.numProductos.add(Integer.parseInt(tfCantidad.getText()));
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Solo se pueden introducir números.");
            }
        });

        btnCancelar.addActionListener(e13 -> {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar?", "", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {
                this.dispose();
            }
        });

        tfBusqueda.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                Launcher.loginScreen.ventanaPrincipal.filtrarLista(tfBusqueda.getText(), tableInventario);
            }
        });
    }

    private boolean isDataValid(int stock) {
        if (Integer.parseInt(tfCantidad.getText()) > stock) {
            JOptionPane.showMessageDialog(null, "No se pueden agregar más productos de los que están disponibles");
            return false;
        } else if (Integer.parseInt(tfCantidad.getText()) < 0) {
            JOptionPane.showMessageDialog(null, "No se pueden introducir números negativos.");
            return false;
        }
        return true;
    }

    public void configureComponents() {
        Launcher.loginScreen.ventanaPrincipal.filtrarLista("", tableInventario);

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

        this.add(panelSuperior, BorderLayout.NORTH);
        this.add(panelTabla, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);
    }

}
