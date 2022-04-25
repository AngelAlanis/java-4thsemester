package EjemploHilos;

import javax.swing.*;

public class Practica3 implements Runnable {

    Thread hilo;
    String nombreHilo;

    public Practica3(String nombreHilo) {
        hilo = new Thread(this, nombreHilo);
        this.nombreHilo = nombreHilo;
        hilo.start();
    }

    public static void main(String[] args) {
        var sub1 = new Practica3("Sub_1");
        var sub2 = new Practica3("Sub_2");
        var sub3 = new Practica3("Sub_3");
        var sub4 = new Practica3("Sub_4");

        try {
            sub1.hilo.join();
            sub2.hilo.join();
            sub3.hilo.join();
            sub4.hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        System.out.println("Comienza " + hilo.getName());
        switch (hilo.getName()) {
            case "Sub_1" -> {
                for (int i = 0; i < 15; i++) {
                    System.out.println("Iteración " + i);
                }
            }
            case "Sub_2" -> {
                if (hilo.isAlive()) {
                    System.out.println("El hilo " + hilo.getName() + " está activo.");
                } else {
                    System.out.println("El hilo no está vivo");
                }

            }
            case "Sub_3" -> {
                String input = JOptionPane.showInputDialog(null, "Ingrese una palabra");
                JOptionPane.showMessageDialog(null, input);
            }
            case "Sub_4" -> {
                JFrame jFrame = new JFrame();
                jFrame.setSize(200, 400);
                jFrame.setLocationRelativeTo(null);
                jFrame.setVisible(true);
            }

            default -> System.out.println("No se encontró el hilo");

        }
    }
}
