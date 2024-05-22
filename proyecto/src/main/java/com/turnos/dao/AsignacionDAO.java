package com.turnos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.turnos.dto.Asignacion;

public class AsignacionDAO {
    public boolean agregarAsignacion(Asignacion asignacion) {
        String sql = "INSERT INTO asignaciones (fecha_inicio, fecha_fin, hora_inicio, hora_fin, trabajador_id, turno_id, posicion_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, asignacion.getFechaInicio());
            pstmt.setDate(2, asignacion.getFechaFin());
            pstmt.setTime(3, asignacion.getHoraInicio());
            pstmt.setTime(4, asignacion.getHoraFin());
            pstmt.setInt(2, asignacion.getTrabajadorId());
            pstmt.setInt(3, asignacion.getTurnoId());
            pstmt.setInt(4, asignacion.getPosicionId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarAsignacion(int id) {
        String sql = "DELETE FROM asignaciones WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarAsignacion(Asignacion asignacion) {
        String sql = "UPDATE asignaciones SET fecha_inicio = ?, fecha_fin = ?, hora_inicio = ?, hora_fin = ?, trabajador_id = ?, turno_id = ?, posicion_id = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, asignacion.getFechaInicio());
            pstmt.setDate(2, asignacion.getFechaFin());
            pstmt.setTime(3, asignacion.getHoraInicio());
            pstmt.setTime(4, asignacion.getHoraFin());
            pstmt.setInt(5, asignacion.getTrabajadorId());
            pstmt.setInt(6, asignacion.getTurnoId());
            pstmt.setInt(7, asignacion.getPosicionId());
            pstmt.setInt(8, asignacion.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarAsignacionesPorTrabajador(int trabajador_id) {
        String sql = "DELETE FROM asignaciones WHERE trabajador_id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trabajador_id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarAsignacionesPorTurno(int turno_id) {
        String sql = "DELETE FROM asignaciones WHERE turno_id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, turno_id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Asignacion> obtenerTodasAsignaciones() {
        String sql = "SELECT * FROM asignaciones";
        List<Asignacion> asignaciones = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Asignacion asignacion = new Asignacion();
                asignacion.setId(rs.getInt("id"));
                asignacion.setFechaInicio(rs.getDate("fecha_inicio"));
                asignacion.setFechaFin(rs.getDate("fecha_fin"));
                asignacion.setHoraInicio(rs.getTime("hora_inicio"));
                asignacion.setHoraFin(rs.getTime("hora_fin"));
                asignacion.setTrabajadorId(rs.getInt("trabajador_id"));
                asignacion.setTurnoId(rs.getInt("turno_id"));
                asignacion.setPosicionId(rs.getInt("posicion_id"));
                asignaciones.add(asignacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asignaciones;
    }

    public List<Asignacion> obtenerAsignacionesPorTrabajador(int trabajador_id) {
        String sql = "SELECT * FROM asignaciones WHERE trabajador_id = ?";
        List<Asignacion> asignaciones = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trabajador_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Asignacion asignacion = new Asignacion();
                    asignacion.setId(rs.getInt("id"));
                    asignacion.setFechaInicio(rs.getDate("fecha_inicio"));
                    asignacion.setFechaFin(rs.getDate("fecha_fin"));
                    asignacion.setHoraInicio(rs.getTime("hora_inicio"));
                    asignacion.setHoraFin(rs.getTime("hora_fin"));
                    asignacion.setTrabajadorId(rs.getInt("trabajador_id"));
                    asignacion.setTurnoId(rs.getInt("turno_id"));
                    asignacion.setPosicionId(rs.getInt("posicion_id"));
                    asignaciones.add(asignacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asignaciones;
    }

    public List<Asignacion> obtenerAsignacionesPorTurno(int turno_id) {
        String sql = "SELECT * FROM asignaciones WHERE turno_id = ?";
        List<Asignacion> asignaciones = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, turno_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Asignacion asignacion = new Asignacion();
                    asignacion.setId(rs.getInt("id"));
                    asignacion.setFechaInicio(rs.getDate("fecha_inicio"));
                    asignacion.setFechaFin(rs.getDate("fecha_fin"));
                    asignacion.setHoraInicio(rs.getTime("hora_inicio"));
                    asignacion.setHoraFin(rs.getTime("hora_fin"));
                    asignacion.setTrabajadorId(rs.getInt("trabajador_id"));
                    asignacion.setTurnoId(rs.getInt("turno_id"));
                    asignacion.setPosicionId(rs.getInt("posicion_id"));
                    asignaciones.add(asignacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asignaciones;
    }

    public Asignacion obtenerAsignacionPorId(int id) {
        String sql = "SELECT * FROM asignaciones WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Asignacion asignacion = new Asignacion();
                    asignacion.setId(rs.getInt("id"));
                    asignacion.setFechaInicio(rs.getDate("fecha_inicio"));
                    asignacion.setFechaFin(rs.getDate("fecha_fin"));
                    asignacion.setHoraInicio(rs.getTime("hora_inicio"));
                    asignacion.setHoraFin(rs.getTime("hora_fin"));
                    asignacion.setTrabajadorId(rs.getInt("trabajador_id"));
                    asignacion.setTurnoId(rs.getInt("turno_id"));
                    asignacion.setPosicionId(rs.getInt("posicion_id"));
                    return asignacion;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Asignacion> obtenerAsignacionesPorRangoFecha(java.sql.Date inicio, java.sql.Date fin) {
        String sql = "SELECT * FROM asignaciones WHERE fecha BETWEEN ? AND ?";
        List<Asignacion> asignaciones = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, inicio);
            pstmt.setDate(2, fin);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Asignacion asignacion = new Asignacion();
                    asignacion.setId(rs.getInt("id"));
                    asignacion.setFechaInicio(rs.getDate("fecha_inicio"));
                    asignacion.setFechaFin(rs.getDate("fecha_fin"));
                    asignacion.setHoraInicio(rs.getTime("hora_inicio"));
                    asignacion.setHoraFin(rs.getTime("hora_fin"));
                    asignacion.setTrabajadorId(rs.getInt("trabajador_id"));
                    asignacion.setTurnoId(rs.getInt("turno_id"));
                    asignacion.setPosicionId(rs.getInt("posicion_id"));
                        
                    asignaciones.add(asignacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asignaciones;
    }

    public List<Asignacion> obtenerAsignacionesPorFecha(java.sql.Date fecha) {
        String sql = "SELECT * FROM asignaciones WHERE fecha = ?";
        List<Asignacion> asignaciones = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, fecha);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Asignacion asignacion = new Asignacion();
                    asignacion.setId(rs.getInt("id"));
                    asignacion.setFechaInicio(rs.getDate("fecha_inicio"));
                    asignacion.setFechaFin(rs.getDate("fecha_fin"));
                    asignacion.setHoraInicio(rs.getTime("hora_inicio"));
                    asignacion.setHoraFin(rs.getTime("hora_fin"));
                    asignacion.setTrabajadorId(rs.getInt("trabajador_id"));
                    asignacion.setTurnoId(rs.getInt("turno_id"));
                    asignacion.setPosicionId(rs.getInt("posicion_id"));
                    asignaciones.add(asignacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asignaciones;
    }
}
