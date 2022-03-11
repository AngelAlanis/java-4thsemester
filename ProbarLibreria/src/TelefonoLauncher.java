import com.misael.Telefono;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TelefonoLauncher {

    Scanner scanner = new Scanner(System.in);

    ArrayList<Telefono> listaTelefonos = new ArrayList<>();

    public static void main(String[] args) {
        new TelefonoLauncher().ejecutarPrograma();
    }

    public void ejecutarPrograma(){

        char seguir;

        do {

            int eleccion;
            System.out.println("¿Qué operación desea realizar?\n" +
                    "1. Capturar datos\n" +
                    "2. Leer datos");

            eleccion = scanner.nextInt();

            System.out.println(eleccion);

            switch (eleccion) {
                case 1 -> capturarDatos();
                case 2 -> imprimirDatos();
            }

            System.out.println("¿Desea continuar?\n" +
                    "Sí = Y \n" +
                    "No = N");
            seguir = scanner.next().charAt(0);
            if(seguir == 'N'){
                break;
            }

        } while(seguir == 'Y');
    }

    public void capturarDatos(){
        String numSerie, marca, capacidad, pantalla;
        numSerie = JOptionPane.showInputDialog(null, "Ingrese el número de serie");
        marca = JOptionPane.showInputDialog(null, "Ingrese la marca del teléfono");
        capacidad = JOptionPane.showInputDialog(null, "Ingrese la capacidad de la memoria");
        pantalla = JOptionPane.showInputDialog(null , "Ingrese el tamaño de la pantalla");

        listaTelefonos.add(new Telefono(numSerie, marca, capacidad, pantalla));
    }

    public void imprimirDatos(){
        for (int i = 0; i < listaTelefonos.size(); i++){
            System.out.println("Número de serie: " + listaTelefonos.get(i).getNumeroSerie());
            System.out.println("Marca: " + listaTelefonos.get(i).getMarca());
            System.out.println("Memoria: " + listaTelefonos.get(i).getMemoria());
            System.out.println("Pantalla: " + listaTelefonos.get(i).getPantalla());
        }
    }
}

