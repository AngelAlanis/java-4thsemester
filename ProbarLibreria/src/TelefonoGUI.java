import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import com.misael.Telefono;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Objects;

public class TelefonoGUI extends JFrame {
    private JTextField tfNumSerie;
    private JTextField tfNumSerieOut;
    private JTextField tfMarcaOut;
    private JTextField tfPantallaOut;
    private JTextField tfMemoriaOut;
    private JButton    btnGuardar;
    private JButton    btnMostrar;
    private JLabel     labelNumSerie;
    private JLabel     labelMarca;
    private JLabel     labelPantalla;
    private JLabel     labelNumSerieOut;
    private JLabel     labelMarcaOut;
    private JLabel     labelPantallaOut;
    private JLabel     labelMemoria;
    private JLabel     labelMemoriaOut;
    private JPanel     panelPrincipal;

    private final String[] listaPantallas = {"14 inch", "15 inch", "17 inch"};
    private final String[] listaMemorias  = {"4GB", "8GB", "16GB", "32GB", "64GB", "128GB"};
    private final String[] listaMarcas    = {"Samsung", "Xiaomi", "Apple", "Huawei"};

    private JComboBox<String> cbMemoria;
    private JComboBox<String> cbPantalla;
    private JButton           btnSiguiente;
    private JButton           btnAnterior;
    private JPanel            panelBotonesMostrar;
    private JPanel            panelBotonGuardar;
    private JComboBox<String> cbMarca;

    int currentTelefono = 0;

    ArrayList<Telefono> listaTelefonos = new ArrayList<>();

    public TelefonoGUI(String title) {
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setResizable(false);
        configurarComponentes();
        initActionListeners();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void configurarComponentes() {
        cbMemoria.setModel(new DefaultComboBoxModel<>(listaMemorias));
        cbPantalla.setModel(new DefaultComboBoxModel<>(listaPantallas));
        cbMarca.setModel(new DefaultComboBoxModel<>(listaMarcas));
    }

    public void initActionListeners() {
        btnGuardar.addActionListener(e -> {
            String numSerie = tfNumSerie.getText();
            String marca    = Objects.requireNonNull(cbMarca.getSelectedItem()).toString();
            String memoria  = Objects.requireNonNull(cbMemoria.getSelectedItem()).toString();
            String pantalla = Objects.requireNonNull(cbPantalla.getSelectedItem()).toString();

            listaTelefonos.add(new Telefono(numSerie, marca, memoria, pantalla));

            JOptionPane.showMessageDialog(null, "Teléfono registrado correctamente.");

            clearTextFields();

        });

        btnMostrar.addActionListener(e -> printCurrentAlumno());


        btnSiguiente.addActionListener(e1 -> {
            currentTelefono++;
            printCurrentAlumno();
        });

        btnAnterior.addActionListener(e12 -> {
            currentTelefono--;
            printCurrentAlumno();
        });

    }

    private void clearTextFields() {
        tfNumSerie.setText("");
        cbMarca.setSelectedIndex(0);
        cbMemoria.setSelectedIndex(0);
        cbPantalla.setSelectedIndex(0);
    }

    private void printCurrentAlumno() {
        if (currentTelefono >= 0 && currentTelefono < listaTelefonos.size()) {
            tfNumSerieOut.setText(listaTelefonos.get(currentTelefono).getNumeroSerie());
            tfMarcaOut.setText(listaTelefonos.get(currentTelefono).getMarca());
            tfMemoriaOut.setText(listaTelefonos.get(currentTelefono).getMemoria());
            tfPantallaOut.setText(listaTelefonos.get(currentTelefono).getPantalla());
        }
    }
}
