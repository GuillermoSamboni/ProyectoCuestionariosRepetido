package com.cuestionarios.cuestionarioproyecto.model;

public class Preguntas {
    private String imagen_e;
    private String imagen_m;
    private String contenido;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String respuesta;
    private String tipo;
    private String titulo;

    public Preguntas() {
    }

    public Preguntas(String imagen_e, String imagen_m, String contenido, String op1, String op2, String op3, String op4, String respuesta, String tipo, String titulo) {
        this.imagen_e = imagen_e;
        this.imagen_m = imagen_m;
        this.contenido = contenido;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.respuesta = respuesta;
        this.tipo = tipo;
        this.titulo = titulo;
    }

    public String getImagen_e() {
        return imagen_e;
    }

    public void setImagen_e(String imagen_e) {
        this.imagen_e = imagen_e;
    }

    public String getImagen_m() {
        return imagen_m;
    }

    public void setImagen_m(String imagen_m) {
        this.imagen_m = imagen_m;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Preguntas{" +
                "imagen_e='" + imagen_e + '\'' +
                ", imagen_m='" + imagen_m + '\'' +
                ", contenido='" + contenido + '\'' +
                ", op1='" + op1 + '\'' +
                ", op2='" + op2 + '\'' +
                ", op3='" + op3 + '\'' +
                ", op4='" + op4 + '\'' +
                ", respuesta='" + respuesta + '\'' +
                ", tipo='" + tipo + '\'' +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
