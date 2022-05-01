package PracticaDesktop;

import javax.swing.DefaultListModel;

public class UnHilo implements Runnable {

    DefaultListModel<String> modelo = new DefaultListModel<>();

    @Override
    public void run() {
        System.out.println("Subproceso hilo");
        for (int i = 0; i < 5; i++) {
            System.out.println("Iteración " + i);
            modelo.addElement("Iteraión " + i);
            //Ventana1JInternalFrame.miLista.setModel(modelo);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Termina subproceso hilo");
    }
}