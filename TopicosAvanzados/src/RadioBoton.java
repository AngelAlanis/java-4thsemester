import javax.swing.*;

public class RadioBoton extends JFrame {

    public RadioBoton () {
        super("Radio botones");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JRadioButton opc1 = new JRadioButton("Ing. Sistemas", false);
        JRadioButton opc2 = new JRadioButton("Ing. Informática", false);
        JRadioButton opc3 = new JRadioButton("Ing. Industrial", false);

        ButtonGroup groupOpc = new ButtonGroup();

        groupOpc.add(opc1);
        groupOpc.add(opc2);
        groupOpc.add(opc3);

        JPanel jPanel = new JPanel();
        jPanel.add(opc1);
        jPanel.add(opc2);
        jPanel.add(opc3);

        setContentPane(jPanel);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        RadioBoton launcher = new RadioBoton();
    }

}
