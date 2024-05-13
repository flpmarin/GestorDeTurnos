package com.turnos.dto;

import java.sql.Time;

public class Turno {
    private int id;
    private String nombre;
    private Time horaInicio;
    private Time horaFin;
    
    public Turno() {
    }

    public Turno(int id, String nombre, Time horaInicio, Time horaFin) {
        this.id = id;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
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

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "turno: "+nombre+", Inicio: " + horaInicio +" Fin: " + horaFin ;
    }

    

    
}
