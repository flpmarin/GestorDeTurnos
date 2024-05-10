package com.turnos.dto;

import java.util.HashMap;
import java.util.Map;

public class Departamento {
    private int id;
    private String nombre;
    private Map<Integer, Trabajador> trabajadores;

    

    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.trabajadores = new HashMap<>();

    }
    public Departamento() {
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

    public void agregarTrabajador(Trabajador trabajador) {
        this.trabajadores.put(trabajador.getId(), trabajador);
    }

    public Map<Integer, Trabajador> getTrabajadores() {
        return this.trabajadores;
    }

    @Override
    public String toString() {
        return nombre;
    }
   
    
}
