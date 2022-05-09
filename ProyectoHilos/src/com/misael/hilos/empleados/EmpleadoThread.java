package com.misael.hilos.empleados;


import java.util.Random;

public class EmpleadoThread implements Runnable {

    Thread  hilo;
    String  nombreEmpleado;
    NPC     npc;
    Sound   sound;
    Random  random    = new Random();
    boolean isRunning = true;

    public EmpleadoThread(NPC npc, String nombreEmpleado, Sound sound) {
        this.npc            = npc;
        this.nombreEmpleado = nombreEmpleado;
        this.sound          = sound;
        hilo                = new Thread(this, nombreEmpleado);
        hilo.start();
    }

    public void playSFX(int i) {
        sound.setFile(i);
        sound.play();
    }

    public void stopThread() {
        isRunning     = false;
        npc.isWorking = false;
    }

    @Override
    public void run() {
        int timeBetweenResources = random.nextInt(1000) + 2000;
        int resourcesGotten1;
        int resourcesGotten2;

        while (isRunning) {

            System.out.println("is running");

            if (npc.isWorking) {

                try {
                    Thread.sleep(timeBetweenResources);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                switch (hilo.getName()) {
                    case "Clint" -> {
                        if (npc.image == npc.work1) {
                            resourcesGotten1 = random.nextInt(6) + 1;
                            npc.resources.totalGeode += resourcesGotten1;
                            playSFX(5);
                        }

                        if (npc.image == npc.work2) {
                            resourcesGotten2 = random.nextInt(3) + 1;
                            npc.resources.totalIron += resourcesGotten2;
                            playSFX(6);
                        }
                    }
                    case "Robin" -> {
                        if (npc.image == npc.work1) {
                            resourcesGotten1 = random.nextInt(9) + 1;
                            npc.resources.totalStone += resourcesGotten1;
                            playSFX(3);
                        }
                    }
                }
            }

        }
    }
}
