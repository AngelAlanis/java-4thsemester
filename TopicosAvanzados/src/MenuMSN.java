import javax.swing.*;
import java.awt.*;

import static javax.swing.UIManager.setLookAndFeel;

public class MenuMSN extends JFrame {

    public MenuMSN() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super("Windows Live Messenger");
        getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        

        //Titulo
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
        JPanel panelTituloLinea2 = new JPanel();
        JLabel labelIniciarSesionEn = new JLabel("Iniciar sesión en");
        labelIniciarSesionEn.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelIniciarSesionEn.setFont(new Font("Candara Light", Font.PLAIN, 24));
        JLabel labelWindowsLive = new JLabel("Windows Live");
        labelWindowsLive.setFont(new Font("Candara Light", Font.PLAIN, 24));
        JLabel labelMessenger = new JLabel("Messenger");
        labelMessenger.setFont(new Font("Candara Light", Font.BOLD, 24));

        panelTituloLinea2.add(labelWindowsLive);
        panelTituloLinea2.add(labelMessenger);
        panelTitulo.add(labelIniciarSesionEn);
        panelTitulo.add(panelTituloLinea2);


        //Opciones inicio sesión
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));

        JTextField tfCorreoElectronico = new JTextField("ejemplo555@hotmail.com", 15);

        JPasswordField tfPassword = new JPasswordField("Contraseña", 15);
        JLabel labelContraseñaOlvidada = new JLabel("¿Has olvidado tu contraseña?");
        labelContraseñaOlvidada.setForeground(Color.BLUE);
        labelContraseñaOlvidada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel panelSeleccionSesion = new JPanel();
        panelSeleccionSesion.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel labelIniciarSesionComo = new JLabel("Iniciar sesión como:");
        String[] opciones = {"Disponible", "Ocupado", "Ausente", "Invisible"};
        JComboBox comboBoxInicioSesion = new JComboBox(opciones);
        panelSeleccionSesion.add(labelIniciarSesionComo);
        panelSeleccionSesion.add(comboBoxInicioSesion);

        JCheckBox checkBoxRecordar = new JCheckBox("Recordar mi Id. y contraseña");

        JPanel panelInicioAutomatico = new JPanel();
        panelInicioAutomatico.setLayout(new FlowLayout(FlowLayout.LEFT));
        JCheckBox checkBoxInicioAutomatico = new JCheckBox("Iniciar sesión automáticamente");
        JLabel labelOpciones = new JLabel("Opciones");
        labelOpciones.setForeground(Color.BLUE);
        labelOpciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelInicioAutomatico.add(checkBoxInicioAutomatico);
        panelInicioAutomatico.add(labelOpciones);

        JPanel panelBotones = new JPanel();
        JButton botonIniciarSesion = new JButton("Iniciar sesión");
        JButton botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonIniciarSesion);
        panelBotones.add(botonCancelar);

        JPanel panelRegistrarse = new JPanel();
        JLabel labelNoID = new JLabel("¿No tienes un Windows Live ID?");
        JLabel labelRegistrarse = new JLabel("Registrarse");
        labelRegistrarse.setForeground(Color.BLUE);
        labelRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelRegistrarse.add(labelNoID);
        panelRegistrarse.add(labelRegistrarse);

        tfCorreoElectronico.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelContraseñaOlvidada.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSeleccionSesion.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSeleccionSesion.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkBoxRecordar.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelInicioAutomatico.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBotones.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelRegistrarse.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelLogin.add(tfCorreoElectronico);
        panelLogin.add(tfPassword);
        panelLogin.add(labelContraseñaOlvidada);
        panelLogin.add(panelSeleccionSesion);
        panelLogin.add(checkBoxRecordar);
        panelLogin.add(panelInicioAutomatico);
        panelLogin.add(panelBotones);
        panelLogin.add(panelRegistrarse);


        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new BoxLayout(panelImagen, BoxLayout.Y_AXIS));
        ImageIcon imagenLogin = new ImageIcon("C:\\Users\\misae\\Downloads\\msn.png");
        Image image = imagenLogin.getImage();
        Image imagenReescalada = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon imagenFinal = new ImageIcon(imagenReescalada);
        JLabel labelImagen = new JLabel(imagenFinal);
        panelImagen.add(labelImagen);

        JPanel panelMedio = new JPanel();
        panelMedio.add(panelImagen);
        panelMedio.add(panelLogin);

        panelPrincipal.add(panelTitulo);
        panelPrincipal.add(panelMedio);

        setSize(590, 430);
        setContentPane(panelPrincipal);
        setVisible(true);

    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        MenuMSN launcher = new MenuMSN();
    }
}
