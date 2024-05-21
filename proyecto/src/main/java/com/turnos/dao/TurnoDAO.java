package com.turnos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;

import com.turnos.dto.Turno;

public class TurnoDAO {

    // Método para agregar un turno a la base de datos. Devuelve el id del turno agregado. Si no se pudo agregar, devuelve -1. 
    // Si se agrega un turno que pertenece a un grupo de turnos, se debe pasar un segundo turno con el mismo id de grupo.
    // Si no se pasa un segundo turno, se debe pasar null. y el turno se agrega como un grupo de un solo turno.

    public int agregarTurno(Turno turno1, Turno turno2) {
        // SQL query para insertar un nuevo turno en la base de datos
        String sql = "INSERT INTO turnos (nombre, horaInicio, horaFin) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE turnos SET turnoIdGrupo = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Establecemos los valores para los parámetros de la sentencia SQL
            pstmt.setString(1, turno1.getNombre());
            pstmt.setTime(2, turno1.getHoraInicio());
            pstmt.setTime(3, turno1.getHoraFin());
            // Ejecutamos la sentencia SQL
            pstmt.executeUpdate();
            // Obtenemos las claves generadas (en este caso, el id del turno insertado)
            ResultSet rs = pstmt.getGeneratedKeys();
            // Si se generó una clave, la obtenemos y la asignamos al turno
            if (rs.next()) {
                int id = rs.getInt(1);
                rs.close(); // Cerramos el ResultSet
                pstmt.close(); // Cerramos el PreparedStatement
                if (turno2 != null) {
                    PreparedStatement pstmt2 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Creamos un nuevo PreparedStatement
                    pstmt2.setString(1, turno2.getNombre());
                    pstmt2.setTime(2, turno2.getHoraInicio());
                    pstmt2.setTime(3, turno2.getHoraFin());
                    pstmt2.executeUpdate();
                    ResultSet rs2 = pstmt2.getGeneratedKeys();
                    if (rs2.next()) {
                        int id2 = rs2.getInt(1);
                        rs2.close(); // Cerramos el segundo ResultSet
                        pstmt2.close(); // Cerramos el segundo PreparedStatement
                        PreparedStatement pstmtUpdate1 = conn.prepareStatement(sqlUpdate);
                        pstmtUpdate1.setInt(1, id);
                        pstmtUpdate1.setInt(2, id);
                        pstmtUpdate1.executeUpdate();
                        pstmtUpdate1.close();
                        PreparedStatement pstmtUpdate2 = conn.prepareStatement(sqlUpdate);
                        pstmtUpdate2.setInt(1, id);
                        pstmtUpdate2.setInt(2, id2);
                        pstmtUpdate2.executeUpdate();
                        pstmtUpdate2.close();
                    }
                } else {
                    PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate);
                    pstmtUpdate.setInt(1, id);
                    pstmtUpdate.setInt(2, id);
                    pstmtUpdate.executeUpdate();
                    pstmtUpdate.close();
                }
                return id; // Devolvemos el id del turno insertado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Si algo salió mal, devolvemos -1
    }


    public List<Turno> obtenerTodosTurnos() {
        String sql = "SELECT * FROM turnos";
        List<Turno> turnos = new java.util.ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                turnos.add(new Turno(rs.getInt("id"), rs.getInt("turnoIdGrupo"), rs.getString("nombre"),
                        rs.getTime("horaInicio"),
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
                    turno = new Turno(rs.getInt("id"), rs.getInt("turnoIdGrupo"), rs.getString("nombre"),
                            rs.getTime("horaInicio"),
                            rs.getTime("horaFin"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turno;
    }

    // Método para obtener un turno por el id del grupo al que pertenece el turno.
    public Turno getTurnoPorIdGrupo(int turnoIdGrupo) {
        String sql = "SELECT * FROM turnos WHERE turnoIdGrupo = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, turnoIdGrupo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Time horaInicio = rs.getTime("horaInicio");
                Time horaFin = rs.getTime("horaFin");
                int turnoIdGrupoResult = rs.getInt("turnoIdGrupo");
                return new Turno(id, turnoIdGrupoResult, nombre, horaInicio, horaFin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        String sql = "UPDATE turnos SET nombre = ?, horaInicio = ?, horaFin = ?, turnoIdGrupo = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, turno.getNombre());
            pstmt.setTime(2, turno.getHoraInicio());
            pstmt.setTime(3, turno.getHoraFin());
            pstmt.setInt(4, turno.getTurnoIdGrupo());
            pstmt.setInt(5, turno.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int obtenerNumeroDeTurnosUnicos() {
        String sql = "SELECT COUNT(DISTINCT turnoIdGrupo) FROM turnos";
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

}
