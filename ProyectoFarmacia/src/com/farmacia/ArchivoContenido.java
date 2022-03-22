package com.farmacia;

import java.util.Date;

public class ArchivoContenido {

    private final String nombreArchivo;
    private final Date   fecha;
    private final String ubicacion;

    public ArchivoContenido(String nombreArchivo, Date fecha, String ubicacion) {
        this.nombreArchivo = nombreArchivo;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public Date getFecha() {
        return fecha;
    }


    public String getUbicacion() {
        return ubicacion;
    }
}
