package com.farmacia;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class LoginScreen extends JFrame {

    VentanaPrincipal ventanaPrincipal;
    static boolean loginIsValid = false;
    JPanel panelPrincipal = new JPanel();

    JLabel         labelIngreso       = new JLabel("Ingreso");
    JLabel         labelInstrucciones = new JLabel("Ingrese su usuario y contraseña para continuar");
    JLabel         labelUser          = new JLabel("Usuario");
    JTextField     tfUser             = new JTextField(20);
    JLabel         labelPassword      = new JLabel("Contraseña");
    JPasswordField tfPassword         = new JPasswordField(20);
    ImageIcon      imagenLogo         = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/logoSinTexto.png")));

    JLabel  labelLogo = new JLabel(imagenLogo);
    JButton btnLogin  = new JButton("Ingresar");

    String userAdmin        = "admin";
    String passwordAdmin    = "admin";
    String userEmpleado     = "cajero";
    String passwordEmpleado = "cajero";

    public LoginScreen() {
        setSize(450, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        configurarComponentes();
        setContentPane(panelPrincipal);
        setVisible(true);
        getRootPane().setDefaultButton(btnLogin);
    }

    public void configurarComponentes() {

        panelPrincipal.setLayout(null);

        labelLogo.setBounds(300, 10, 90, 90);
        labelIngreso.setFont(new Font("Myriad Pro", Font.BOLD, 20));
        labelIngreso.setBounds(60, 50, 100, 30);
        labelInstrucciones.setBounds(60, 80, 300, 30);
        labelUser.setBounds(60, 150, 100, 30);
        tfUser.setBounds(60, 190, 330, 30);
        labelPassword.setBounds(60, 230, 100, 30);
        tfPassword.setBounds(60, 270, 330, 30);
        btnLogin.setBounds(60, 350, 330, 30);

        panelPrincipal.add(labelLogo);
        panelPrincipal.add(labelIngreso);
        panelPrincipal.add(labelInstrucciones);
        panelPrincipal.add(labelUser);
        panelPrincipal.add(tfUser);
        panelPrincipal.add(labelPassword);
        panelPrincipal.add(tfPassword);
        panelPrincipal.add(btnLogin);

        tfPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        btnLogin.addActionListener(e -> {
            int permisos = isLoginValid();

            if (permisos == 10 || permisos == 1) {
                try {
                    dispose();
                    ventanaPrincipal = new VentanaPrincipal(permisos);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });
    }

    public int isLoginValid() {
        if (tfUser.getText().isEmpty() || String.valueOf(tfPassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "No deje espacios vacíos");
        } else if (tfUser.getText().equals(userAdmin) && String.valueOf(tfPassword.getPassword()).equals(passwordAdmin)) {
            return 10;
        } else if (tfUser.getText().equals(userEmpleado) && String.valueOf(tfPassword.getPassword()).equals(passwordEmpleado)) {
            return 1;
        } else {
            JOptionPane.showMessageDialog(null, "Datos incorrectos.");
        }
        return 0;
    }
}
