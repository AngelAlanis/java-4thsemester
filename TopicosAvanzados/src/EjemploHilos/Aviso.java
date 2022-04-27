package EjemploHilos;

public class Aviso {
    boolean bandera = false;

    public Aviso() {
    }

    public synchronized void saludoEspera(String nombreHilo) {
        try {
            if (!bandera) {
                wait();
            }
            System.out.println("Trabajando: " + nombreHilo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void saludoNotificacion(String nombreHilo) {
        bandera = true;
        System.out.println("A trabajar: " + nombreHilo);
        notifyAll();
    }
}
