package com.misael;

public class Producto {

    private String numSerie;
    private String nombre;
    private float precio;

    public Producto(String numSerie, String nombre, float precio) {
        this.numSerie = numSerie;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "numSerie='" + numSerie + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
