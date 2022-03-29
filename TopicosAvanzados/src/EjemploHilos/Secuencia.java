package EjemploHilos;

public class Secuencia {

    public static void main(String[] args) {
        var secuencia = new Secuencia();
        secuencia.ciclo(10);
        secuencia.dormir();
        secuencia.ciclo(20);
        secuencia.dormir();
        System.out.println("Terminado");

    }

    public void ciclo(int valor) {
        System.out.println("Inicia ciclo desde 1 hasta " + valor);
        for (int i = 0; i < valor; i++) {
            System.out.println("Valor de i " + i);
        }
    }

    public void dormir() {
        System.out.println("A dormir");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Algo anda mal");
        }
        System.out.println("Despierte");
    }


}
