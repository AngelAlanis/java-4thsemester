package com.misael.hilos.sincronizados;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.Locale;

public class InterfazCuatroHilos extends JFrame {
    private JPanel        panelMain;
    public  JList<String> listaHilo1;
    public  JList<String> listaHilo2;
    public  JList<String> listaHilo3;
    public  JList<String> listaHilo4;
    private JScrollPane   spHilo1;
    private JScrollPane   spLista2;
    private JScrollPane   spLista3;
    private JScrollPane   spLista4;
    private JLabel        labelHilo1;
    private JLabel        labelHilo2;
    private JLabel        labelHilo3;
    private JLabel        labelHilo4;
    private JButton       btnCorrer;
    private JButton       btnLimpiar;

    public InterfazCuatroHilos() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1024, 576);
        this.setContentPane(panelMain);
        initComponentes();
        initActionListeners();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void startThread() {
        HilosSincronizados hilo1 = new HilosSincronizados("Hilo 1", this);
        HilosSincronizados hilo2 = new HilosSincronizados("Hilo 2", this);
        HilosSincronizados hilo3 = new HilosSincronizados("Hilo 3", this);
        HilosSincronizados hilo4 = new HilosSincronizados("Hilo 4", this);

        try {
            hilo1.hilo.join();
            hilo2.hilo.join();
            hilo3.hilo.join();
            hilo4.hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void initComponentes() {

    }

    public void initActionListeners() {
        btnCorrer.addActionListener(e -> startThread());
        btnLimpiar.addActionListener(e -> {
            listaHilo1.setModel(new DefaultListModel<>());
            listaHilo2.setModel(new DefaultListModel<>());
            listaHilo3.setModel(new DefaultListModel<>());
            listaHilo4.setModel(new DefaultListModel<>());
        });
    }

}
