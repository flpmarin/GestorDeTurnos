package com.turnos.negocio;

import java.util.List;

import com.turnos.dao.DepartamentoDAO;
import com.turnos.dto.Departamento;
import com.turnos.dao.PosicionDAO;
import com.turnos.dto.Posicion;
import com.turnos.dao.TrabajadorDAO;
import com.turnos.dto.Trabajador;



//esta clase es la que se comunica con la base de datos
public class GestorTurnos {
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    private PosicionDAO posicionDAO = new PosicionDAO();

   
    // Métodos para departamentos
    public boolean agregarDepartamento(Departamento departamento) {
        return departamentoDAO.agregarDepartamento(departamento);
    }

    public boolean eliminarDepartamento(int id) {
        return departamentoDAO.eliminarDepartamento(id);
    }

    public boolean modificarDepartamento(Departamento departamento) {
        return departamentoDAO.modificarDepartamento(departamento);
    }

    public List<Departamento> obtenerTodosDepartamentos() {
        return departamentoDAO.obtenerTodosDepartamentos();
    }

    // Métodos para trabajadores
    public boolean agregarTrabajador(Trabajador trabajador) {
        return trabajadorDAO.agregarTrabajador(trabajador);
    }

    public boolean eliminarTrabajador(int id) {
        return trabajadorDAO.eliminarTrabajador(id);
    }

    public boolean modificarTrabajador(Trabajador trabajador) {
        return trabajadorDAO.modificarTrabajador(trabajador);
    }

    public List<Trabajador> obtenerTodosTrabajadores() {
        return trabajadorDAO.obtenerTodosTrabajadores();
    }

    // Métodos para posiciones
    public boolean agregarPosicion(Posicion posicion) {
        return posicionDAO.agregarPosicion(posicion);
    }

    public boolean eliminarPosicion(int id) {
        return posicionDAO.eliminarPosicion(id);
    }

    public boolean modificarPosicion(Posicion posicion) {
        return posicionDAO.modificarPosicion(posicion);
    }

    public List<Posicion> obtenerTodosPosiciones() {
        return posicionDAO.obtenerTodosPosiciones();
    }

    
}