package EjemploHilos;

public class HilosPruebaAtributos implements Runnable {

    Thread nombreHilo;

    HilosPruebaAtributos(String nombre) {
        nombreHilo = new Thread(this, nombre);
        nombreHilo.start();
        System.out.println("Se manda a llamar subproceso");
    }

    @Override
    public void run() {
        System.out.println("Subproceso " + nombreHilo.getName() + ". Antes del ciclo. Prioridad: " + nombreHilo.getPriority());

        for (int i = 0; i < 5; i++) {

            System.out.println("Hilo " + i);

            try {
                Thread.sleep(1000);
                System.out.println("Subproceso: " + nombreHilo.getName() + " Ya despertó." + " Prioridad" + nombreHilo.getPriority());
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Algo salió mal");
            }
        }

        System.out.println("Termina hilo");
    }

    public static void main(String[] args) {
        var hilo = new HilosPruebaAtributos("Pepe");

        System.out.println("Inicia proceso main");
        for (int i = 0; i < 10; i++) {
            System.out.println("Main " + i);
            if (hilo.nombreHilo.isAlive()) {
                System.out.println("El proceso hijo todavía tiene vida");
            }
        }
        System.out.println("\nTermina proceso main");
    }
}
