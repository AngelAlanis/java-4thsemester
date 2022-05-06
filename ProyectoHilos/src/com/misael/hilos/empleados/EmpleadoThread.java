package com.misael.hilos.empleados;


import java.util.Random;

public class EmpleadoThread implements Runnable {

    Thread hilo;
    String nombreEmpleado;
    NPC    npc;
    Sound  sound  = new Sound();
    Random random = new Random();

    public EmpleadoThread(NPC npc, String nombreEmpleado) {
        this.npc            = npc;
        this.nombreEmpleado = nombreEmpleado;
        hilo                = new Thread(this, nombreEmpleado);
        hilo.start();
    }

    public void playSFX(int i) {
        sound.setFile(i);
        sound.play();
    }

    @Override
    public void run() {
        int timeBetweenResources = random.nextInt(1000) + 2000;
        int resourcesGotten1;
        int resourcesGotten2;
        while (hilo.isAlive()) {
            if (npc.isWorking) {
                try {
                    Thread.sleep(timeBetweenResources);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                switch (hilo.getName()) {
                    case "Clint" -> {
                        if (npc.image == npc.work1) {
                            resourcesGotten1 = random.nextInt(6);
                            npc.resources.totalGeode += resourcesGotten1;
                            playSFX(0);
                        }

                        if (npc.image == npc.work2) {
                            resourcesGotten2 = random.nextInt(3);
                            npc.resources.totalIron += resourcesGotten2;
                            playSFX(0);
                        }
                    }
                    case "Robin" -> {
                        if (npc.image == npc.work1) {
                            resourcesGotten1 = random.nextInt(9);
                            npc.resources.totalStone += resourcesGotten1;
                            playSFX(4);
                        }
                    }
                }


            }

        }
    }
}
