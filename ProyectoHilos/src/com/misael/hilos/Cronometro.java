package com.misael.hilos;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Cronometro implements Runnable {

    int     milisCounter;
    boolean isPaused = false;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public void printCronometro(){
        Date tiempo = new Date(milisCounter);
        System.out.println(tiempo);
    }

    public void startCronometro() {
        while (!isPaused){
            try {
                Thread.sleep(10);
                if(milisCounter >= 99){
                    System.out.println("pasó un segundo");
                }
                milisCounter += 1;
                System.out.println(milisCounter);
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
        Cronometro hilo = new Cronometro();
        Thread cronometro = new Thread(hilo);
        cronometro.start();
    }
}
