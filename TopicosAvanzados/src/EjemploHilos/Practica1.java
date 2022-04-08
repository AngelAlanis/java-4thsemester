package EjemploHilos;

public class Practica1 implements Runnable {
    Thread hilo;
    String nombreHilo;

    public Practica1(String nombreHilo) {
        hilo = new Thread(this, nombreHilo);
        this.nombreHilo = nombreHilo;
        hilo.start();
    }

    public static void main(String[] args) {
        var primer_hilo  = new Practica1("PRIMER HILO");
        var segundo_hilo = new Practica1("SEGUNDO HILO");

        primer_hilo.hilo.setPriority(1);
        segundo_hilo.hilo.setPriority(10);

        try {
            primer_hilo.hilo.join();
            segundo_hilo.hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fin de la ejecución");


    }

    @Override
    public void run() {
        System.out.println("Comienza hilo " + hilo.getName());
        int randomSleepTime;
        try {
            for (int i = 1; i < 10; i++) {
                randomSleepTime = (int) Math.floor(Math.random() * 3000) + 1000;
                System.out.println("A dormir " + hilo.getName() + " durante " + (randomSleepTime / 1000) + " segundos.");
                Thread.sleep(randomSleepTime);
            }
            System.out.println("Fin del hilo " + hilo.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Interrupción ");
        }
    }
}
