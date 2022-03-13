package com.farmacia;

public class Producto {

    private final String folio;
    private final String descripcion;
    private final float precioVenta;
    private final float importe;
    private final int cantidad;
    private final int stock;

    public Producto (String folio, String descripcion, float precioVenta, int cantidad, float importe, int stock) {
        this.folio = folio;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
        this.importe = importe;
        this.stock = stock;
    }

    public String getFolio() {
        return folio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public float getImporte() {
        return importe;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getStock() {
        return stock;
    }
}
