package com.turnos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.turnos.dto.Ausencia;
import com.turnos.dto.Posicion;
import com.turnos.dto.Trabajador;

public class TrabajadorDAO {

    // Inserta un trabajador en la base de datos sin especificar el id, ya que es
    // autoincremental, después de insertar el trabajador, se obtiene el id generado
    // y se asigna al objeto trabajador, permitiendo referenciarlo en la aplicación.
    public boolean agregarTrabajador(Trabajador trabajador) {
        String sql = "INSERT INTO trabajadores (nombre, departamento_id) VALUES (?, ?)";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, trabajador.getNombre());
            pstmt.setInt(2, trabajador.getDepartamentoId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        trabajador.setId(rs.getInt(1));
                    }
                }
            }
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Trabajador> obtenerTodosTrabajadores() {
        String sql = "SELECT * FROM trabajadores";
        List<Trabajador> trabajadores = new java.util.ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                trabajadores.add(new Trabajador(rs.getInt("id"), rs.getString("nombre"), rs.getInt("departamento_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trabajadores;
    }

    public boolean eliminarTrabajador(int id) {
        String sql = "DELETE FROM trabajadores WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar un trabajador
    public boolean modificarTrabajador(Trabajador trabajador) {
        String sql = "UPDATE trabajadores SET nombre = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, trabajador.getNombre());
            pstmt.setInt(2, trabajador.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Trabajador> obtenerTrabajadoresPorDepartamento(int idDepartamento) {
        String sql = "SELECT * FROM trabajadores WHERE departamento_id = ?";
        List<Trabajador> trabajadores = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDepartamento);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    trabajadores
                            .add(new Trabajador(rs.getInt("id"), rs.getString("nombre"), rs.getInt("departamento_id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trabajadores;
    }

    public List<Posicion> obtenerPosicionesPorTrabajador(int idTrabajador) {
        String sql = "SELECT posiciones.* FROM posiciones " +
                     "JOIN trabajadores_posiciones ON posiciones.id = trabajadores_posiciones.posicion_id " +
                     "WHERE trabajadores_posiciones.trabajador_id = ?";
        List<Posicion> posiciones = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idTrabajador);
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

    // Método para obtener las ausencias de un trabajador en una lista, se obtienen las ausencias de la base de datos.
    public List<Ausencia> obtenerAusenciasPorTrabajador(int idTrabajador) {
        String sql = "SELECT * FROM ausencias WHERE trabajador_id = ?";
        List<Ausencia> ausencias = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idTrabajador);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Ausencia ausencia = new Ausencia();
                    ausencia.setTrabajador_id(rs.getInt("trabajador_id"));
                    ausencia.setInicio(rs.getDate("inicio"));
                    ausencia.setFin(rs.getDate("fin"));
                    ausencia.setMotivo(rs.getString("motivo"));
                    ausencias.add(ausencia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ausencias;
    }
}
