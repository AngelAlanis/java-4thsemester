import com.misael.Mathematics.Mathematics;

import java.util.ArrayList;
import java.util.Arrays;

public class MathematicsPruebas {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Mathematics.busquedaIncremental("x^6-3x^4-2")));
        System.out.println("Resultado regla falsa = " + Mathematics.metodoReglaFalsa("x^6-3x^4-2", 0.0001));
        System.out.println("Resultado bisección = " + Mathematics.metodoBiseccion("x^6-3x^4-2", 0.0001));
        System.out.println("Resultado secante = " + Mathematics.metodoSecante("x^5-5x+1", 0.0001));
    }
}
