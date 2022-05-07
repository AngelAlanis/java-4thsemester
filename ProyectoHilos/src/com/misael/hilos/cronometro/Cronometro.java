package com.misael.hilos.cronometro;

public class Cronometro implements Runnable {

    InterfazCronometro interfazCronometro;
    Thread             hilo;
    int                horas;
    int                milisegundos;
    int                segundos;
    int                minutos;
    boolean            isStopped = true;

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

    public void startCronometro() {
        while (hilo.isAlive()) {
            if (!isStopped) {
                try {
                    Thread.sleep(8);
                    updateTime();
                    interfazCronometro.update();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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

    @Override
    public void run() {
        startCronometro();
    }

}
