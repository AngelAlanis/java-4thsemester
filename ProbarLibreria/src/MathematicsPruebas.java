import com.misael.Mathematics.Mathematics;

public class MathematicsPruebas {

    public static void main(String[] args) {
        System.out.println("Resultado regla falsa = " + Mathematics.metodoReglaFalsa("x^2-5x+1", 0.0001));
        System.out.println("Resultado bisección = " + Mathematics.metodoBiseccion("x^2-5x+1", 0.0001));
        System.out.println("Resultado secante = " + Mathematics.metodoSecante("x^2-5x+1", 0.0001));
        System.out.println("Resultado Newton Raphson = " + Mathematics.metodoNewtonRaphson("x^2-5x+1", 0.0001));

        String expresion = "x^2-5x+1";
        String derivada = Mathematics.derivar("x^2-5x+1");
        System.out.println("Derivada de " + expresion + " = " + derivada);
        System.out.println("Evaluación de la derivada con 3 = " + Mathematics.evaluarExpresion(derivada, 3));
    }
}
