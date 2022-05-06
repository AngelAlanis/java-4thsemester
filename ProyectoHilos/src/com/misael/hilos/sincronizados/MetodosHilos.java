package com.misael.hilos.sincronizados;

public class MetodosHilos{

    public static void main(String[] args) {
        new MetodosHilos().hilosSincronizados();
    }

    synchronized void hilosSincronizados() {
        HilosSincronizados hilo1 = new HilosSincronizados("Hilo 1");
        HilosSincronizados hilo2 = new HilosSincronizados("Hilo 2");
        HilosSincronizados hilo3 = new HilosSincronizados("Hilo 3");
        HilosSincronizados hilo4 = new HilosSincronizados("Hilo 4");

        try {
            hilo1.hilo.join();
            hilo2.hilo.join();
            hilo3.hilo.join();
            hilo4.hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
