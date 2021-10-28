package com.cuestionarios.cuestionarioproyecto.model;

import java.util.ArrayList;

public class Cuestionarios {
    private String id;
    private ArrayList<Preguntas> pregunta = new ArrayList<Preguntas>();
    private String fecha = "";
    private String nombre = "";
    private String correo = "";
    private ArrayList<String> respuestas = new ArrayList<String>();

    public Cuestionarios() {
    }

    public Cuestionarios(String id, ArrayList<Preguntas> pregunta, String fecha, String nombre, String correo, ArrayList<String> respuestas) {
        this.id = id;
        this.pregunta = pregunta;
        this.fecha = fecha;
        this.nombre = nombre;
        this.correo = correo;
        this.respuestas = respuestas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Preguntas> getPregunta() {
        return pregunta;
    }

    public void setPregunta(ArrayList<Preguntas> pregunta) {
        this.pregunta = pregunta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<String> respuestas) {
        this.respuestas = respuestas;
    }

    @Override
    public String toString() {
        return "Cuestionarios{" +
                "id='" + id + '\'' +
                ", pregunta=" + pregunta +
                ", fecha='" + fecha + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", respuestas=" + respuestas +
                '}';
    }
}
