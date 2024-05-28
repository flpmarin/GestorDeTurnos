package com.turnos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.turnos.dto.Posicion;
import com.turnos.dto.Trabajador;

public class PosicionDAO {

    public boolean agregarPosicion(Posicion posicion) {
        String sql = "INSERT INTO posiciones (nombre, departamento_id) VALUES (?, ?)";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, posicion.getNombre());
            pstmt.setInt(2, posicion.getDepartamentoId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        posicion.setId(rs.getInt(1));
                    }
                }
            }
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Posicion> obtenerTodosPosiciones() {
        String sql = "SELECT * FROM posiciones";
        List<Posicion> posiciones = new java.util.ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                posiciones.add(new Posicion(rs.getInt("id"), rs.getString("nombre"), rs.getInt("departamento_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posiciones;
    }

    public boolean eliminarPosicion(int id) {
        String sql = "DELETE FROM posiciones WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar una posicion
    public boolean modificarPosicion(Posicion posicion) {
        String sql = "UPDATE posiciones SET nombre = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, posicion.getNombre());
            pstmt.setInt(2, posicion.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Posicion> obtenerPosicionesPorDepartamento(int idDepartamento) {
        String sql = "SELECT * FROM posiciones WHERE departamento_id = ?";
        List<Posicion> posiciones = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDepartamento);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    posiciones.add(new Posicion(rs.getInt("id"), rs.getString("nombre"), rs.getInt("departamento_id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posiciones;
    }

    public boolean asociarTrabajadorAPosicion(Trabajador trabajador, Posicion posicion) {
        String sql = "INSERT INTO trabajadores_posiciones (trabajador_id, posicion_id) VALUES (?, ?)";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trabajador.getId());
            pstmt.setInt(2, posicion.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean retirarTrabajadorDePosicion(Trabajador trabajador, Posicion posicion) {
        String sql = "DELETE FROM trabajadores_posiciones WHERE trabajador_id = ? AND posicion_id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trabajador.getId());
            pstmt.setInt(2, posicion.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int calcularCantidadPosiciones () {
        String sql = "SELECT COUNT(*) FROM posiciones";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String obtenerNombrePosicionPorId(int posicion_id){
        String sql = "SELECT nombre FROM posiciones WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, posicion_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
