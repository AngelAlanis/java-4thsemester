public class Empleado extends Persona {

    private String puesto;
    private double sueldo;

    public Empleado(String nombre, int edad, String puesto, float sueldo){
        super(nombre, edad);
        this.puesto = puesto;
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", puesto='" + puesto + '\'' +
                ", sueldo=" + sueldo;
    }
}
