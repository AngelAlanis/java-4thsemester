import com.misael.Mathematics.Mathematics;

public class MathematicsPruebas {

    public static void main(String[] args) {
       // System.out.println(Arrays.toString(Mathematics.busquedaIncremental("x^6-3x^4-2")));
        System.out.println("Resultado regla falsa = " + Mathematics.metodoReglaFalsa("cos(x^2)-3x+5", 0.0001));
        System.out.println("Resultado biseccción = " + Mathematics.metodoBiseccion("cos(x^2)-3x+5", 0.0001));
    }
}
