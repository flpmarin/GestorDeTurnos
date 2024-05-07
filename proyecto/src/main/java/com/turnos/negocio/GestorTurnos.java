package com.turnos.negocio;


import com.turnos.dao.DepartamentoDAO;
import com.turnos.dto.Departamento;


//esta clase esla que se comunica con la base de datos
public class GestorTurnos {
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();

    // Métodos para departamentos
    public boolean agregarDepartamento(Departamento departamento) {
        return departamentoDAO.agregarDepartamento(departamento);
    }

    
}