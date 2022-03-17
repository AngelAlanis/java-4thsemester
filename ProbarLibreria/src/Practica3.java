import com.misael.Producto;
import com.misael.Cliente;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Practica3 {

    ArrayList<Cliente> listaClientes = new ArrayList<>();
    ArrayList<Producto> listaProductos = new ArrayList<>();

    public static void main(String[] args) {
        new Practica3().ejecutarPrograma();
    }

    public void registrarCliente() {
        String numCliente, nombreCliente, telefono;

        numCliente = JOptionPane.showInputDialog(null, "Ingrese el número del cliente");
        nombreCliente = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente");
        telefono = JOptionPane.showInputDialog(null, "Ingrese el teléfono del cliente");

        listaClientes.add(new Cliente(numCliente, nombreCliente, telefono));
    }

    public void registrarProducto() {
        String numSerie, nombreProducto;
        float precioProducto;

        numSerie = JOptionPane.showInputDialog(null, "Ingrese el número de serie del producto");
        nombreProducto = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto");
        precioProducto = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese el precio del producto"));

        listaProductos.add(new Producto(numSerie, nombreProducto, precioProducto));
    }

    public void ejecutarPrograma() {
        char seguir;
        Scanner scanner = new Scanner(System.in);

        do {

            int eleccion;
            System.out.println("""
                    ¿Qué operación desea realizar?
                    1. Registrar cliente
                    2. Registrar producto
                    3. Mostrar clientes
                    4. Mostrar productos""");

            eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1 -> registrarCliente();
                case 2 -> registrarProducto();
                case 3 -> imprimirClientes();
                case 4 -> imprimirProductos();
            }

            System.out.println("""
                    ¿Desea continuar?
                    Sí = Y
                    No = N""");
            seguir = scanner.next().charAt(0);
            if (seguir == 'N') {
                break;
            }

        } while (seguir == 'Y');
    }


    public void imprimirClientes() {
        StringBuilder resultado = new StringBuilder();

        for (Cliente cliente : listaClientes) {
            resultado.append(cliente);
        }

        JOptionPane.showMessageDialog(null, resultado.toString());
    }

    public void imprimirProductos() {
        StringBuilder resultado = new StringBuilder();

        for (Producto producto : listaProductos) {
            resultado.append(producto);
        }

        JOptionPane.showMessageDialog(null, resultado.toString());
    }
}
