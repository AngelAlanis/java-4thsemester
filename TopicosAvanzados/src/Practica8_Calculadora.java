import javax.swing.*;
import java.awt.*;

public class Practica8_Calculadora extends JFrame {

    private JPanel panelBotones, panelInput, panelPrincipal;
    private JTextField tfInput;
    private JButton btnOFF, btnC, btnPorcentaje, btnRaiz, btnMas;
    private JButton btnMC, btn7, btn8, btn9, btnMenos;
    private JButton btnMR, btn4, btn5, btn6, btnDivision;
    private JButton btnMMas, btn1, btn2, btn3, btnPor;
    private JButton btnMMenos, btnMasMenos, btn0, btnPunto, btnIgual;

    public Practica8_Calculadora() {
        super("Calculadora aritmética");
        configurarVentana();
        configurarComponentes();
        setContentPane(panelPrincipal);
        setVisible(true);
    }

    public void configurarVentana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.setSize(350, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }

    public void configurarComponentes() {
        panelBotones = new JPanel();
        panelInput = new JPanel();
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelBotones.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        tfInput = new JTextField(25);

        btnOFF = new JButton("OFF");
        btnC = new JButton("C");
        btnPorcentaje = new JButton("%");
        btnRaiz = new JButton("Raiz");
        btnMas = new JButton("+");
        btnMC = new JButton("MC");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");
        btnMenos = new JButton("-");
        btnMR = new JButton("MR");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btnDivision = new JButton("/");
        btnMMas = new JButton("M+");
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btnPor = new JButton("X");
        btnMMenos = new JButton("-");
        btnMasMenos = new JButton("+/-");
        btn0 = new JButton("0");
        btnPunto = new JButton(".");
        btnIgual = new JButton("=");

        panelInput.add(tfInput);

        JButton[] botones =
                {btnOFF, btnC, btnPorcentaje, btnRaiz, btnMas,
                        btnMC, btn7, btn8, btn9, btnMenos,
                        btnMR, btn4, btn5, btn6, btnDivision,
                        btnMMas, btn1, btn2, btn3, btnPor,
                        btnMMenos, btnMasMenos, btn0, btnPunto, btnIgual};

        for (JButton botone : botones) {
            botone.setPreferredSize(new Dimension(60, 60));
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotones.add(btnOFF, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelBotones.add(btnC, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panelBotones.add(btnPorcentaje, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        panelBotones.add(btnRaiz, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        panelBotones.add(btnMas, gbc);
        //Linea 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelBotones.add(btnMC, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelBotones.add(btn7, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        panelBotones.add(btn8, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        panelBotones.add(btn9, gbc);
        gbc.gridx = 4;
        gbc.gridy = 1;
        panelBotones.add(btnMenos, gbc);
        //Linea 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelBotones.add(btnMR, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelBotones.add(btn4, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        panelBotones.add(btn5, gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        panelBotones.add(btn6, gbc);
        gbc.gridx = 4;
        gbc.gridy = 2;
        panelBotones.add(btnDivision, gbc);
        //Linea 4
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelBotones.add(btnMMas, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelBotones.add(btn1, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        panelBotones.add(btn2, gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        panelBotones.add(btn3, gbc);
        gbc.gridx = 4;
        gbc.gridy = 3;
        panelBotones.add(btnPor, gbc);
        //Linea 5
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelBotones.add(btnMMenos, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelBotones.add(btnMasMenos, gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        panelBotones.add(btn0, gbc);
        gbc.gridx = 3;
        gbc.gridy = 4;
        panelBotones.add(btnPunto, gbc);
        gbc.gridx = 4;
        gbc.gridy = 4;
        panelBotones.add(btnIgual, gbc);

        panelPrincipal.add(panelInput);
        panelPrincipal.add(panelBotones);

    }

    public static void main(String[] args) {
        Practica8_Calculadora launcher = new Practica8_Calculadora();
    }

}
