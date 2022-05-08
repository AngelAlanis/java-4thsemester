package com.misael.hilos.cronometro;

public class Cronometro implements Runnable {

    InterfazCronometro interfazCronometro;
    Thread             hilo;
    int                horas;
    int                milisegundos;
    int                segundos;
    int                minutos;
    boolean            isStopped = true;
    boolean            isRunning = true;

    public Cronometro(InterfazCronometro interfazCronometro) {
        this.interfazCronometro = interfazCronometro;
        hilo                    = new Thread(this, "Cronómetro");
        hilo.start();
    }

    public void updateTime() {

        if (!isStopped) {
            milisegundos += 1;
        }

        if (milisegundos >= 100) {
            milisegundos = 0;
            segundos++;
        }

        if (segundos >= 60) {
            segundos = 0;
            minutos++;
        }

        if (minutos >= 60) {
            minutos = 0;
            horas++;
        }
    }

    public void pauseCronometro() {
        isStopped = true;
    }

    public void stopCronometro() {
        isStopped    = true;
        minutos      = 0;
        segundos     = 0;
        milisegundos = 0;
        interfazCronometro.update();
    }

    public void resumeCronometro() {
        isStopped = false;
    }

    public void stopThread() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {

            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!isStopped) {
                updateTime();
                interfazCronometro.update();
            }

        }

    }

}
