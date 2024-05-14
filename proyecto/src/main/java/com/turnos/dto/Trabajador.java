package com.turnos.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trabajador {
    private int id;
    private String nombre;
    private int departamentoId;
    List<Posicion> posiciones;
    List<Ausencia> ausencias;

    public Trabajador() {
    }

    public Trabajador(int id, String nombre, int departamentoId) {
        this.id = id;
        this.nombre = nombre;
        this.departamentoId = departamentoId;
        this.posiciones = new java.util.ArrayList<>();
        this.ausencias = new java.util.ArrayList<>();
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

    public List<Posicion> getPosiciones() {
        return posiciones;
    }

    public List<Ausencia> getAusencias() {
        return ausencias;
    }

    public void setAusencias(List<Ausencia> ausencias) {
        this.ausencias = ausencias;
    }

    // agregar una posición a la lista de posiciones del trabajador, en el GUI se hará la validación de no agregar duplicados a la lista. 
    public void agregarPosicionALista(Posicion posicion) {
        if (this.posiciones == null) {
            this.posiciones = new ArrayList<>();
        }
        this.posiciones.add(posicion);
    }
    // retirar una posición de la lista de posiciones del trabajador
    public void retirarPosicionDeLista(Posicion posicion) {
        if (this.posiciones != null) {
            this.posiciones.remove(posicion);
        }
    }
   

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Trabajador otro = (Trabajador) obj;
        return id == otro.id; // Compara los objetos en función de su id
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Genera un hash en función del id
    }
}
