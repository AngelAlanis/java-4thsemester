package com.misael.hilos;

public class HilosSincronizados implements Runnable {

    String nombreHilo;
    Thread hilo;

    public HilosSincronizados(String nombreHilo) {
        hilo            = new Thread(this, nombreHilo);
        this.nombreHilo = nombreHilo;
        hilo.run();
    }

    synchronized void cicloNumeros() {
        System.out.println("Inició:\t" + hilo.getName());
        for (int i = 1; i <= 5; i++) {
            System.out.println(hilo.getName() + ": " + i);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Terminó:\t" + hilo.getName());
    }

    synchronized void cicloLetras() {
        System.out.println("Inició:\t" + hilo.getName());
        for (int i = 1; i <= 5; i++) {
            System.out.println(hilo.getName() + ": " + (char) (i + 96));
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Terminó:\t" + hilo.getName());
    }

    @Override
    public void run() {
        switch (hilo.getName()) {
            case "Hilo 1", "Hilo 2" -> cicloNumeros();
            case "Hilo 3", "Hilo 4" -> cicloLetras();
            default -> System.out.println("Hilo no encontrado");
        }

    }
}
