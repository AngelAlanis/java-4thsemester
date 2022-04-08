package EjemploHilos;

import java.util.Objects;

class ejercicio implements Runnable {
    public Thread hilo;
    String n_hilo;

    // Este es el metodo constructor
    ejercicio(String nombrehilo) {
        hilo = new Thread(this, nombrehilo);
        n_hilo = nombrehilo; //almacena el nombre del hilo
        System.out.println("Comienza " + hilo.getName() + " Iniciando");
        hilo.start();
    }

    // AQUI SE DEBE PONER LO QUE HACEN LOS HILOS
    public void run() {
        try {
            if (Objects.equals(n_hilo, "Sub_PROCESO1")) {
                for (int i = 1; i <= 15; i++)
                    System.out.println("Hilo1:" + i);
                Thread.sleep(1000);
                System.out.println("Terminando Hilo1");
            }
            if (Objects.equals(n_hilo, "Sub_PROCESO2")) {
                for (int i = 1; i <= 10; i++)
                    System.out.println("Hilo2:" + i);
                Thread.sleep(500);
                System.out.println("Terminando Hilo2");
            }
            if (Objects.equals(n_hilo, "Sub_PROCESO3")) {
                for (int i = 1; i <= 5; i++)
                    System.out.println("Hilo3:" + i);
                Thread.sleep(100);
                System.out.println("Terminando Hilo3");
            }
        } catch (InterruptedException exc) {
            System.out.println("Interrupcion en hilo " + hilo.getName());
        }
    }

    public static void main(String[] a) {
        ejercicio ob1 = new ejercicio("Sub_PROCESO1");
        ejercicio ob2 = new ejercicio("Sub_PROCESO2");
        ejercicio ob3 = new ejercicio("Sub_PROCESO3");

        ob1.hilo.setPriority(Thread.NORM_PRIORITY);
        ob2.hilo.setPriority(Thread.MIN_PRIORITY);
        ob3.hilo.setPriority(Thread.MAX_PRIORITY);
        try {
            ob1.hilo.join();
            ob2.hilo.join();
            ob3.hilo.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupcion en el main");
        }
        System.out.println("Termina main");
    }
}
