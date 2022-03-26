import com.misael.Mathematics.Mathematics;

import java.util.ArrayList;
import java.util.Arrays;

public class MathematicsPruebas {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Mathematics.busquedaIncremental("x^4-5x^3-4")));
        System.out.println("Resultado regla falsa = " + Mathematics.metodoReglaFalsa("x^4-5x^3-4", 0.0001));
        System.out.println("Resultado bisección = " + Mathematics.metodoBiseccion("x^4-5x^3-4", 0.0001));
        System.out.println("Resultado secante = " + Mathematics.metodoSecante("x^4-5x^3-4", 0.0001));
    }
}
