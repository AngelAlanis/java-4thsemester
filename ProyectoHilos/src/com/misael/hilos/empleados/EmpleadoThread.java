package com.misael.hilos.empleados;


import java.util.Random;

public class EmpleadoThread implements Runnable {

    Thread hilo;
    String nombreEmpleado;
    NPC    npc;
    Random random = new Random();

    public EmpleadoThread(NPC npc, String nombreEmpleado) {
        this.npc = npc;
        this.nombreEmpleado = nombreEmpleado;
        hilo     = new Thread(this, nombreEmpleado);
        hilo.start();
    }

    @Override
    public void run() {
        int timeBetweenResources = random.nextInt(1000) + 2000;
        int resourcesGotten;
        while (hilo.isAlive()) {
            if (npc.isWorking) {
                try {
                    Thread.sleep(timeBetweenResources);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resourcesGotten = random.nextInt(10) + 3;
                System.out.println(hilo.getName() + " New resource: " + resourcesGotten);
                npc.totalResources += resourcesGotten;
                System.out.println(hilo.getName() + " Total resources:" + npc.totalResources);
            }

        }
    }
}
