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

    // En GestorTurnos.java
    public Departamento getDepartamentoPorId(int id) {
        return departamentoDAO.getDepartamentoPorId(id);
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

    public List<Trabajador> obtenerTrabajadoresPorDepartamento(int idDepartamento) {
        return trabajadorDAO.obtenerTrabajadoresPorDepartamento(idDepartamento);
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

    public List<Posicion> obtenerTodasPosiciones() {
        return posicionDAO.obtenerTodosPosiciones();
    }

    public List<Posicion> obtenerPosicionesPorDepartamento(int idDepartamento) {
        return posicionDAO.obtenerPosicionesPorDepartamento(idDepartamento);
    }

    // está en TraabajadorDao
    public List<Posicion> obtenerPosicionesPorTrabajador(int idTrabajador) {
        return trabajadorDAO.obtenerPosicionesPorTrabajador(idTrabajador);
    }

    // Método para asociar una posición habilitada a un trabajador
    public boolean asociarPosicionATrabajador(Trabajador trabajador, Posicion posicion) {
        try {
            // si la posición no está asignada al trabajador, la asignamos y viceversa.
            if (!trabajador.getPosiciones().contains(posicion)) {
                trabajador.getPosiciones().add(posicion);
                posicion.getTrabajadores().add(trabajador);
    
                // Actualizamos la base de datos
                PosicionDAO posicionDAO = new PosicionDAO();
                boolean exito = posicionDAO.asociarTrabajadorAPosicion(trabajador, posicion);
    
                return exito; // Devolvemos el resultado de la operación en la base de datos
            }
            return false; // La posición ya estaba asignada al trabajador
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Hubo un error
        }
    }

    // Método para retirar una posición habilitada de un trabajador
    public boolean retirarPosicionHabilitada(Trabajador trabajador, Posicion posicion) {
        try {
            // si la posición está asignada al trabajador, la retiramos y viceversa.
            if (trabajador.getPosiciones().contains(posicion)) {
                trabajador.getPosiciones().remove(posicion);
                posicion.getTrabajadores().remove(trabajador);
    
                // Actualizamos la base de datos
                PosicionDAO posicionDAO = new PosicionDAO();
                boolean exito = posicionDAO.retirarTrabajadorDePosicion(trabajador, posicion);
    
                return exito; // Devolvemos el resultado de la operación en la base de datos
            }
            return false; // La posición no estaba asignada al trabajador
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Hubo un error
        }
    }

}