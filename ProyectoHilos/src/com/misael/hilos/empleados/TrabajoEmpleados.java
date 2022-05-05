package com.misael.hilos.empleados;

import javax.swing.*;

public class TrabajoEmpleados implements Runnable {

    String nombreEmpleado;
    Thread hilo;
    JLabel aspecto;

    static boolean isSupervisorHere;

    public TrabajoEmpleados(String nombreEmpleado) {
        hilo                = new Thread(this, nombreEmpleado);
        this.nombreEmpleado = nombreEmpleado;
        hilo.start();
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isSupervisorHere) {
                System.out.println("El supervisor está aquí");
            } else {
                System.out.println("El supervisor no está aquí");
            }
        }

    }
}
