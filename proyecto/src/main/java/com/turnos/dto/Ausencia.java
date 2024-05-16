package com.turnos.dto;

import java.sql.Date;

public class Ausencia {
    private String motivo;
    private Date inicio;
    private Date fin;
    private int trabajadorId;
    
    public Ausencia() {
    }

    public Ausencia(String motivo, Date inicio, Date fin, int trabajadorId) {
        this.motivo = motivo;
        this.inicio = inicio;
        this.fin = fin;
        this.trabajadorId = trabajadorId;
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
        return trabajadorId;
    }

    public void setTrabajador_id(int trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    @Override
    public String toString() {
        return "Ausencia{" + "motivo=" + motivo + ", inicio=" + inicio + ", fin=" + fin + ", trabajadorId=" + trabajadorId + '}';
    }
    

    
}
