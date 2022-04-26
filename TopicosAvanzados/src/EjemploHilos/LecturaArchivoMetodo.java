package EjemploHilos;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LecturaArchivoMetodo {
    String concatenado = "";

    public void leerArchivo(String archivo) {
        try {
            Thread.sleep(1000);
            String cadena;
            FileReader     fileReader     = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            cadena = bufferedReader.readLine();
            bufferedReader.close();
            concatenado += cadena + "\n";
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    synchronized void capturar() {
        String palabra = JOptionPane.showInputDialog(null, "Teclea alguna palabra");
        JOptionPane.showMessageDialog(null, "La palabra escrita es: " + palabra);
    }

    public void ciclo() {
        for (int i = 0; i < 15; i++) {
            System.out.println("Iteración " + i);
        }
        System.out.println("Termina hilo 3");
    }

    synchronized void imprimirArchivo() {
        System.out.println("\nHilo 1 e Hilo 5. El archivo leído contiene: \n" + concatenado);
    }
}
