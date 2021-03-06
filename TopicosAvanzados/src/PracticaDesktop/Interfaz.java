package PracticaDesktop;

import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class Interfaz extends JFrame {

    private       JMenuBar       jMenuBar;
    private       JMenuItem      menuVentana1;
    private       JMenuItem      menuVentana2;
    private       JPanel         panelPrincipal;
    private       JDesktopPane   desktopPane;
    private       JInternalFrame internalFrameVentana1;
    private       JInternalFrame internalFrameVentana2;
    private       JButton        botonHilo;
    private       JButton        botonHilos;
    public static JList<String>  listaHiloUno;
    public static JList<String>  listaHiloDos;

    public static void main(String[] args) {
        var interfaz = new Interfaz();
    }

    public Interfaz() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(640, 480);
        this.setLocationRelativeTo(null);
        makeDesktop();
        makeVentana1();
        makeVentana2();
        this.getContentPane().add(desktopPane);
        desktopPane.add(internalFrameVentana1);
        desktopPane.add(internalFrameVentana2);
        configurarComponentes();
        initActionListeners();
        this.setJMenuBar(jMenuBar);
        this.setVisible(true);
    }

    public void configurarComponentes() {
        jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("Menú");
        menuVentana1  = new JMenuItem("Ventana 1");
        menuVentana2  = new JMenuItem("Ventana 2");
        JMenuItem menuItemSalir = new JMenuItem("Salir");

        jMenu.add(menuVentana1);
        jMenu.add(menuVentana2);
        jMenu.addSeparator();
        jMenu.add(menuItemSalir);

        jMenuBar.add(jMenu);
    }

    public void initActionListeners() {
        menuVentana1.addActionListener(e -> internalFrameVentana1.setVisible(true));

        menuVentana2.addActionListener(e -> internalFrameVentana2.setVisible(true));

        botonHilo.addActionListener(e -> {
            var hilo_1 = new UnHilo("hilo_1");
        });

        botonHilos.addActionListener(e -> {
            var hilo_1 = new UnHilo("hilo_1");
            var hilo_2 = new UnHilo("hilo_2");
        });
    }

    public void makeDesktop() {
        desktopPane = new JDesktopPane();
    }

    public void makeVentana2() {
        listaHiloUno = new JList<>();
        listaHiloDos = new JList<>();
        botonHilos   = new JButton("Ejecutar hilos");

        JScrollPane scrollListaUno = new JScrollPane(listaHiloUno);
        JScrollPane scrollListaDos = new JScrollPane(listaHiloDos);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        internalFrameVentana2 = new JInternalFrame("Ventana 2", true, true, true, true);
        internalFrameVentana2.setTitle("Ventana 2");
        internalFrameVentana2.setBounds(100, 100, 450, 300);
        internalFrameVentana2.setLayout(new GridBagLayout());

        internalFrameVentana2.add(scrollListaUno, gbc);
        gbc.gridx = 1;
        internalFrameVentana2.add(scrollListaDos, gbc);
        gbc.gridy = 1;
        internalFrameVentana2.add(botonHilos, gbc);

    }

    public void makeVentana1() {
        listaHiloUno   = new JList<>();
        botonHilo      = new JButton("Hilo");

        JScrollPane scrollPaneList = new JScrollPane(listaHiloUno);
        JLabel labelInternalFrame = new JLabel("Estoy en la ventana 1");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        internalFrameVentana1 = new JInternalFrame("Internal Frame", true, true, true, true);
        internalFrameVentana1.setTitle("Ventana 1");
        internalFrameVentana1.setBounds(100, 100, 450, 300);
        internalFrameVentana1.setLayout(new GridBagLayout());

        internalFrameVentana1.add(labelInternalFrame, gbc);
        gbc.gridy = 1;
        internalFrameVentana1.add(scrollPaneList, gbc);
        gbc.gridy = 2;
        internalFrameVentana1.add(botonHilo, gbc);
    }

}
