package com.turnos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.turnos.dto.Preferencia;

public class PreferenciaDAO {
    public boolean agregarPreferencia(Preferencia preferencia) {
        String sql = "INSERT INTO preferencias (descripcion, trabajador_id, turno_id) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, preferencia.getDescripcion());
            pstmt.setInt(2, preferencia.getTrabajadorId());
            pstmt.setInt(3, preferencia.getTurnoId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPreferencia(int id) {
        String sql = "DELETE FROM preferencias WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar una preferencia
    public boolean modificarPreferencia(Preferencia preferencia) {
        String sql = "UPDATE preferencias SET descripcion = ?, trabajador_id = ?, turno_id = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, preferencia.getDescripcion());
            pstmt.setInt(2, preferencia.getTrabajadorId());
            pstmt.setInt(3, preferencia.getTurnoId());
            pstmt.setInt(4, preferencia.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPreferenciasPorTrabajador(int trabajadorId) {
        String sql = "DELETE FROM preferencias WHERE trabajador_id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trabajadorId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Preferencia> obtenerPreferenciasPorTrabajador(int trabajadorId) {
        String sql = "SELECT * FROM preferencias WHERE trabajador_id = ?";
        List<Preferencia> preferencias = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trabajadorId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Preferencia preferencia = new Preferencia();
                preferencia.setId(rs.getInt("id"));
                preferencia.setDescripcion(rs.getString("descripcion"));
                preferencia.setTrabajadorId(rs.getInt("trabajador_id"));
                preferencia.setTurnoId(rs.getInt("turno_id"));
                preferencias.add(preferencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preferencias;
    }

    public List<Preferencia> obtenerTodasPreferencias() {
        String sql = "SELECT * FROM preferencias";
        List<Preferencia> preferencias = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Preferencia preferencia = new Preferencia();
                preferencia.setId(rs.getInt("id"));
                preferencia.setDescripcion(rs.getString("descripcion"));
                preferencia.setTrabajadorId(rs.getInt("trabajador_id"));
                preferencia.setTurnoId(rs.getInt("turno_id"));
                preferencias.add(preferencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preferencias;
    }
    

    
        
}
