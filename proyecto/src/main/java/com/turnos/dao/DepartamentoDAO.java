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
        List<Departamento> departamentos = new java.util.ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                departamentos.add(new Departamento(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departamentos;
    }

    public boolean eliminarDepartamento(int id) {
        String sql = "DELETE FROM Departamento WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar un departamento
    public boolean modificarDepartamento(Departamento departamento) {
        String sql = "UPDATE Departamento SET nombre = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, departamento.getNombre());
            pstmt.setInt(2, departamento.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

        
  

}
