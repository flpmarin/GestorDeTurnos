package com.turnos.negocio;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

import com.turnos.dto.Departamento;
import com.turnos.dao.DepartamentoDAO;
import com.turnos.dto.Asignacion;
import com.turnos.dao.AsignacionDAO;
import com.turnos.dto.Ausencia;
import com.turnos.dao.AusenciaDAO;
import com.turnos.dto.Posicion;
import com.turnos.dao.PosicionDAO;
import com.turnos.dto.Trabajador;
import com.turnos.dao.TrabajadorDAO;
import com.turnos.dto.Turno;
import com.turnos.dao.TurnoDAO;


//esta clase es la que se comunica con la base de datos
public class GestorTurnos {
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    private PosicionDAO posicionDAO = new PosicionDAO();
    private TurnoDAO turnoDAO = new TurnoDAO();
    private AusenciaDAO ausenciaDAO = new AusenciaDAO();
    private AsignacionDAO asignacionDAO = new AsignacionDAO();

    // Métodos para departamentos
    public boolean agregarDepartamento(Departamento departamento) {
        return departamentoDAO.agregarDepartamento(departamento);
    }

    public boolean eliminarDepartamento(int id) {
        return departamentoDAO.eliminarDepartamento(id);
    }

    public boolean modificarDepartamento(Departamento departamento) {
        return departamentoDAO.modificarDepartamento(departamento);
    }

    public List<Departamento> obtenerTodosDepartamentos() {
        return departamentoDAO.obtenerTodosDepartamentos();
    }

    public Departamento getDepartamentoPorId(int id) {
        return departamentoDAO.getDepartamentoPorId(id);
    }

    public void cargarDepartamentosDesdeJson(URL url) {
    Gson gson = new Gson();
    try {
        // Leer el archivo JSON y convertirlo en una lista de Departamento
        String contenido = new String(Files.readAllBytes(Paths.get(url.toURI())));
        List<Departamento> departamentos = gson.fromJson(contenido, new TypeToken<List<Departamento>>(){}.getType());

        // Guardar cada Departamento en la base de datos
        for (Departamento departamento : departamentos) {
            departamentoDAO.agregarDepartamento(departamento);
        }

        // Después de cargar los datos, recuperar todos los departamentos y imprimirlos
        List<Departamento> departamentosEnDB = departamentoDAO.obtenerTodosDepartamentos();
        for (Departamento departamento : departamentosEnDB) {
            System.out.println(departamento);
        }
    } catch (IOException | URISyntaxException e) {
        e.printStackTrace();
    }
}



    // Métodos para trabajadores
    public boolean agregarTrabajador(Trabajador trabajador) {
        return trabajadorDAO.agregarTrabajador(trabajador);
    }

    public boolean eliminarTrabajador(int id) {
        return trabajadorDAO.eliminarTrabajador(id);
    }

    public boolean modificarTrabajador(Trabajador trabajador) {
        return trabajadorDAO.modificarTrabajador(trabajador);
    }

    public List<Trabajador> obtenerTodosTrabajadores() {
        return trabajadorDAO.obtenerTodosTrabajadores();
    }

    public List<Trabajador> obtenerTrabajadoresPorDepartamento(int idDepartamento) {
        return trabajadorDAO.obtenerTrabajadoresPorDepartamento(idDepartamento);
    }

    // Métodos para posiciones
    public boolean agregarPosicion(Posicion posicion) {
        return posicionDAO.agregarPosicion(posicion);
    }

    public boolean eliminarPosicion(int id) {
        return posicionDAO.eliminarPosicion(id);
    }

    public boolean modificarPosicion(Posicion posicion) {
        return posicionDAO.modificarPosicion(posicion);
    }

    public List<Posicion> obtenerTodasPosiciones() {
        return posicionDAO.obtenerTodosPosiciones();
    }

    public List<Posicion> obtenerPosicionesPorDepartamento(int idDepartamento) {
        return posicionDAO.obtenerPosicionesPorDepartamento(idDepartamento);
    }

    // está en TraabajadorDao
    public List<Posicion> obtenerPosicionesPorTrabajador(int idTrabajador) {
        return trabajadorDAO.obtenerPosicionesPorTrabajador(idTrabajador);
    }

