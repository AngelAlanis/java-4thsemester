package com.misael.hilos;

import javax.naming.ServiceUnavailableException;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Cronometro implements Runnable {

    int               milisCounter;
    boolean           isPaused = false;
    DateTimeFormatter dtf      = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public void printCronometro() {
        int segundos = (int) (milisCounter / 1000) % 60;
        int minutos = (int) (milisCounter / (1000*60) % 60);


        System.out.println(minutos + ":0" + segundos + ":" + milisCounter);
    }

    public void startCronometro() {
        while (!isPaused) {
            try {
                Thread.sleep(10);
                milisCounter += 1;
                printCronometro();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pauseCronometro() {

    }

    public void restartCronometro() {

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
