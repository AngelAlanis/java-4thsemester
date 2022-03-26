import com.misael.Mathematics.Mathematics;

import java.util.Arrays;

public class MathematicsPruebas {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Mathematics.busquedaIncremental("x^4 - 4.55x^3 - 14.16x^2 + 45.8x")));
        System.out.println("Resultado regla falsa = " + Mathematics.metodoReglaFalsa("x^4 - 4.55x^3 - 14.16x^2 + 45.8x", 0.0001));
        System.out.println("Resultado bisección = " + Mathematics.metodoBiseccion("x^4 - 4.55x^3 - 14.16x^2 + 45.8x", 0.0001));
    }
}
