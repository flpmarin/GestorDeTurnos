package com.turnos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.turnos.dto.Turno;

public class TurnoDAO {
    public boolean agregarTurno(Turno turno) {
        String sql = "INSERT INTO turnos (nombre, horaInicio, horaFin) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, turno.getNombre());
            pstmt.setTime(2, turno.getHoraInicio());
            pstmt.setTime(3, turno.getHoraFin());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Turno> obtenerTodosTurnos() {
        String sql = "SELECT * FROM turnos";
        List<Turno> turnos = new java.util.ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                turnos.add(new Turno(rs.getInt("id"), rs.getString("nombre"), rs.getTime("horaInicio"),
                        rs.getTime("horaFin")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turnos;
    }

    public Turno getTurnoPorId(int id) {
        String sql = "SELECT * FROM turnos WHERE id = ?";
        Turno turno = null;
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    turno = new Turno(rs.getInt("id"), rs.getString("nombre"), rs.getTime("horaInicio"),
                            rs.getTime("horaFin"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turno;
    }

    public boolean eliminarTurno(int id) {
        String sql = "DELETE FROM turnos WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarTurno(Turno turno) {
        String sql = "UPDATE turnos SET nombre = ?, horaInicio = ?, horaFin = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, turno.getNombre());
            pstmt.setTime(2, turno.getHoraInicio());
            pstmt.setTime(3, turno.getHoraFin());
            pstmt.setInt(4, turno.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

}
