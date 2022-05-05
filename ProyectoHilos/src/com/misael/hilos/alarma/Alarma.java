package com.misael.hilos.alarma;


public class Alarma implements Runnable {

    static boolean isOnFire;

    @Override
    public void run() {
        while(true){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(isOnFire){
                System.out.println("Is on fire");
            } else {
                System.out.println("Is not on fire");
            }

        }
    }
}
