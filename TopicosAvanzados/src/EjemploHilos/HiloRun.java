package EjemploHilos;

public class HiloRun extends Thread {
    private final String  nombreHilo;
    private final Aviso   aviso;
    private final boolean bandera;

    public HiloRun(String nombreHilo, boolean bandera, Aviso aviso) {
        this.nombreHilo = nombreHilo;
        this.bandera = bandera;
        this.aviso = aviso;
    }

    public void run() {
        System.out.println("Llegó " + nombreHilo);
        try {
            Thread.sleep(1500);
            if (bandera) {
                aviso.saludoNotificacion(nombreHilo);
            } else {
                aviso.saludoEspera(nombreHilo);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
