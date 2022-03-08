import javax.swing.*;

public class PracticaCinco extends JFrame {

    public PracticaCinco () {
        super("Selección ITD");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        JLabel labelTitulo = new JLabel("Instituto Tecnológico de Durango");
        JLabel labelSelect1 = new JLabel("Selecciona tu carrera:");
        JLabel labelSelect2 = new JLabel("Selecciona tus materias:");

        JRadioButton opcSistemas = new JRadioButton("Ing. en Sistemas");
        JRadioButton opcInformatica = new JRadioButton("Ing. en Informática");
        JRadioButton opcTICS = new JRadioButton("Ing. en TICS");
        JRadioButton opcIndustrial = new JRadioButton("Ing. Industrial");
        JRadioButton opcElectronica = new JRadioButton("Ing. en Electrónica");

        JPanel panelCarreras = new JPanel();
        panelCarreras.setLayout(new BoxLayout(panelCarreras, BoxLayout.Y_AXIS));

        panelCarreras.add(opcSistemas);
        panelCarreras.add(opcInformatica);
        panelCarreras.add(opcTICS);
        panelCarreras.add(opcIndustrial);
        panelCarreras.add(opcElectronica);

        ButtonGroup opcionesCarreras = new ButtonGroup();

        opcionesCarreras.add(opcSistemas);
        opcionesCarreras.add(opcInformatica);
        opcionesCarreras.add(opcTICS);
        opcionesCarreras.add(opcIndustrial);
        opcionesCarreras.add(opcElectronica);

        JCheckBox opcCalculo = new JCheckBox("Cálculo Integral");
        JCheckBox opcTopicos = new JCheckBox("Tópicos avanzados de programación");
        JCheckBox opcFundamentos = new JCheckBox("Fundamentos de base de datos");
        JCheckBox opcWeb = new JCheckBox("Programación web");
        JCheckBox opcMoviles = new JCheckBox("Desarrollo de aplicaciones móviles");

        JPanel panelMaterias = new JPanel();
        panelMaterias.setLayout(new BoxLayout(panelMaterias, BoxLayout.Y_AXIS));
        panelMaterias.add(opcCalculo);
        panelMaterias.add(opcTopicos);
        panelMaterias.add(opcFundamentos);
        panelMaterias.add(opcWeb);
        panelMaterias.add(opcMoviles);

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        panelPrincipal.add(labelTitulo);
        panelPrincipal.add(labelSelect1);
        panelPrincipal.add(panelCarreras);
        panelPrincipal.add(labelSelect2);
        panelPrincipal.add(panelMaterias);
        panelPrincipal.add(panelBotones);

        setContentPane(panelPrincipal);
        pack();
        setVisible(true);

    }

    public static void main(String[] args) {
        PracticaCinco launcher = new PracticaCinco();

    }

}
