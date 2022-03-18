package com.misael;

public class Alumno {

    private String numControl, nombre, especialidad, semestre;

    public Alumno(String numControl, String nombre, String especialidad, String semestre) {
        this.numControl = numControl;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.semestre = semestre;
    }

    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
}
