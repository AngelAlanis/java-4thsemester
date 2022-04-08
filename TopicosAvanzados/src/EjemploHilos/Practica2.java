package EjemploHilos;

public class Practica2 implements Runnable {

    Thread hilo;
    String nombreHilo;

    public Practica2(String nombreHilo) {
        hilo = new Thread(this, nombreHilo);
        this.nombreHilo = nombreHilo;
        hilo.start();
    }

    public static void main(String[] args) {
        var hilo1 = new Practica2("Hilo I");
        var hilo2 = new Practica2("Hilo II");
        var hilo3 = new Practica2("Hilo III");
        var hilo4 = new Practica2("Hilo IV");

        try {
            hilo4.hilo.join();
            hilo3.hilo.join();
            hilo2.hilo.join();
            hilo1.hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        System.out.println("Comienza " + hilo.getName() + " método run()");

        try {
            for (int i = 1; i < 9; i++) {
                System.out.println("A dormir " + hilo.getName());
                switch (hilo.getName()) {
                    case "Hilo I" -> Thread.sleep(250);
                    case "Hilo 2" -> Thread.sleep(200);
                    case "Hilo 3" -> Thread.sleep(150);
                    case "Hilo 4" -> Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Interrupción en hilo");
        }

        System.out.println("**** Terminado " + hilo.getName());
    }
}
