package com.turnos.dto;

import java.sql.Date;

public class Ausencia {
    private String motivo;
    private Date inicio;
    private Date fin;
    private int trabajador_id;
    
    public Ausencia() {
    }

    public Ausencia(String motivo, Date inicio, Date fin, int trabajador_id) {
        this.motivo = motivo;
        this.inicio = inicio;
        this.fin = fin;
        this.trabajador_id = trabajador_id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public int getTrabajador_id() {
        return trabajador_id;
    }

    public void setTrabajador_id(int trabajador_id) {
        this.trabajador_id = trabajador_id;
    }

    @Override
    public String toString() {
        return "Ausencia{" + "motivo=" + motivo + ", inicio=" + inicio + ", fin=" + fin + ", trabajador_id=" + trabajador_id + '}';
    }
    

    
}
