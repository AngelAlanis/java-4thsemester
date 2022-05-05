package com.misael.hilos.cronometro;

import java.time.format.DateTimeFormatter;

public class Cronometro implements Runnable {

    Thread hilo;
    int               milisegundos;
    int               segundos;
    int               minutos;
    boolean           isPaused = false;
    DateTimeFormatter dtf      = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public Cronometro(){
        hilo = new Thread(this, "Cronómetro");
        hilo.start();
    }

    public void printCronometro() {

        if(milisegundos >= 100){
            milisegundos = 0;
            segundos++;
        }

        if(segundos >= 60){
            segundos = 0;
            minutos++;
        }

        System.out.println(minutos + ":0" + segundos + ":" + milisegundos);
    }

    public void startCronometro() {
        while (!isPaused) {
            try {
                Thread.sleep(10);
                milisegundos += 1;
                printCronometro();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pauseCronometro() {
        hilo.suspend();
    }

    public void restartCronometro() {
        hilo.interrupt();
    }

    public void resumeCronometro(){
        hilo.resume();
    }

    @Override
    public void run() {
        System.out.println("run");
        startCronometro();
    }

    public static void main(String[] args) {
        Cronometro hilo       = new Cronometro();
        Thread     cronometro = new Thread(hilo);
        cronometro.start();
    }
}