    // Método para asociar una posición habilitada a un trabajador
    public boolean asociarPosicionATrabajador(Trabajador trabajador, Posicion posicion) {
        try {
            // si la posición no está asignada al trabajador, la asignamos y viceversa.
            if (!trabajador.getPosiciones().contains(posicion)) {
                trabajador.getPosiciones().add(posicion);
                posicion.getTrabajadores().add(trabajador);
    
                // Actualizamos la base de datos
                PosicionDAO posicionDAO = new PosicionDAO();
                boolean exito = posicionDAO.asociarTrabajadorAPosicion(trabajador, posicion);
    
                return exito; // Devolvemos el resultado de la operación en la base de datos
            }
            return false; // La posición ya estaba asignada al trabajador
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Hubo un error
        }
    }

    // Método para retirar una posición habilitada de un trabajador
    public boolean retirarPosicionHabilitada(Trabajador trabajador, Posicion posicion) {
        try {
            // si la posición está asignada al trabajador, la retiramos y viceversa.
            if (trabajador.getPosiciones().contains(posicion)) {
                trabajador.getPosiciones().remove(posicion);
                posicion.getTrabajadores().remove(trabajador);
    
                // Actualizamos la base de datos
                PosicionDAO posicionDAO = new PosicionDAO();
                boolean exito = posicionDAO.retirarTrabajadorDePosicion(trabajador, posicion);
    
                return exito; // Devolvemos el resultado de la operación en la base de datos
            }
            return false; // La posición no estaba asignada al trabajador
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Hubo un error
        }
    }

    // Métodos para turnos

    public int agregarTurno(Turno turno1, Turno turno2) {
        return turnoDAO.agregarTurno(turno1, turno2);
    }

    public boolean eliminarTurno(int id) {
        return turnoDAO.eliminarTurno(id);
    }

    public boolean modificarTurno(Turno turno) {
        return turnoDAO.modificarTurno(turno);
    }

    public List<Turno> obtenerTodosTurnos() {
        return turnoDAO.obtenerTodosTurnos();
    }

    public Turno getTurnoPorId(int id) {
        return turnoDAO.getTurnoPorId(id);
    }

    public Turno getTurnoPorIdGrupo(int turnoIdGrupo) {
        return turnoDAO.getTurnoPorIdGrupo(turnoIdGrupo);
    }

    // Métodos para ausencias

    public boolean agregarAusencia(Ausencia ausencia) {
        return ausenciaDAO.agregarAusencia(ausencia);
    }

    public boolean eliminarAusencia(Date inicio, Date fin, int trabajador_id) {
        return ausenciaDAO.eliminarAusencia(inicio, fin, trabajador_id);
    }

    public boolean modificarAusencia(Ausencia ausencia) {
        return ausenciaDAO.modificarAusencia(ausencia);
    }

    public List<Ausencia> obtenerTodasAusencias() {
        return ausenciaDAO.obtenerTodasAusencias();
    }

    public List<Ausencia> obtenerAusenciasPorTrabajador(int idTrabajador) {
        return ausenciaDAO.obtenerAusenciasPorTrabajador(idTrabajador);
    }

   // Métodos para asignaciones
    public boolean agregarAsignacion(Asignacion asignacion) {
        return asignacionDAO.agregarAsignacion(asignacion);
    }

    public boolean eliminarAsignacion(int id) {
        return asignacionDAO.eliminarAsignacion(id);
    }

    public boolean modificarAsignacion(Asignacion asignacion) {
        return asignacionDAO.modificarAsignacion(asignacion);
    }

    public boolean eliminarAsignacionesPorTrabajador(int trabajador_id) {
        return asignacionDAO.eliminarAsignacionesPorTrabajador(trabajador_id);
    }

    public List<Asignacion> obtenerTodasAsignaciones() {
        return asignacionDAO.obtenerTodasAsignaciones();
    }

    public List<Asignacion> obtenerAsignacionesPorTrabajador(int idTrabajador) {
        return asignacionDAO.obtenerAsignacionesPorTrabajador(idTrabajador);
    }

    public List<Asignacion> obtenerAsignacionesPorTurno(int idTurno) {
        return asignacionDAO.obtenerAsignacionesPorTurno(idTurno);
    }

    public List<Asignacion> obtenerAsignacionesPorRangoFecha(Date inicio, Date fin) {
        return asignacionDAO.obtenerAsignacionesPorRangoFecha(inicio, fin);
    }




}