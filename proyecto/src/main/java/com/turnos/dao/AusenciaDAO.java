package com.turnos.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.turnos.dto.Ausencia;

public class AusenciaDAO {
    public boolean agregarAusencia(Ausencia ausencia) {
        String sql = "INSERT INTO ausencias (motivo, inicio, fin, trabajador_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ausencia.getMotivo());
            pstmt.setDate(2, ausencia.getInicio());
            pstmt.setDate(3, ausencia.getFin());
            pstmt.setInt(4, ausencia.getTrabajador_id());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarAusencia(Date inicio, Date fin, int trabajador_id) {
        String sql = "DELETE FROM ausencias WHERE inicio = ? AND fin = ? AND trabajador_id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, inicio);
            pstmt.setDate(2, fin);
            pstmt.setInt(3, trabajador_id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar una ausencia
    public boolean modificarAusencia(Ausencia ausencia) {
        String sql = "UPDATE ausencias SET motivo = ?, inicio = ?, fin = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ausencia.getMotivo());
            pstmt.setDate(2, ausencia.getInicio());
            pstmt.setDate(3, ausencia.getFin());
            pstmt.setInt(4, ausencia.getTrabajador_id());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarAusenciasPorTrabajador(int trabajador_id) {
        String sql = "DELETE FROM ausencias WHERE trabajador_id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trabajador_id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Ausencia> obtenerTodasAusencias() {
        String sql = "SELECT * FROM ausencias";
        List<Ausencia> ausencias = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Ausencia ausencia = new Ausencia();
                ausencia.setMotivo(rs.getString("motivo"));
                ausencia.setInicio(rs.getDate("inicio"));
                ausencia.setFin(rs.getDate("fin"));
                ausencia.setTrabajador_id(rs.getInt("trabajador_id"));
                ausencias.add(ausencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ausencias;
    }

    public List<Ausencia> obtenerAusenciasPorTrabajador(int trabajador_id) {
    String sql = "SELECT * FROM ausencias WHERE trabajador_id = ?";
    List<Ausencia> ausencias = new ArrayList<>();
    try (Connection conn = Conexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, trabajador_id);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Ausencia ausencia = new Ausencia();
                ausencia.setMotivo(rs.getString("motivo"));
                ausencia.setInicio(rs.getDate("inicio"));
                ausencia.setFin(rs.getDate("fin"));
                ausencia.setTrabajador_id(rs.getInt("trabajador_id"));
                ausencias.add(ausencia);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ausencias;
}
}
