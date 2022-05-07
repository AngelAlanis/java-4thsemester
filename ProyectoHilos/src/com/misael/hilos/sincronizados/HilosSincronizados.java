package com.misael.hilos.sincronizados;

import javax.swing.*;

public class HilosSincronizados implements Runnable {

    String                   nombreHilo;
    Thread                   hilo;
    DefaultListModel<String> modeloUno;
    DefaultListModel<String> modeloDos;
    DefaultListModel<String> modeloTres;
    DefaultListModel<String> modeloCuatro;

    InterfazCuatroHilos interfaz;


    public HilosSincronizados(String nombreHilo, InterfazCuatroHilos interfaz) {
        hilo            = new Thread(this, nombreHilo);
        this.nombreHilo = nombreHilo;
        this.interfaz   = interfaz;
        hilo.start();
    }

    public synchronized void cicloNumeros() {
        modeloUno = new DefaultListModel<>();
        modeloDos = new DefaultListModel<>();
        for (int i = 1; i <= 5; i++) {

            if (hilo.getName().equals("Hilo 1")) {
                modeloUno.addElement(String.valueOf(i));
                interfaz.listaHilo1.setModel(modeloUno);
            } else if (hilo.getName().equals("Hilo 2")) {
                modeloDos.addElement(String.valueOf(i));
                interfaz.listaHilo2.setModel(modeloDos);
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(hilo.getName() + ": " + i);
        }
    }

    public synchronized void cicloLetras() {
        modeloTres   = new DefaultListModel<>();
        modeloCuatro = new DefaultListModel<>();

        for (int i = 1; i <= 5; i++) {

            if (hilo.getName().equals("Hilo 3")) {
                modeloTres.addElement(String.valueOf((char) (i + 96)));
                interfaz.listaHilo3.setModel(modeloTres);
            } else if (hilo.getName().equals("Hilo 4")) {
                modeloCuatro.addElement(String.valueOf((char) (i + 96)));
                interfaz.listaHilo4.setModel(modeloCuatro);
            }

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(hilo.getName() + ": " + (char) (i + 96));
        }
    }

    @Override
    public void run() {
        synchronized (interfaz) {
            switch (hilo.getName()) {
                case "Hilo 1", "Hilo 2" -> cicloNumeros();
                case "Hilo 3", "Hilo 4" -> cicloLetras();
                default -> System.out.println("Hilo no encontrado");
            }
        }

    }
}
