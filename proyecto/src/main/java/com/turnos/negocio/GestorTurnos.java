package com.turnos.negocio;

import com.turnos.dao.DepartamentoDAO;
import com.turnos.dto.Departamento;


//esta clase esla que se comunica con la base de datos
public class GestorTurnos {
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();

    // Métodos para departamentos
    public boolean crearDepartamento(Departamento departamento) {
        return departamentoDAO.crearDepartamento(departamento);
    }

    public boolean eliminarDepartamento(int id) {
        return departamentoDAO.eliminarDepartamento(id);
    }

    
}