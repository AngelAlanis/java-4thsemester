import javax.swing.*;
import java.awt.*;

public class PracticaDos extends JFrame {

    JLabel labelTitulo = new JLabel("Instituto Tecnológico de Durango");
    JTextField tFControl = new JTextField(20);
    JLabel labelControl = new JLabel("Control:");
    JTextField tFNombre = new JTextField(20);
    JLabel labelNombre = new JLabel("Nombre:");
    JTextField tfCarrera = new JTextField("Ingenieria en sistemas",20);
    JLabel labelCarrera = new JLabel("Carrera:");
    JTextField tfSemestre = new JTextField(20);
    JLabel labelSemestre = new JLabel("Semestre:");
    JButton btnInscribir = new JButton("Inscribir");
    JButton btnSalir = new JButton("Salir");

    public PracticaDos(){
        super("Cedula de inscripción");
        labelTitulo.setAlignmentX(CENTER_ALIGNMENT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelBotones = new JPanel();
        JPanel panelInputs = new JPanel();
        panelInputs.setLayout(new BoxLayout(panelInputs, BoxLayout.Y_AXIS));

        JPanel panelControl = new JPanel();
        panelControl.add(labelControl);
        panelControl.add(tFControl);

        JPanel panelNombre = new JPanel();
        panelNombre.add(labelNombre);
        panelNombre.add(tFNombre);

        JPanel panelCarrera = new JPanel();
        panelCarrera.add(labelCarrera);
        panelCarrera.add(tfCarrera);

        JPanel panelSemestre = new JPanel();
        panelSemestre.add(labelSemestre);
        panelSemestre.add(tfSemestre);

        panelBotones.add(btnInscribir);
        panelBotones.add(btnSalir);

        panelInputs.add(labelTitulo);
        panelInputs.add(panelControl);
        panelInputs.add(panelNombre);
        panelInputs.add(panelCarrera);
        panelInputs.add(panelSemestre);
        panelInputs.add(panelBotones);

        setContentPane(panelInputs);
        setLocationRelativeTo(null);
        setSize(340,300);
    }

    public static void main(String[] args) {
        PracticaDos frameMain = new PracticaDos();
        frameMain.setVisible(true);

    }
}
