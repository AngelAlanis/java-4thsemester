package PracticaDesktop;

import javax.swing.DefaultListModel;

public class UnHilo implements Runnable {

    Thread hilo;
    String nombreHilo;

    DefaultListModel<String> modeloUno = new DefaultListModel<>();
    DefaultListModel<String> modeloDos = new DefaultListModel<>();

    public UnHilo (String nombreHilo) {
        hilo = new Thread(this, nombreHilo);
        this.nombreHilo = nombreHilo;
        hilo.start();
    }

    @Override
    public void run() {
        switch(hilo.getName()){
            case "hilo_1" -> {
                System.out.println("Comienza hilo 1");
                for (int i = 0; i < 5; i++) {
                    modeloUno.addElement("HiloUno " + i);
                    Interfaz.listaHiloUno.setModel(modeloUno);
                    System.out.println("HiloUno " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Termina hilo 1");
            }

            case "hilo_2" -> {
                System.out.println("Comienza hilo 2");
                for (int i = 0; i < 5; i++) {
                    modeloDos.addElement("HiloDos " + i);
                    System.out.println("HiloDos " + i);
                    Interfaz.listaHiloDos.setModel(modeloDos);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Termina hilo 2");
            }
        }


    }
}