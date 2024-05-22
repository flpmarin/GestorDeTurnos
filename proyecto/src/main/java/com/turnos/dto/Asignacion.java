package com.turnos.dto;

import java.sql.Date;
import java.sql.Time;

public class Asignacion {
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private Time horaInicio;
    private Time horaFin;
    private int trabajadorId;
    private int turnoId;
    private int posicionId;
    
    public Asignacion() {
    }

    public Asignacion(int id, Date fechaInicio, Date fechaFin, Time horaInicio, Time horaFin, int trabajadorId, int turnoId, int posicionId) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.trabajadorId = trabajadorId;
        this.turnoId = turnoId;
        this.posicionId = posicionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public int getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(int trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    public int getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(int turnoId) {
        this.turnoId = turnoId;
    }

    public int getPosicionId() {
        return posicionId;
    }

    public void setPosicionId(int posicionId) {
        this.posicionId = posicionId;
    }
    @Override
    public String toString() {
        return "Asignacion{" + "id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", idTrabajador=" + trabajadorId + ", idTurno=" + turnoId + ", idPosicion=" + posicionId + '}';
    }
}