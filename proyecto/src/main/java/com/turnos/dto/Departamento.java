package com.turnos.dto;

import java.util.LinkedHashMap;

// LinkedHashMap permite mantener el orden de inserción, asi como un acceso rápido a los elementos por su clave.
public class Departamento {
    private int id;
    private String nombre;
    private LinkedHashMap<Integer, Trabajador> trabajadores; 
    private LinkedHashMap<Integer, Posicion> posiciones;

    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.trabajadores = new LinkedHashMap<>();
        this.posiciones = new LinkedHashMap<>();
    }

    public Departamento() {
        this.trabajadores = new LinkedHashMap<>();
        this.posiciones = new LinkedHashMap<>();
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

    public LinkedHashMap<Integer, Trabajador> getTrabajadores() {
        return this.trabajadores;
    }
  
    public void agregarPosicion(Posicion posicion) {
        if (this.posiciones != null) {
            this.posiciones.put(posicion.getId(), posicion);
        }
    }

    public LinkedHashMap<Integer, Posicion> getPosiciones() {
        return this.posiciones;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
   
    
}
