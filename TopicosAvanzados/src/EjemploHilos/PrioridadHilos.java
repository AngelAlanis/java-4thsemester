package EjemploHilos;

public class PrioridadHilos implements Runnable {

    Thread hilo;
    String nombreHilo;

    PrioridadHilos(String nombreHilo) {
        hilo = new Thread(this, nombreHilo);
        this.nombreHilo = nombreHilo;
        hilo.start();
    }

    @Override
    public void run() {
        System.out.println("Comienza " + hilo.getName() + " método run()");

        try {
            System.out.println("A dormir " + hilo.getName());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Interrupción en hilo");
        }

        System.out.println("Terminado " + hilo.getName());
    }

    public static void main(String[] args) {

        var subProceso0 = new PrioridadHilos("SUBPROCESO_0");
        var subProceso1 = new PrioridadHilos("SUBPROCESO_1");
        var subProceso2 = new PrioridadHilos("SUBPROCESO_2");
        var subProceso3 = new PrioridadHilos("SUBPROCESO_3");

        subProceso0.hilo.setPriority(Thread.NORM_PRIORITY);
        subProceso1.hilo.setPriority(Thread.MIN_PRIORITY);
        subProceso2.hilo.setPriority(Thread.NORM_PRIORITY);
        subProceso3.hilo.setPriority(Thread.MAX_PRIORITY);

        try {
            subProceso0.hilo.join();
            subProceso1.hilo.join();
            subProceso2.hilo.join();
            subProceso3.hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Interrupción en el main");
        }
        System.out.println("Termina main");
    }
}
