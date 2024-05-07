package com.turnos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.turnos.dto.Departamento;

public class DepartamentoDAO {   // Obtener un autor por su ID

     public boolean agregarDepartamento(Departamento departamento) {
        String sql = "INSERT INTO Departamento (nombre) VALUES (?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, departamento.getNombre());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Departamento> obtenerTodosDepartamentos() {
        String sql = "SELECT * FROM Departamento";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            List<Departamento> departamentos = new java.util.ArrayList<>();
            while (rs.next()) {
                departamentos.add(new Departamento(rs.getInt("id"), rs.getString("nombre")));
            }
            return departamentos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
