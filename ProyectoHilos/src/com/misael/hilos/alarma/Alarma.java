package com.misael.hilos.alarma;


public class Alarma implements Runnable {

    boolean isOnFire;
    boolean isRunning = true;

    public void stopThread() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isOnFire) {
                System.out.println("Is on fire");
            } else {
                System.out.println("Is not on fire");
            }

        }
    }
}
