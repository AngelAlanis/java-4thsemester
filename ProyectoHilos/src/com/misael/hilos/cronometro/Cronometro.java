package com.misael.hilos.cronometro;

public class Cronometro implements Runnable {

    InterfazCronometro interfazCronometro;
    Thread             hilo;
    int                milisegundos;
    int                segundos;
    int                minutos;
    boolean            isPaused = false;

    public Cronometro(InterfazCronometro interfazCronometro) {
        this.interfazCronometro = interfazCronometro;
        hilo                    = new Thread(this, "Cronómetro");
        hilo.start();
    }

    public void updateTime() {

        milisegundos += 1;

        if (milisegundos >= 100) {
            milisegundos = 0;
            segundos++;
        }

        if (segundos >= 60) {
            segundos = 0;
            minutos++;
        }
    }

    public void startCronometro() {
        while (!isPaused) {
            try {
                Thread.sleep(10);
                updateTime();
                interfazCronometro.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    public void pauseCronometro() {
//        hilo.suspend();
//    }
//
//    public void restartCronometro() {
//        hilo.interrupt();
//    }
//
//    public void resumeCronometro(){
//        hilo.resume();
//    }

    @Override
    public void run() {
        startCronometro();
    }

}
