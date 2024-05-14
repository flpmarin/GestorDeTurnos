package com.turnos.dto;

import java.util.List;
import java.util.Objects;

public class Posicion {
    private int id;
    private String nombre;
    private int departamentoId;
    private List<Trabajador> trabajadores;

    public Posicion() {
    }

    public Posicion(int id, String nombre, int departamentoId) {
        this.id = id;
        this.nombre = nombre;
        this.departamentoId = departamentoId;
        this.trabajadores = new java.util.ArrayList<>();
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

    public List<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    // agregar un trabajador a la lista de trabajadores de la posición, en el GUI se
    // hará la validación de no agregar duplicados a la lista.
    public void addTrabajador(Trabajador trabajador) {
        if (this.trabajadores == null) {
            this.trabajadores = new java.util.ArrayList<>();
        }
        this.trabajadores.add(trabajador);
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override // Sobreescribir el método equals para comparar objetos de tipo Posicion, en
              // función de su id y no de la referencia de memoria. 
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Posicion otro = (Posicion) obj;
        return id == otro.id; // Compara los objetos en función de su id
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Genera un hash en función del id
    }

}
