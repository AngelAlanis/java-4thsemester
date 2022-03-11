package com.misael;

public class Telefono {

    private String numeroSerie, marca, memoria, pantalla;

    public Telefono(String numeroSerie, String marca, String memoria, String pantalla) {
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.memoria = memoria;
        this.pantalla = pantalla;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getPantalla() {
        return pantalla;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }
}
