package com.turnos.dto;

public class Preferencia {
    private int id;
    private String descripcion;
    private int trabajadorId;
    private int turnoId;
    
    public Preferencia() {
    }

    public Preferencia(int id, String descripcion, int trabajadorId, int turnoId) {
        this.id = id;
        this.descripcion = descripcion;
        this.trabajadorId = trabajadorId;
        this.turnoId = turnoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        return "Preferencia [descripcion=" + descripcion + ", id=" + id + ", trabajadorId=" + trabajadorId + ", turnoId="
                + turnoId + "]";
    }

    
}
