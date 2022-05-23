import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;

import com.misael.Producto;
import com.misael.Cliente;

public class GUIProductoCliente extends JFrame {
    private JPanel     panelPrincipal;
    private JTextField tfNumSerie;
    private JTextField tfNombreProducto;
    private JTextField tfPrecio;
    private JLabel     labelProducto;
    private JLabel     labelNumSerie;
    private JLabel     labelPrecio;
    private JLabel     labelNombreProducto;
    private JTextField tfNumCuenta;
    private JTextField tfNombreCliente;
    private JTextField tfTelefono;
    private JTextArea  taInformacion;
    private JLabel     labelCliente;
    private JLabel     labelNumCuenta;
    private JLabel     labelNombreCliente;
    private JLabel     labelTelefono;
    private JPanel     PanelRegistros;
    private JPanel     panelCliente;
    private JPanel     panelProducto;
    private JPanel     panelInformacion;
    private JLabel     labelInformacion;
    private JButton    btnRegistrar;
    private JPanel     panelBoton;
    private JButton    btnAnterior;
    private JButton    btnSiguiente;

    ArrayList<Producto> listaProductos = new ArrayList<>();
    ArrayList<Cliente>  listaClientes  = new ArrayList<>();

    int currentProductoandCliente = 0;

    public GUIProductoCliente() {
        super("Interfaz Producto-Cliente");
        initActionListeners();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        listaClientes.add(new Cliente("4328432", "Juanito", "732473232849"));
        listaClientes.add(new Cliente("5443543", "Pepe", "32948723234"));
        listaClientes.add(new Cliente("9080909", "Martín", "73247353229"));
        listaProductos.add(new Producto("34298432", "Jabón", 23));
        listaProductos.add(new Producto("23478632", "Papel", 14));
        listaProductos.add(new Producto("3432432", "Cartón", 34));

    }

    public void initActionListeners() {
        btnRegistrar.addActionListener(e -> {
            registrarProducto();
            registrarCliente();
            JOptionPane.showMessageDialog(null, "Registro realizado correctamente");
            clearTextFields();

            if (currentProductoandCliente == 0) {
                taInformacion.setText(getCurrentObjectText());
            }
        });

        btnAnterior.addActionListener(e -> {
            currentProductoandCliente--;
            taInformacion.setText(getCurrentObjectText());
        });

        btnSiguiente.addActionListener(e -> {
            currentProductoandCliente++;
            taInformacion.setText(getCurrentObjectText());
        });
    }

    private String getCurrentObjectText() {
        String resultado = "";
        if (currentProductoandCliente >= 0 && currentProductoandCliente < listaClientes.size()) {
            resultado += "\nNombre del cliente: " + listaClientes.get(currentProductoandCliente).getNombre();
            resultado += "\nNúmero de cliente: " + listaClientes.get(currentProductoandCliente).getNumCliente();
            resultado += "\nTeléfono: " + listaClientes.get(currentProductoandCliente).getTelefono();
            resultado += "\nNúmero de serie del producto: " + listaProductos.get(currentProductoandCliente).getNumSerie();
            resultado += "\nNombre del producto: " + listaProductos.get(currentProductoandCliente).getNombre();
            resultado += "\nPrecio: $" + listaProductos.get(currentProductoandCliente).getPrecio();
        }
        return resultado;
    }

    private void registrarProducto() {
        String numSerie       = tfNumSerie.getText();
        String nombreProducto = tfNombreProducto.getText();
        float  precio         = Float.parseFloat(tfPrecio.getText());

        listaProductos.add(new Producto(numSerie, nombreProducto, precio));
    }

    private void registrarCliente() {
        String numCuenta     = tfNumCuenta.getText();
        String nombreCliente = tfNombreCliente.getText();
        String telefono      = tfTelefono.getText();

        listaClientes.add(new Cliente(numCuenta, nombreCliente, telefono));
    }

    public void clearTextFields() {
        tfNumSerie.setText("");
        tfNombreProducto.setText("");
        tfPrecio.setText("");
        tfNumCuenta.setText("");
        tfNombreCliente.setText("");
        tfTelefono.setText("");
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        new GUIProductoCliente();
    }

}
