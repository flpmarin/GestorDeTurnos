package com.turnos.dto;

public class Posicion {
    private int id;
    private String nombre;
    private int departamentoId;

    public Posicion() {
    }

    public Posicion(int id, String nombre, int departamentoId) {
        this.id = id;
        this.nombre = nombre;
        this.departamentoId = departamentoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(int departamentoId) {
        this.departamentoId = departamentoId;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
