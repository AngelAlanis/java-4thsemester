package AgendaDB;

public class Contacto {

    int    identificador;
    String nombre, telefono, correo, categoria;

    public Contacto(int identificador, String nombre, String telefono, String correo, String categoria) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.categoria = categoria;
    }

    public Contacto(String nombre, String telefono, String correo, String categoria) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.categoria = categoria;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
}
