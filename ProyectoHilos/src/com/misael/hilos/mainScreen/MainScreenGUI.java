package com.misael.hilos.mainScreen;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.misael.hilos.SetupFile;
import com.misael.hilos.alarma.InterfazAlarma;
import com.misael.hilos.cronometro.InterfazCronometro;
import com.misael.hilos.empleados.LauncherEmpleados;
import com.misael.hilos.sincronizados.InterfazCuatroHilos;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

public class MainScreenGUI extends JFrame {

    private JPanel    panelMain;
    private JLabel    labelCronometro;
    private JLabel    labelTitle;
    private JLabel    labelAlarm;
    private JLabel    labelThreads;
    private JLabel    labelEmpleados;
    private ImageIcon iconAlarm;
    private ImageIcon iconThreads;
    private ImageIcon iconEmpleados;
    private ImageIcon iconCronometro;

    private SetupFile setup = new SetupFile();

    public MainScreenGUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(540, 760);
        this.setContentPane(panelMain);
        loadImages();
        initActionListeners();
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initComponents() {
        labelCronometro.setIcon(iconCronometro);
        labelAlarm.setIcon(iconAlarm);
        labelThreads.setIcon(iconThreads);
        labelEmpleados.setIcon(iconEmpleados);
    }

    public void initActionListeners() {
        labelAlarm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var interfaz = new InterfazAlarma();
            }
        });

        labelCronometro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var interfazCronometro = new InterfazCronometro();
            }
        });

        labelThreads.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var interfazCuatroHilos = new InterfazCuatroHilos();
            }
        });

        labelEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var launcher = new LauncherEmpleados();
            }
        });
    }

    public void loadImages() {
        iconCronometro = setup.imageIcon("/resources/misc/icon_stopwatch.png");
        iconAlarm      = setup.imageIcon("/resources/misc/icon_fire.png");
        iconThreads    = setup.imageIcon("/resources/misc/icon_threads.png");
        iconEmpleados  = setup.imageIcon("/resources/misc/icon_empleados.png");
    }

}
