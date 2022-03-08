import javax.swing.*;

public class PracticaTres extends JFrame {

    JLabel labelUser = new JLabel("Nombre de usuario:");
    JLabel labelPassword = new JLabel("Password de usuario:");
    JTextField textUser = new JTextField(15);
    JPasswordField fieldPassword = new JPasswordField(15);
    JLabel labelComentarios = new JLabel("Comentarios:");
    JTextArea textAreaComments = new JTextArea(8,15);

    public PracticaTres () {
        super("Registro usuarios");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelUser = new JPanel();
        panelUser.add(labelUser);
        panelUser.add(textUser);

        JPanel panelPassword = new JPanel();
        panelPassword.add(labelPassword);
        panelPassword.add(fieldPassword);

        JPanel panelComentarios = new JPanel();
        panelComentarios.add(labelComentarios);
        panelComentarios.add(textAreaComments);

        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.add(panelUser);
        panelMain.add(panelPassword);
        panelMain.add(panelComentarios);

        setContentPane(panelMain);
        pack();
    }

    public static void main(String[] args) {
        PracticaTres launcher = new PracticaTres();
        launcher.setVisible(true);
    }

}
