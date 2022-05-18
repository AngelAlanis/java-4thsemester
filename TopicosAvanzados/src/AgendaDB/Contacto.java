package AgendaDB;

public class Contacto {

    int    identificador;
    String nombre, telefono, correo, categoria, direccion, cumpleaños;

    public Contacto(int identificador, String nombre, String telefono, String correo, String categoria, String direccion, String cumpleaños) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.categoria = categoria;
        this.direccion = direccion;
        this.cumpleaños = cumpleaños;
    }

    public Contacto(String nombre, String telefono, String correo, String categoria, String direccion, String cumpleaños) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.categoria = categoria;
        this.direccion = direccion;
        this.cumpleaños = cumpleaños;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCumpleaños() {
        return cumpleaños;
    }

}
