package EjemploHilos;

public class HiloLecturaArchivo implements Runnable {
    Thread               hilo;
    String               nombreHilo;
    LecturaArchivoMetodo archivoMetodo = new LecturaArchivoMetodo();

    public HiloLecturaArchivo(String nombreHilo) {
        hilo = new Thread(this, nombreHilo);
        this.nombreHilo = nombreHilo;
        System.out.println("Comienza hilo " + hilo.getName());
        hilo.start();
    }

    @Override
    public void run() {
        synchronized (archivoMetodo) {

            switch (hilo.getName()) {
                case "Nom_Hilo1" -> archivoMetodo.leerArchivo("C:\\Users\\misae\\IdeaProjects\\java-4thsemester\\TopicosAvanzados\\src\\EjemploHilos\\ArchivoALeer.txt");
                case "Nom_Hilo2" -> archivoMetodo.capturar();
                case "Nom_Hilo3" -> archivoMetodo.ciclo();
                case "Nom_Hilo4" -> {
                    try {
                        Thread.sleep(1500);
                        if (hilo.isAlive()) {
                            System.out.println("El Nom_Hilo4 está vivo");
                            System.out.println("El hilo tiene como prioridad " + hilo.getPriority());
                            System.out.println("Termina Nom_Hilo4");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Interrupción en hilo");
                    }
                }
                case "Nom_Hilo5" -> archivoMetodo.imprimirArchivo();
                default -> System.out.println("Hilo no encontrado");
            }
        }
    }

    public static void main(String[] args) {
        HiloLecturaArchivo hilo1 = new HiloLecturaArchivo("Nom_Hilo1");
        HiloLecturaArchivo hilo2 = new HiloLecturaArchivo("Nom_Hilo2");
        HiloLecturaArchivo hilo3 = new HiloLecturaArchivo("Nom_Hilo3");
        HiloLecturaArchivo hilo4 = new HiloLecturaArchivo("Nom_Hilo4");
        HiloLecturaArchivo hilo5 = new HiloLecturaArchivo("Nom_Hilo5");

        hilo4.hilo.setPriority(5);

        try {
            hilo1.hilo.join();
            hilo2.hilo.join();
            hilo3.hilo.join();
            hilo4.hilo.join();
            hilo5.hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
