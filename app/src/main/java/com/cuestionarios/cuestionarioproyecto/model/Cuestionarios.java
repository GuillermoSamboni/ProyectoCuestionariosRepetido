package com.cuestionarios.cuestionarioproyecto.model;

import java.util.ArrayList;

public class Cuestionarios {
    private ArrayList preguntas = new ArrayList();
    private String fecha_creacion = "";
    private String nombre_cuestionario = "";

    public Cuestionarios() {
    }

    public Cuestionarios(ArrayList preguntas, String fecha_creacion, String nombre_cuestionario) {
        this.preguntas = preguntas;
        this.fecha_creacion = fecha_creacion;
        this.nombre_cuestionario = nombre_cuestionario;
    }

    public ArrayList getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList preguntas) {
        this.preguntas = preguntas;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getNombre_cuestionario() {
        return nombre_cuestionario;
    }

    public void setNombre_cuestionario(String nombre_cuestionario) {
        this.nombre_cuestionario = nombre_cuestionario;
    }

    @Override
    public String toString() {
        return "Cuestionarios{" +
                "preguntas=" + preguntas +
                ", fecha_creacion='" + fecha_creacion + '\'' +
                ", nombre_cuestionario='" + nombre_cuestionario + '\'' +
                '}';
    }
}
