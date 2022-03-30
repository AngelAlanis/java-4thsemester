package EjemploHilos;

public class UnHilo implements Runnable {

    @Override
    public void run() {
        System.out.println("Subproceso hilo");
        int i;
        for (i = 0; i < 5; i++) {
            System.out.println("Hilo " + i);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Interrupción en el hilo");

            }
        }
        System.out.println("Termina subproceso hilo");
    }

    public static void main(String[] args) {
        Thread hilo  = new Thread(new UnHilo());
        Thread hilo2 = new Thread(new UnHilo());
        Thread hilo3 = new Thread(new UnHilo());

        hilo.start();
        hilo2.start();
        hilo3.start();

        System.out.println("Se manda a llamar el subproceso hilo");
        System.out.println("Proceso main");
        for (int i = 0; i < 10; i++) {
            System.out.println("Main " + i);
        }
        System.out.println("Termina subproceso main");
    }
}
