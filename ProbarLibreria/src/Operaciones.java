
import com.misael.MyMethods;

import java.util.Scanner;

public class Operaciones {


    public static void main(String[] args) {
        char continuar = 'Y';
        int operacion = 0;

        int a = 0, b = 0;

        do {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Escriba un número para la operación que desea realizar\n" +
                    "1. Sumar\n" +
                    "2. Restar\n" +
                    "3. Multiplicar\n" +
                    "4. Dividir\n");

            operacion = scanner.nextInt();

            System.out.println("Ingrese el valor del primer número");
            a = scanner.nextInt();

            System.out.println("Ingrese el valor del segundo número");
            b = scanner.nextInt();

            switch(operacion){
                case 1 -> System.out.println("La respuesta de la suma de " + a + " y " + b + " es : " + MyMethods.sumar(a, b));
                case 2 -> System.out.println("La respuesta de la resta de " + a + " y " + b + " es : " + MyMethods.restar(a, b));
                case 3 -> System.out.println("La respuesta de la multiplicación de " + a + " y " + b + " es : " + MyMethods.multiplicar(a, b));
                case 4 -> System.out.println("La respuesta de la división de " + a + " y " + b + " es : " + MyMethods.dividir(a, b));

            }

            System.out.println("¿Desea continuar?\n" +
                    "Sí = Y \n" +
                    "No = N");
            continuar = scanner.next().charAt(0);
            if(continuar == 'N'){
                break;
            }

        }while(continuar == 'Y');

    }

}
