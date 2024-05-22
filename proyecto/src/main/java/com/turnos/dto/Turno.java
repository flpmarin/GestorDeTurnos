package com.turnos.dto;

import java.sql.Time;

public class Turno {
    private int id;
    private int turnoIdGrupo;
    private String nombre;
    private Time horaInicio;
    private Time horaFin;
    
    public Turno() {
    }

    public Turno(int id, int turnoIdGrupo, String nombre, Time horaInicio, Time horaFin) {
        this.id = id;
        this.turnoIdGrupo = turnoIdGrupo;
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

    public int getTurnoIdGrupo() {
        return turnoIdGrupo;
    }

    public void setTurnoIdGrupo(int turnoIdGrupo) {
        this.turnoIdGrupo = turnoIdGrupo;
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

    // Método que calcula la duración del turno en horas y la devuelve como entero
    public int getDuracion() {
        long milisegundosInicio = horaInicio.getTime();
        long milisegundosFin = horaFin.getTime();

        long duracionEnMilisegundos = milisegundosFin - milisegundosInicio;
        return (int) (duracionEnMilisegundos / (60 * 60 * 1000)); // Convertimos la duración a horas
    }

    @Override
    public String toString() {
        return "ID Grupo: "+ turnoIdGrupo +" turno: "+nombre+", Inicio: " + horaInicio +" Fin: " + horaFin ;
    }

    

    
}
