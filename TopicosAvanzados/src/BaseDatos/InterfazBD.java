package BaseDatos;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterfazBD extends JFrame {
    private JPanel     panelPrincipal;
    private JTextField tfNombre;
    private JTextField tfPuesto;
    private JTextField tfDepartamento;
    private JLabel     lbNombre;
    private JLabel     lbPuesto;
    private JLabel     lbDepartamento;
    private JButton    btnGuardar;
    private Conectar   conectar;
    private Connection registro;

    public InterfazBD() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 400);
        conectarBaseDatos();
        configurarComponentes();
        initActionListeners();
        this.setContentPane(panelPrincipal);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        InterfazBD interfazBD = new InterfazBD();
    }

    public void initActionListeners() {
        btnGuardar.addActionListener(e -> guardarUsuario());
    }

    private void guardarUsuario() {
        String nombre       = tfNombre.getText();
        String puesto       = tfPuesto.getText();
        String departamento = tfDepartamento.getText();
        String sqlQuerry    = "INSERT INTO empleado (nombre,puesto,departamento) VALUES (?,?,?)";

        try {
            PreparedStatement preparedStatement = registro.prepareStatement(sqlQuerry);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, puesto);
            preparedStatement.setString(3, departamento);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro guardado.");
            limpiar();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(InterfazBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void conectarBaseDatos() {
        conectar = new Conectar();
        registro = conectar.conexion();
    }

    private void limpiar() {
        tfNombre.setText("");
        tfPuesto.setText("");
        tfDepartamento.setText("");
    }

    public void configurarComponentes() {

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelPrincipal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tfNombre = new JTextField();
        panelPrincipal.add(tfNombre, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfPuesto = new JTextField();
        panelPrincipal.add(tfPuesto, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tfDepartamento = new JTextField();
        panelPrincipal.add(tfDepartamento, new GridConstraints(2, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lbNombre = new JLabel();
        lbNombre.setText("Nombre:");
        panelPrincipal.add(lbNombre, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbPuesto = new JLabel();
        lbPuesto.setText("Puesto:");
        panelPrincipal.add(lbPuesto, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbDepartamento = new JLabel();
        lbDepartamento.setText("Departamento:");
        panelPrincipal.add(lbDepartamento, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnGuardar = new JButton();
        btnGuardar.setText("Guardar");
        panelPrincipal.add(btnGuardar, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelPrincipal;
    }

}