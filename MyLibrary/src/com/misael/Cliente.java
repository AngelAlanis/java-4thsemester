package com.misael;

public class Cliente {

    private String numCliente;
    private String nombre;
    private String telefono;

    public Cliente(String numCliente, String nombre, String telefono) {
        this.numCliente = numCliente;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(String numCliente) {
        this.numCliente = numCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "numCliente='" + numCliente + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
