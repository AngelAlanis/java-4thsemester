import com.misael.Mathematics.Mathematics;

import java.util.ArrayList;
import java.util.Arrays;

public class MathematicsPruebas {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(Mathematics.busquedaIncremental("x^4-5x^3-4")));
//        System.out.println("Resultado regla falsa = " + Mathematics.metodoReglaFalsa("x^4-5x^3-4", 0.0001));
//        System.out.println("Resultado bisección = " + Mathematics.metodoBiseccion("x^4-5x^3-4", 0.0001));
        ArrayList<Double> xi = new ArrayList<>();
        ArrayList<Double> fxi = new ArrayList<>();
        xi.add(0.0);
        xi.add(-1.0);
        fxi.add(-2.0);
        fxi.add(0.7183);

        System.out.println(Mathematics.nextSolucionSecante(1, xi, fxi));
    }
}
