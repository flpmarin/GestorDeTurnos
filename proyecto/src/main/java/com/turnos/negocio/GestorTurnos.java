package com.turnos.negocio;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.turnos.dto.Preferencia;
import com.turnos.dao.PreferenciaDAO;

//esta clase es la que se comunica con la base de datos
public class GestorTurnos {
    private static final int MAX_HORAS_SEMANALES = 40; // Máximo de horas semanales permitidas

    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    private PosicionDAO posicionDAO = new PosicionDAO();
    private TurnoDAO turnoDAO = new TurnoDAO();
    private AusenciaDAO ausenciaDAO = new AusenciaDAO();
    private AsignacionDAO asignacionDAO = new AsignacionDAO();
    private PreferenciaDAO preferenciaDAO = new PreferenciaDAO();

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

    public Departamento obtenerDepartamentoPorTrabajador(Trabajador trabajador) {
        return departamentoDAO.obtenerDepartamentoPorTrabajador(trabajador.getId());
    }

    public void cargarDepartamentosDesdeJson(URL url) {
        Gson gson = new Gson();
        try {
            // Leer el archivo JSON y convertirlo en una lista de Departamento
            String contenido = new String(Files.readAllBytes(Paths.get(url.toURI())));
            List<Departamento> departamentos = gson.fromJson(contenido, new TypeToken<List<Departamento>>() {
            }.getType());

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

    public int calcularCantidadPosiciones() {
        return posicionDAO.calcularCantidadPosiciones();
    }

    public String obtenerNombrePosicionPorId(int id) {
        return posicionDAO.obtenerNombrePosicionPorId(id);
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

    public int NumeroDeTrabajadoresPorDepartamento(int idDepartamento) {
        return trabajadorDAO.NumeroDeTrabajadoresPorDepartamento(idDepartamento);
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

    public Turno obtenerTurnoPorId(int id) {
        return turnoDAO.getTurnoPorId(id);
    }

    public Turno obtenerTurnoPorIdGrupo(int turnoIdGrupo) {
        return turnoDAO.getTurnoPorIdGrupo(turnoIdGrupo);
    }

    public int obtenerNumeroDeTurnosUnicos() {
        return turnoDAO.obtenerNumeroDeTurnosUnicos();
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

    public List<Asignacion> obtenerAsignacionesPorFecha(Date fecha) {
        return asignacionDAO.obtenerAsignacionesPorFecha(fecha);
    }

    // Métodos para preferencias
    public boolean agregarPreferencia(int trabajadorId, int turnoId) {
        Preferencia preferencia = new Preferencia();
        preferencia.setTrabajadorId(trabajadorId);
        preferencia.setTurnoId(turnoId);
        return preferenciaDAO.agregarPreferencia(preferencia);
    }

    public boolean eliminarPreferencia(int id) {
        return preferenciaDAO.eliminarPreferencia(id);
    }

    public boolean modificarPreferencia(int id, int trabajadorId, int turnoId) {
        Preferencia preferencia = new Preferencia();
        preferencia.setId(id);
        preferencia.setTrabajadorId(trabajadorId);
        preferencia.setTurnoId(turnoId);
        return preferenciaDAO.modificarPreferencia(preferencia);
    }

    public boolean eliminarPreferenciasPorTrabajador(int trabajadorId) {
        return preferenciaDAO.eliminarPreferenciasPorTrabajador(trabajadorId);
    }

    public List<Preferencia> obtenerPreferenciasPorTrabajador(int trabajadorId) {
        return preferenciaDAO.obtenerPreferenciasPorTrabajador(trabajadorId);
    }

    public List<Preferencia> obtenerPreferenciasPorTurno(int turnoId) {
        // This method does not exist in your PreferenciaDAO class
        // You need to implement it there first
        return new ArrayList<>();
    }

    public List<Preferencia> obtenerTodasPreferencias() {
        return preferenciaDAO.obtenerTodasPreferencias();
    }

    // generacion de turnos automaticos

    private int calcularHorasTurno(int turnoId, List<Turno> todosLosTurnos) {
        for (Turno turno : todosLosTurnos) {
            if (turno.getId() == turnoId) {
                if (turnoCruzaMedianoche(turnoId, todosLosTurnos)) {
                    long horasHastaMedianoche = ChronoUnit.HOURS.between(turno.getHoraInicio().toLocalTime(),
                            LocalTime.MAX);
                    long horasDesdeMedianoche = ChronoUnit.HOURS.between(LocalTime.MIN,
                            turno.getHoraFin().toLocalTime());
                    return (int) (horasHastaMedianoche + horasDesdeMedianoche);
                } else {
                    return (int) ChronoUnit.HOURS.between(turno.getHoraInicio().toLocalTime(),
                            turno.getHoraFin().toLocalTime());
                }
            }
        }
        return 0; // Devuelve 0 si no se encuentra el turno
    }

    private boolean validarAsignacion(Trabajador trabajador, Turno turno,
            Map<Integer, List<Asignacion>> asignacionesPorTrabajador) {
        // Convert Time to LocalTime
        LocalTime localTimeInicioTurno = turno.getHoraInicio().toLocalTime();

        // Verifica si el trabajador ya tiene un turno asignado para esa fecha
        return !asignacionesPorTrabajador.containsKey(trabajador.getId())
                || !asignacionesPorTrabajador.get(trabajador.getId()).stream()
                        .anyMatch(t -> t.getHoraInicio().toLocalTime().equals(localTimeInicioTurno));
    }

    public void generarAsignacionAutomatica(int departamentoId, Date fechaInicio, Date fechaFin) {
        // Departamento departamento = departamentoDAO.getDepartamentoPorId(departamentoId);
        List<Trabajador> trabajadores = trabajadorDAO.obtenerTrabajadoresPorDepartamento(departamentoId);
        List<Posicion> posiciones = posicionDAO.obtenerPosicionesPorDepartamento(departamentoId);
        List<Turno> turnos = turnoDAO.obtenerTodosTurnos();

        Map<Integer, List<Asignacion>> asignacionesPorTrabajador = new HashMap<>();
        Map<Date, List<Trabajador>> trabajadoresSinAsignacion = new HashMap<>();

        // Inicializar la lista de asignaciones por trabajador
        for (Trabajador trabajador : trabajadores) {
            asignacionesPorTrabajador.put(trabajador.getId(),
                    asignacionDAO.obtenerAsignacionesPorTrabajador(trabajador.getId()));
        }

        Trabajador ultimoTrabajadorNoche = null;

        // Asignar turnos a cada trabajador
        for (Date fecha = fechaInicio; !fecha.after(fechaFin); fecha = Date.valueOf(fecha.toLocalDate().plusDays(1))) {
            for (Turno turno : turnos) {
                boolean turnoAsignado = false;

                if (turnoCruzaMedianoche(turno.getId(), turnos) && ultimoTrabajadorNoche != null
                        && validarAsignacion(ultimoTrabajadorNoche, turno, fecha, asignacionesPorTrabajador)) {
                    asignarTurno(ultimoTrabajadorNoche, turno, fecha, asignacionesPorTrabajador, posiciones);
                    turnoAsignado = true;
                } else {
                    for (Trabajador trabajador : trabajadores) {
                        if (validarAsignacion(trabajador, turno, fecha, asignacionesPorTrabajador)) {
                            asignarTurno(trabajador, turno, fecha, asignacionesPorTrabajador, posiciones);
                            turnoAsignado = true;

                            if (turnoCruzaMedianoche(turno.getId(), turnos)) {
                                ultimoTrabajadorNoche = trabajador;
                            }

                            break;
                        }
                    }
                }

                if (!turnoAsignado) {
                    // Agregar el trabajador al mapa de trabajadores sin asignación para ese día
                    trabajadoresSinAsignacion.computeIfAbsent(fecha, k -> new ArrayList<>()).add(ultimoTrabajadorNoche);
                }
            }
        }

        // Al final de la semana:
        // Al final de la semana:
        for (Map.Entry<Date, List<Trabajador>> entry : trabajadoresSinAsignacion.entrySet()) {
            for (Trabajador trabajador : entry.getValue()) {
                // Calcular las horas que el trabajador ha trabajado en los últimos 7 días
                int horasTrabajadas = calcularHorasTrabajadas(trabajador.getId(), entry.getKey(),
                        asignacionesPorTrabajador);

                // Si el trabajador ha trabajado menos de 40 horas:
                if (horasTrabajadas < 40) {
                    // Asignar al trabajador a los turnos sin posición hasta que haya trabajado 40 horas
                    while (horasTrabajadas < 40) {
                        Turno turnoSinPosicion = encontrarTurnoSinPosicionParaTrabajador(trabajador, entry.getKey(), turnos, asignacionesPorTrabajador);
                        if (turnoSinPosicion != null) {
                            Asignacion nuevaAsignacion = asignarTurno(trabajador, turnoSinPosicion, entry.getKey(), asignacionesPorTrabajador, posiciones);
                            List<Asignacion> asignacionesDelTrabajador = asignacionesPorTrabajador.get(trabajador.getId());
                            if (asignacionesDelTrabajador == null) {
                                asignacionesDelTrabajador = new ArrayList<>();
                                asignacionesPorTrabajador.put(trabajador.getId(), asignacionesDelTrabajador);
                            }
                            asignacionesDelTrabajador.add(nuevaAsignacion);
                            horasTrabajadas += turnoSinPosicion.getDuracion();
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    // Método para asignar un turno a un trabajador
    private Asignacion asignarTurno(Trabajador trabajador, Turno turno, Date fecha,
            Map<Integer, List<Asignacion>> asignacionesPorTrabajador, List<Posicion> posiciones) {

        // Validar la asignación antes de proceder
        if (!validarAsignacion(trabajador, turno, asignacionesPorTrabajador)) {
            throw new RuntimeException("El trabajador ya tiene un turno asignado a esa hora.");
        }

        Asignacion asignacion = new Asignacion();
        asignacion.setFechaInicio(fecha);
        asignacion.setFechaFin(fecha);
        asignacion.setHoraInicio(turno.getHoraInicio());
        asignacion.setHoraFin(turno.getHoraFin());
        asignacion.setTrabajadorId(trabajador.getId());
        asignacion.setTurnoId(turno.getId());

        Posicion posicion = encontrarPosicionDisponible(posiciones, trabajador);
        if (posicion != null) {
            asignacion.setPosicionId(posicion.getId());
        } else {
            // Manejar el caso en que no se encuentre una posición disponible
            asignacion.setPosicionId(-1); // o lanzar una excepción en su lugar si es más apropiado
        }

        asignacionDAO.agregarAsignacion(asignacion);
        asignacionesPorTrabajador.get(trabajador.getId()).add(asignacion);

        // Devolver la asignación
        return asignacion;
    }

    private boolean turnoCruzaMedianoche(int turnoId, List<Turno> todosLosTurnos) {
        int turnoIdGrupo = -1;
        for (Turno turno : todosLosTurnos) {
            if (turno.getId() == turnoId) {
                turnoIdGrupo = turno.getTurnoIdGrupo();
                break;
            }
        }
        if (turnoIdGrupo == -1) {
            return false; // Devuelve false si no se encuentra el turno
        }
        for (Turno otroTurno : todosLosTurnos) {
            if (otroTurno.getId() != turnoId && otroTurno.getTurnoIdGrupo() == turnoIdGrupo) {
                return true;
            }
        }
        return false;
    }

    // Método para validar una asignación, Este método valida si un turno puede ser
    // asignado a un trabajador. Verifica las preferencias del trabajador, las
    // ausencias, si el trabajador ya tiene un turno en el mismo día, si la
    // asignación del turno excedería las horas máximas semanales y si el trabajador
    // está habilitado para todas las posiciones requeridas.
    private boolean validarAsignacion(Trabajador trabajador, Turno turno, Date fecha,
            Map<Integer, List<Asignacion>> asignacionesPorTrabajador) {
        List<Asignacion> asignaciones = asignacionesPorTrabajador.get(trabajador.getId());

        // Validar inactividad (preferencia)
        if (obtenerPreferenciasPorTrabajador(trabajador.getId()) != null) {
            return false;
        }

        // Validar ausencias
        for (Ausencia ausencia : ausenciaDAO.obtenerAusenciasPorTrabajador(trabajador.getId())) {
            if (!fecha.before(ausencia.getInicio()) && !fecha.after(ausencia.getFin())) {
                return false;
            }
        }

        // Validar asignación única por día
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.getFechaInicio().equals(fecha)) {
                return false;
            }
        }

        // Validar horas asignadas
        int horasAsignadas = calcularHorasAsignadas(trabajador.getId(), asignaciones);
        List<Turno> todosLosTurnos = obtenerTodosTurnos(); // You need to implement this method
        int horasTurno = calcularHorasTurno(turno.getId(), todosLosTurnos);
        if (horasAsignadas + horasTurno > MAX_HORAS_SEMANALES) {
            return false;
        }

        // Validar si el trabajador está habilitado para todas las posiciones requeridas
        // por su departamento
        Departamento departamentoTrabajador = obtenerDepartamentoPorTrabajador(trabajador); // You need to implement
                                                                                            // this
                                                                                            // method
        boolean estaHabilitadoParaTodasPosiciones = departamentoTrabajador.getPosiciones().values().stream()
                .allMatch(posicion -> trabajador.getPosiciones().contains(posicion));
        if (!estaHabilitadoParaTodasPosiciones) {
            return false;
        }

        return true;
    }

    private boolean esTrabajadorHabilitado(Posicion posicion, Trabajador trabajador) {
        return trabajador.getPosiciones().contains(posicion);
    }

    // Método para encontrar una posición disponible para un trabajador
    private Posicion encontrarPosicionDisponible(List<Posicion> posiciones, Trabajador trabajador) {
        for (Posicion posicion : posiciones) {
            if (posicion.getTrabajadores().contains(trabajador) && esTrabajadorHabilitado(posicion, trabajador)) {
                return posicion;
            }
        }
        return null;
    }

    // Método para calcular las horas asignadas a un trabajador
    private int calcularHorasAsignadas(int trabajadorId, List<Asignacion> asignaciones) {
        int totalHoras = 0;
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.getTrabajadorId() == trabajadorId) {
                totalHoras += calcularHorasTurno(asignacion.getTurnoId(), obtenerTodosTurnos());
            }
        }
        return totalHoras;
    }

    // Método para calcular las horas trabajadas por un trabajador en una fecha
    private int calcularHorasTrabajadas(int trabajadorId, Date fecha,
        Map<Integer, List<Asignacion>> asignacionesPorTrabajador) {
    LocalDate fechaLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    List<Asignacion> asignaciones = asignacionesPorTrabajador.get(trabajadorId);
    return calcularHorasTrabajadasUltimos7Dias(trabajadorId, fechaLocal, asignaciones);
}

    // Método para encontrar un turno sin asignar para un trabajador
    private Turno encontrarTurnoSinPosicionParaTrabajador(Trabajador trabajador, Date fecha, List<Turno> turnos, Map<Integer, List<Asignacion>> asignacionesPorTrabajador) {
        for (Turno turno : turnos) {
            if (turnoEstaSinAsignar(turno, fecha)
                    && validarAsignacion(trabajador, turno, asignacionesPorTrabajador)) {
                return turno;
            }
        }
        return null;
    }

    // Método para calcular las horas trabajadas por un trabajador en los últimos 7
    // días.
    private int calcularHorasTrabajadasUltimos7Dias(int trabajadorId, LocalDate fecha, List<Asignacion> asignaciones) {
        LocalDate sevenDaysAgo = fecha.minusDays(7);
        int totalHoras = 0;

        for (Asignacion asignacion : asignaciones) {
            if (asignacion.getTrabajadorId() == trabajadorId) {
                Date inicioTurno = asignacion.getFechaInicio();
                Date finTurno = asignacion.getFechaFin();

                Instant inicioTurnoInstant = inicioTurno.toInstant();
                Instant finTurnoInstant = finTurno.toInstant();

                LocalDate inicioTurnoLocalDate = inicioTurnoInstant.atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate finTurnoLocalDate = finTurnoInstant.atZone(ZoneId.systemDefault()).toLocalDate();

                if (!inicioTurnoLocalDate.isBefore(sevenDaysAgo) && !finTurnoLocalDate.isAfter(fecha)) {
                    long horas = Duration.between(inicioTurnoInstant, finTurnoInstant).toHours();
                    totalHoras += horas;
                }
            }
        }

        return totalHoras;
    }

    private boolean turnoEstaSinAsignar(Turno turno, Date fecha) {
        List<Asignacion> asignaciones = obtenerAsignacionesPorFecha(fecha);
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.getTurnoId() == turno.getId()) {
                return false;
            }
        }
        return true;
    }

}