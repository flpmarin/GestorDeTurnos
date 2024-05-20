package com.turnos.dto;

import java.sql.Date;

public class Asignacion {
    private int id;
    private Date fecha;
    private int idTrabajador;
    private int idTurno;
    private int idPosicion;
    
    public Asignacion() {
    }

    public Asignacion(int id, Date fecha, int idTrabajador, int idTurno, int idPosicion) {
        this.id = id;
        this.fecha = fecha;
        this.idTrabajador = idTrabajador;
        this.idTurno = idTurno;
        this.idPosicion = idPosicion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(int idPosicion) {
        this.idPosicion = idPosicion;
    }

    @Override
    public String toString() {
        return "Asignacion{" + "id=" + id + ", fecha=" + fecha + ", idTrabajador=" + idTrabajador + ", idTurno=" + idTurno + ", idPosicion=" + idPosicion + '}';
    }

    
    
}
