public class MetodosNumericos {

    public static void main(String[] args) {
        MetodosNumericos main = new MetodosNumericos();
        main.metodoIterativo(164, 2);
    }

    public double metodoIterativo(int x, double xN) {
        double siguienteResultado = (0.5 * (xN + (x / xN)));
        System.out.format("%.2f\n", siguienteResultado);
        if(siguienteResultado != xN){
            metodoIterativo(x,siguienteResultado);
        }
        return siguienteResultado;
    }
}


