package com.turnos.ui;

import com.turnos.dto.Departamento;
import com.turnos.dto.Trabajador;
import com.turnos.dto.Posicion;
import com.turnos.negocio.GestorTurnos;
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TurnosGUI extends JFrame {
    private GestorTurnos gestorTurnos = new GestorTurnos(); // Instancia de la clase GestorTurnos en lugar de llamar a
                                                            // los métodos del paquete dao, permite separar la lógica de
                                                            // negocio de la lógica de acceso a datos.
    private JComboBox<Departamento> comboDepartamentoEliminar, comboDepartamentoModificar,
            comboDepartamentosParaAgregarTrabajador, comboDepartamentosParaAgregarPosicion,
            comboDepartamentosModTrabajador;
    private JComboBox<Trabajador> comboTrabajadorEliminar, comboTrabajadorModificar;
    private JComboBox<Posicion> comboPosicionEliminar, comboPosicionModificar,comboPosicionesHabilitadas, comboPosicionesNoHabilitadas;
    private JTextField txtDepartamentoNombre, txtModificarNombreDepartamento, txtTrabajadorNombre,
            txtModificarNombreTrabajador, txtPosicionNombre, txtModificarNombrePosicion;
    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);
    private static final String MSG_EXITO = "%s exitosamente.";
    private static final String MSG_ERROR = "Error al %s.";

    public TurnosGUI() {
        super("Sistema de Gestión de Turnos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initUI();
        cargarDepartamentos();
        cargarTodasPosiciones();
        cargarTodosTrabajadores();
    }

    private void initUI() {
        initAgregarDepartamentoPanel();
        initEliminarDepartamentoPanel();
        initModificarDepartamentoPanel();

        initAgregarTrabajadorPanel();
        initEliminarTrabajadorPanel();
        initModificarTrabajadorPanel();

        initAgregarPosicionPanel();
        initEliminarPosicionPanel();
        initModificarPosicionPanel();

        initMenu();
        add(cards, BorderLayout.CENTER);
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");

        // Submenu para departamentos
        JMenu submenuDepartamentos = new JMenu("Departamentos");
        JMenuItem miAgregarDepartamento = new JMenuItem("Agregar");
        JMenuItem miEliminarDepartamento = new JMenuItem("Eliminar");
        JMenuItem miModificarDepartamento = new JMenuItem("Modificar");

        miAgregarDepartamento.addActionListener(e -> cardLayout.show(cards, "AgregarDepartamento"));
        miEliminarDepartamento.addActionListener(e -> cardLayout.show(cards, "EliminarDepartamento"));
        miModificarDepartamento.addActionListener(e -> cardLayout.show(cards, "ModificarDepartamento"));

        submenuDepartamentos.add(miAgregarDepartamento);
        submenuDepartamentos.add(miEliminarDepartamento);
        submenuDepartamentos.add(miModificarDepartamento);
        menu.add(submenuDepartamentos);

        // Submenu para trabajadores
        JMenu submenuTrabajadores = new JMenu("Trabajadores");
        JMenuItem miAgregarTrabajador = new JMenuItem("Agregar");
        JMenuItem miEliminarTrabajador = new JMenuItem("Eliminar");
        JMenuItem miModificarTrabajador = new JMenuItem("Modificar");

        miAgregarTrabajador.addActionListener(e -> cardLayout.show(cards, "AgregarTrabajador"));
        miEliminarTrabajador.addActionListener(e -> cardLayout.show(cards, "EliminarTrabajador"));
        miModificarTrabajador.addActionListener(e -> cardLayout.show(cards, "ModificarTrabajador"));

        submenuTrabajadores.add(miAgregarTrabajador);
        submenuTrabajadores.add(miEliminarTrabajador);
        submenuTrabajadores.add(miModificarTrabajador);
        menu.add(submenuTrabajadores);

        // Submenu para posiciones
        JMenu submenuPosiciones = new JMenu("Posiciones");
        JMenuItem miAgregarPosicion = new JMenuItem("Agregar");
        JMenuItem miEliminarPosicion = new JMenuItem("Eliminar");
        JMenuItem miModificarPosicion = new JMenuItem("Modificar");

        miAgregarPosicion.addActionListener(e -> cardLayout.show(cards, "AgregarPosicion"));
        miEliminarPosicion.addActionListener(e -> cardLayout.show(cards, "EliminarPosicion"));
        miModificarPosicion.addActionListener(e -> cardLayout.show(cards, "ModificarPosicion"));

        submenuPosiciones.add(miAgregarPosicion);
        submenuPosiciones.add(miEliminarPosicion);
        submenuPosiciones.add(miModificarPosicion);
        menu.add(submenuPosiciones);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
    // seccion departamentos

    // panel para agregar un departamento
    private void initAgregarDepartamentoPanel() {
        JPanel panelAgregar = new JPanel(new GridLayout(0, 2));
        txtDepartamentoNombre = new JTextField();
        JButton btnAgregarDepartamento = new JButton("Agregar Departamento");
        btnAgregarDepartamento.addActionListener(e -> agregarDepartamento());
        panelAgregar.add(new JLabel("Nombre:"));
        panelAgregar.add(txtDepartamentoNombre);
        panelAgregar.add(new JLabel()); // Componente invisible para ocupar la celda
        panelAgregar.add(btnAgregarDepartamento);
        cards.add(panelAgregar, "AgregarDepartamento");
    }

    // panel para eliminar un departamento
    private void initEliminarDepartamentoPanel() {
        JPanel panelEliminar = new JPanel(new GridLayout(0, 2));
        comboDepartamentoEliminar = new JComboBox<>();
        List<Departamento> departamentos = cargarDepartamentos();
        for (Departamento departamento : departamentos) {
            comboDepartamentoEliminar.addItem(departamento);
        }
        JButton btnEliminarDepartamento = new JButton("Eliminar Departamento");
        btnEliminarDepartamento.addActionListener(e -> eliminarDepartamento());
        panelEliminar.add(new JLabel("Departamento:"));
        panelEliminar.add(comboDepartamentoEliminar);
        panelEliminar.add(new JLabel()); // Componente invisible para ocupar la celda
        panelEliminar.add(btnEliminarDepartamento);
        cards.add(panelEliminar, "EliminarDepartamento");
    }

    // panel para modificar un departamento
    private void initModificarDepartamentoPanel() {
        JPanel panelModificar = new JPanel(new GridLayout(0, 2));
        comboDepartamentoModificar = new JComboBox<>();
        List<Departamento> departamentos = cargarDepartamentos();
        for (Departamento departamento : departamentos) {
            comboDepartamentoModificar.addItem(departamento);
        }
        txtModificarNombreDepartamento = new JTextField();
        JButton btnModificarDepartamento = new JButton("Modificar Departamento");
        btnModificarDepartamento.addActionListener(e -> modificarDepartamento());
        panelModificar.add(new JLabel("Departamento:"));
        panelModificar.add(comboDepartamentoModificar);
        panelModificar.add(new JLabel("Nuevo Nombre:"));
        panelModificar.add(txtModificarNombreDepartamento);
        panelModificar.add(new JLabel()); // Componente invisible para ocupar la celda
        panelModificar.add(btnModificarDepartamento);
        cards.add(panelModificar, "ModificarDepartamento");
    }

    // Método para cargar los departamentos en los combos, es útil como lista pero
    // no para modificar la interfaz gráfica de otros elementos asociados.
    public List<Departamento> cargarDepartamentos() {
        try {
            return gestorTurnos.obtenerTodosDepartamentos();
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la pila de llamadas de la excepción
            JOptionPane.showMessageDialog(this, "Error al cargar los departamentos: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list on error
        }
    }

    // Método para cargar los departamentos para el comboBox que esta en el panel de
    // modificar trabajador, permite actualizar la interfaz gráfica con el
    // departamento seleccionado y sus asociaciones.
    public void cargarDepartamentosModTrabajador() {
        try {
            List<Departamento> departamentos = gestorTurnos.obtenerTodosDepartamentos();
            comboDepartamentosModTrabajador.removeAllItems(); // Limpia los items del combo
            for (Departamento departamento : departamentos) {
                comboDepartamentosModTrabajador.addItem(departamento);
            }
            // revalidar y repintar el combo
            comboDepartamentosModTrabajador.revalidate();
            comboDepartamentosModTrabajador.repaint();
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la pila de llamadas de la excepción
            JOptionPane.showMessageDialog(this, "Error al cargar los departamentos: " + e.getMessage());
        }
    }

    // Este método toma el resultado de una operación (true/false),
    // el nombre de la operación (agregar, eliminar, modificar, etc.)
    // y una función para actualizar la interfaz de usuario. Muestra un mensaje de
    // éxito o error dependiendo del resultado de la operación.
    private void manejarRespuestaOperacion(boolean resultado, String operacion, Runnable cargarDatos) {
        String mensajeExito = String.format(MSG_EXITO, operacion);
        String mensajeError = String.format(MSG_ERROR, operacion);
        if (resultado) {
            JOptionPane.showMessageDialog(this, mensajeExito);
            if (cargarDatos != null) {
                cargarDatos.run();// Actualiza la interfaz de usuario.
            }
        } else {
            JOptionPane.showMessageDialog(this, mensajeError);
        }
    }

    private void agregarDepartamento() {
        String nombre = txtDepartamentoNombre.getText();
        if (!nombre.isEmpty()) {
            Departamento departamento = new Departamento(0, nombre);
            boolean resultado = gestorTurnos.agregarDepartamento(departamento);
            manejarRespuestaOperacion(resultado, "agregar el departamento", () -> {
                if (resultado) {
                    List<Departamento> departamentos = cargarDepartamentos();
                    comboDepartamentoEliminar.removeAllItems();
                    comboDepartamentoModificar.removeAllItems();
                    comboDepartamentosParaAgregarTrabajador.removeAllItems();
                    comboDepartamentosParaAgregarPosicion.removeAllItems();
                    comboDepartamentosModTrabajador.removeAllItems();
                    for (Departamento dep : departamentos) {
                        comboDepartamentoEliminar.addItem(dep);
                        comboDepartamentoModificar.addItem(dep);
                        comboDepartamentosParaAgregarTrabajador.addItem(dep);
                        comboDepartamentosParaAgregarPosicion.addItem(dep);
                        comboDepartamentosModTrabajador.addItem(dep);
                    }
                }
            });
            txtDepartamentoNombre.setText("");
        }
    }

    private void eliminarDepartamento() {
        Departamento departamento = (Departamento) comboDepartamentoEliminar.getSelectedItem();
        if (departamento != null) {
            boolean resultado = gestorTurnos.eliminarDepartamento(departamento.getId());
            manejarRespuestaOperacion(resultado, "eliminar el departamento", () -> {
                if (resultado) {
                    List<Departamento> departamentos = cargarDepartamentos();
                    comboDepartamentoEliminar.removeAllItems();
                    comboDepartamentoModificar.removeAllItems();
                    comboDepartamentosParaAgregarTrabajador.removeAllItems();
                    comboDepartamentosParaAgregarPosicion.removeAllItems();
                    comboDepartamentosModTrabajador.removeAll();
                    for (Departamento dep : departamentos) {
                        comboDepartamentoEliminar.addItem(dep);
                        comboDepartamentoModificar.addItem(dep);
                        comboDepartamentosParaAgregarTrabajador.addItem(dep);
                        comboDepartamentosParaAgregarPosicion.addItem(dep);
                        comboDepartamentosModTrabajador.addItem(dep);
                    }
                }
            });
        }
    }

    private void modificarDepartamento() {
        Departamento departamento = (Departamento) comboDepartamentoModificar.getSelectedItem();
        String nuevoNombre = txtModificarNombreDepartamento.getText();
        if (departamento != null && !nuevoNombre.isEmpty()) {
            departamento.setNombre(nuevoNombre);
            boolean resultado = gestorTurnos.modificarDepartamento(departamento);
            manejarRespuestaOperacion(resultado, "modificar el departamento", () -> {
                if (resultado) {
                    List<Departamento> departamentos = cargarDepartamentos();
                    comboDepartamentoEliminar.removeAllItems();
                    comboDepartamentoModificar.removeAllItems();
                    comboDepartamentosParaAgregarTrabajador.removeAllItems();
                    comboDepartamentosParaAgregarPosicion.removeAllItems();
                    comboDepartamentosModTrabajador.removeAllItems();
                    for (Departamento dep : departamentos) {
                        comboDepartamentoEliminar.addItem(dep);
                        comboDepartamentoModificar.addItem(dep);
                        comboDepartamentosParaAgregarTrabajador.addItem(dep);
                        comboDepartamentosParaAgregarPosicion.addItem(dep);
                        comboDepartamentosModTrabajador.addItem(dep);
                    }
                }
            });
        }
    }

    // seccion trabajadores

    // onDepartamentoSelected se llama cuando se selecciona un departamento en el
    // JComboBox de departamentos.
    public void onDepartamentoSelected(Departamento departamento) {
        cargarTrabajadoresPorDepartamento(departamento); // Cargar los trabajadores del departamento seleccionado
    }

    // panel para agregar un trabajador
    private void initAgregarTrabajadorPanel() {
        JPanel panelAgregar = new JPanel(new GridLayout(0, 2));
        txtTrabajadorNombre = new JTextField();
        comboDepartamentosParaAgregarTrabajador = new JComboBox<>();
        List<Departamento> departamentos = cargarDepartamentos();
        for (Departamento departamento : departamentos) {
            comboDepartamentosParaAgregarTrabajador.addItem(departamento);
        }
        JButton btnAgregarTrabajador = new JButton("Agregar Trabajador");
        btnAgregarTrabajador.addActionListener(e -> agregarTrabajador());
        panelAgregar.add(new JLabel("Nombre:"));
        panelAgregar.add(txtTrabajadorNombre);
        panelAgregar.add(new JLabel("Departamento:")); // Etiqueta para el JComboBox
        panelAgregar.add(comboDepartamentosParaAgregarTrabajador); // Agrega el JComboBox al panel
        panelAgregar.add(new JLabel()); // Componente invisible para ocupar la celda
        panelAgregar.add(btnAgregarTrabajador);
        cards.add(panelAgregar, "AgregarTrabajador");
    }

    // panel para eliminar un trabajador
    private void initEliminarTrabajadorPanel() {
        JPanel panelEliminar = new JPanel(new GridLayout(0, 2));
        comboTrabajadorEliminar = new JComboBox<>();
        JButton btnEliminarTrabajador = new JButton("Eliminar Trabajador");
        btnEliminarTrabajador.addActionListener(e -> eliminarTrabajador());
        panelEliminar.add(new JLabel("Trabajador:"));
        panelEliminar.add(comboTrabajadorEliminar);
        panelEliminar.add(new JLabel()); // Componente invisible para ocupar la celda
        panelEliminar.add(btnEliminarTrabajador);
        cards.add(panelEliminar, "EliminarTrabajador");
    }

    // panel para modificar un trabajador
    private void initModificarTrabajadorPanel() {
        JPanel panelModificar = new JPanel(new GridLayout(0, 2));
    
        comboPosicionesHabilitadas = new JComboBox<>();
        comboPosicionesNoHabilitadas = new JComboBox<>();
        JButton btnAsociarPosicion = new JButton("Asociar Posición");
        JButton btnRetirarPosicion = new JButton("Retirar Posición");
    
        comboDepartamentosModTrabajador = new JComboBox<>();
        cargarDepartamentosModTrabajador();
        // Agregar un listener al JComboBox de departamentos para cargar los
        // trabajadores del departamento seleccionado
        comboDepartamentosModTrabajador.addActionListener(e -> {
            Departamento departamentoSeleccionado = (Departamento) comboDepartamentosModTrabajador.getSelectedItem();
            if (departamentoSeleccionado != null) {
                cargarTrabajadoresPorDepartamento(departamentoSeleccionado);
            }
        });
    
        comboTrabajadorModificar = new JComboBox<>();
    
        comboTrabajadorModificar.addActionListener(e -> {
            Trabajador trabajadorSeleccionado = (Trabajador) comboTrabajadorModificar.getSelectedItem();
            if (trabajadorSeleccionado != null) {
                cargarPosicionesPorTrabajadorYDepartamento(trabajadorSeleccionado);
            }
        });
    
        txtModificarNombreTrabajador = new JTextField();
        JButton btnModificarTrabajador = new JButton("Modificar Nombre");
        btnModificarTrabajador.addActionListener(e -> modificarTrabajador());
        panelModificar.add(new JLabel("Departamento:"));
        panelModificar.add(comboDepartamentosModTrabajador);
        panelModificar.add(new JLabel("Seleccionar trabajador:"));
        panelModificar.add(comboTrabajadorModificar);
        panelModificar.add(new JLabel("Nuevo Nombre:"));
        panelModificar.add(txtModificarNombreTrabajador);
        panelModificar.add(new JLabel()); // Componente invisible para ocupar la celda
        panelModificar.add(btnModificarTrabajador);
        panelModificar.add(comboPosicionesNoHabilitadas);
        panelModificar.add(comboPosicionesHabilitadas);
        panelModificar.add(btnAsociarPosicion);
        panelModificar.add(btnRetirarPosicion);
        
        btnAsociarPosicion.addActionListener(e -> {
            Posicion posicionSeleccionada = (Posicion) comboPosicionesNoHabilitadas.getSelectedItem();
            Trabajador trabajadorSeleccionado = (Trabajador) comboTrabajadorModificar.getSelectedItem();
            if (posicionSeleccionada != null && trabajadorSeleccionado != null) {
                boolean exito = gestorTurnos.asociarPosicionATrabajador(trabajadorSeleccionado, posicionSeleccionada);
                if (exito) {
                    cargarPosicionesPorTrabajadorYDepartamento(trabajadorSeleccionado);
                } else {
                    JOptionPane.showMessageDialog(null, "La posición ya estaba asignada al trabajador o hubo un error.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una posición y un trabajador.");
            }
        });
    
        btnRetirarPosicion.addActionListener(e -> {
            Posicion posicionSeleccionada = (Posicion) comboPosicionesHabilitadas.getSelectedItem();
            Trabajador trabajadorSeleccionado = (Trabajador) comboTrabajadorModificar.getSelectedItem();
            if (posicionSeleccionada != null && trabajadorSeleccionado != null) {
                boolean exito = gestorTurnos.retirarPosicionHabilitada(trabajadorSeleccionado, posicionSeleccionada);
                if (exito) {
                    cargarPosicionesPorTrabajadorYDepartamento(trabajadorSeleccionado);
                } else {
                    JOptionPane.showMessageDialog(null, "La posición no estaba asignada al trabajador o hubo un error.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una posición y un trabajador.");
            }
        });

        cards.add(panelModificar, "ModificarTrabajador");
    }
    // Método para cargar los trabajadores en los combos
    public void cargarTodosTrabajadores() {
        try {
            List<Trabajador> trabajadores = gestorTurnos.obtenerTodosTrabajadores();
            comboTrabajadorEliminar.removeAllItems();
            comboTrabajadorModificar.removeAllItems(); // Limpia los items de comboTrabajadorModificar
            for (Trabajador trabajador : trabajadores) {
                comboTrabajadorEliminar.addItem(trabajador);
                comboTrabajadorModificar.addItem(trabajador);
            }
            // revalidar y repintar los combo
            comboTrabajadorEliminar.revalidate();
            comboTrabajadorEliminar.repaint();
            comboTrabajadorModificar.revalidate();
            comboTrabajadorModificar.repaint();
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la pila de llamadas de la excepción
            JOptionPane.showMessageDialog(this, "Error al cargar los trabajadores: " + e.getMessage());
        }
    }

    public void cargarTrabajadoresPorDepartamento(Departamento departamento) {
        try {
            List<Trabajador> trabajadores = gestorTurnos.obtenerTrabajadoresPorDepartamento(departamento.getId());
            comboTrabajadorModificar.removeAllItems(); // Limpia los items de comboTrabajadorModificar
            comboTrabajadorEliminar.removeAllItems();
            for (Trabajador trabajador : trabajadores) {
                comboTrabajadorModificar.addItem(trabajador);
                comboTrabajadorEliminar.addItem(trabajador);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la pila de llamadas de la excepción
            JOptionPane.showMessageDialog(this, "Error al cargar los trabajadores: " + e.getMessage());
        }
    }
    // Método para obtener las posiciones habilitadas por el trabajador
    public Posicion obtenerPosicionHabilitadaSeleccionada() {
        return (Posicion) comboPosicionesHabilitadas.getSelectedItem();
    }   
    
    // Método para obtener la posición restante seleccionada en el JComboBox
    public Posicion obtenerPosicionNoHabilitadaSeleccionada() {
        return (Posicion) comboPosicionesNoHabilitadas.getSelectedItem();
    }

    // Métodos para agregar un trabajador, mostrando un JComboBox con los
    // departamentos disponibles para asignar al trabajador y permitiendo
    // seleccionar uno y obtener su id para asociarlo al trabajador.
    private void agregarTrabajador() {
        String nombre = txtTrabajadorNombre.getText();
        Departamento departamentoSeleccionado = (Departamento) comboDepartamentosParaAgregarTrabajador.getSelectedItem();
        if (!nombre.isEmpty() && departamentoSeleccionado != null) {
            Trabajador trabajador = new Trabajador(0, nombre, departamentoSeleccionado.getId());
            boolean resultado = gestorTurnos.agregarTrabajador(trabajador);
            manejarRespuestaOperacion(resultado, "agregar el trabajador", this::cargarTodosTrabajadores);
            txtTrabajadorNombre.setText("");
        }
    }

    private void eliminarTrabajador() {
        Trabajador trabajador = (Trabajador) comboTrabajadorEliminar.getSelectedItem();
        if (trabajador != null) {
            boolean resultado = gestorTurnos.eliminarTrabajador(trabajador.getId());
            manejarRespuestaOperacion(resultado, "eliminar el trabajador", this::cargarTodosTrabajadores);
        }
    }

    private void modificarTrabajador() {
        Trabajador trabajador = (Trabajador) comboTrabajadorModificar.getSelectedItem();
        String nuevoNombre = txtModificarNombreTrabajador.getText();
        if (trabajador != null && !nuevoNombre.isEmpty()) {
            trabajador.setNombre(nuevoNombre);
            boolean resultado = gestorTurnos.modificarTrabajador(trabajador);
            Departamento departamentoSeleccionado = (Departamento) comboDepartamentosModTrabajador.getSelectedItem();
            manejarRespuestaOperacion(resultado, "modificar el trabajador", () -> cargarTrabajadoresPorDepartamento(departamentoSeleccionado));
        }
        txtModificarNombreTrabajador.setText("");
    }

    // seccion posiciones

    // panel para agregar una posicion
    private void initAgregarPosicionPanel() {
        JPanel panelAgregar = new JPanel(new GridLayout(0, 2));
        comboDepartamentosParaAgregarPosicion = new JComboBox<>();
        List<Departamento> departamentos = gestorTurnos.obtenerTodosDepartamentos();
        for (Departamento departamento : departamentos) {
            comboDepartamentosParaAgregarPosicion.addItem(departamento);
        }

        txtPosicionNombre = new JTextField();
        JButton btnAgregarPosicion = new JButton("Agregar Posicion");
        btnAgregarPosicion.addActionListener(e -> agregarPosicion());
        panelAgregar.add(new JLabel("Nombre:"));
        panelAgregar.add(txtPosicionNombre);
        panelAgregar.add(new JLabel("Departamento:")); // Etiqueta para el JComboBox
        panelAgregar.add(comboDepartamentosParaAgregarPosicion); // Agrega el JComboBox al panel
        panelAgregar.add(new JLabel()); // Componente invisible para ocupar la celda
        panelAgregar.add(btnAgregarPosicion);
        cards.add(panelAgregar, "AgregarPosicion");
    }

    // panel para eliminar una posicion
    private void initEliminarPosicionPanel() {
        JPanel panelEliminar = new JPanel(new GridLayout(0, 2));
        comboPosicionEliminar = new JComboBox<>();
        JButton btnEliminarPosicion = new JButton("Eliminar Posicion");
        btnEliminarPosicion.addActionListener(e -> eliminarPosicion());
        panelEliminar.add(new JLabel("Posicion:"));
        panelEliminar.add(comboPosicionEliminar);
        panelEliminar.add(new JLabel()); // Componente invisible para ocupar la celda
        panelEliminar.add(btnEliminarPosicion);
        cards.add(panelEliminar, "EliminarPosicion");
    }

    // panel para modificar una posicion
    private void initModificarPosicionPanel() {
        JPanel panelModificar = new JPanel(new GridLayout(0, 2));
        comboPosicionModificar = new JComboBox<>();
        txtModificarNombrePosicion = new JTextField();
        JButton btnModificarPosicion = new JButton("Modificar Posicion");
        btnModificarPosicion.addActionListener(e -> modificarPosicion());
        panelModificar.add(new JLabel("Posición:"));
        panelModificar.add(comboPosicionModificar);
        panelModificar.add(new JLabel("Nuevo Nombre:"));
        panelModificar.add(txtModificarNombrePosicion);
        panelModificar.add(new JLabel()); // Componente invisible para ocupar la celda
        panelModificar.add(btnModificarPosicion);
        cards.add(panelModificar, "ModificarPosicion");
    }

    // Método para cargar las posiciones en los combos
    public void cargarTodasPosiciones() {
        try {
            List<Posicion> posiciones = gestorTurnos.obtenerTodasPosiciones();
            comboPosicionEliminar.removeAllItems();
            comboPosicionModificar.removeAllItems(); // Limpia los items de comboPosicionModificar
            for (Posicion posicion : posiciones) {
                comboPosicionEliminar.addItem(posicion);
                comboPosicionModificar.addItem(posicion);
            }
            // revalidar y repintar los combo
            comboPosicionEliminar.revalidate();
            comboPosicionEliminar.repaint();
            comboPosicionModificar.revalidate();
            comboPosicionModificar.repaint();
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la pila de llamadas de la excepción
            JOptionPane.showMessageDialog(this, "Error al cargar las posiciones: " + e.getMessage());
        }
    }

    private void cargarPosicionesPorTrabajadorYDepartamento(Trabajador trabajador) {
        // Obtén todas las posiciones del departamento
        List<Posicion> posicionesDepartamento = gestorTurnos
                .obtenerPosicionesPorDepartamento(trabajador.getDepartamentoId());
        List<Posicion> posicionesTrabajador = gestorTurnos.obtenerPosicionesPorTrabajador(trabajador.getId());
        // Crea una lista de posiciones no asignadas
        List<Posicion> posicionesNoAsignadas = new ArrayList<>(posicionesDepartamento);
        posicionesNoAsignadas.removeAll(posicionesTrabajador);
        // Limpia el JComboBox de posiciones habilitadas
        comboPosicionesHabilitadas.removeAllItems();
        // Agrega las posiciones asignadas al JComboBox de posiciones habilitadas
        for (Posicion posicion : posicionesTrabajador) {
            comboPosicionesHabilitadas.addItem(posicion);
        }
        // Limpia el JComboBox de posiciones no habilitadas
        comboPosicionesNoHabilitadas.removeAllItems();
        // Agrega las posiciones no asignadas al JComboBox de posiciones no habilitadas
        for (Posicion posicion : posicionesNoAsignadas) {
            comboPosicionesNoHabilitadas.addItem(posicion);
        }
    }

    // Métodos para agregar una posición, mostrando un JComboBox con los
    // trabajadores disponibles para asignar a la posición y permitiendo seleccionar
    // uno y obtener su id para asociarlo a la posición.
    private void agregarPosicion() {
        String nombre = txtPosicionNombre.getText();
        Departamento departamentoSeleccionado = (Departamento) comboDepartamentosParaAgregarPosicion.getSelectedItem();
        if (!nombre.isEmpty() && departamentoSeleccionado != null) {
            Posicion posicion = new Posicion(0, nombre, departamentoSeleccionado.getId());
            boolean resultado = gestorTurnos.agregarPosicion(posicion);
            if (resultado) {
                // Obtiene el departamento de la memoria
                Departamento departamentoEnMemoria = gestorTurnos
                        .getDepartamentoPorId(departamentoSeleccionado.getId());
                // Agrega la posición al departamento en la memoria
                if (departamentoEnMemoria != null) {
                    departamentoEnMemoria.agregarPosicion(posicion);
                }
                manejarRespuestaOperacion(resultado, "agregar la posición", () -> {
                    cargarTodasPosiciones();
                });
                txtPosicionNombre.setText("");
            }
        }
    }

    private void eliminarPosicion() {
        Posicion posicion = (Posicion) comboPosicionEliminar.getSelectedItem();
        if (posicion != null) {
            boolean resultado = gestorTurnos.eliminarPosicion(posicion.getId());
            manejarRespuestaOperacion(resultado, "eliminar la posición", this::cargarTodasPosiciones);
        }
    }

    private void modificarPosicion() {
        Posicion posicion = (Posicion) comboPosicionModificar.getSelectedItem();
        String nuevoNombre = txtModificarNombrePosicion.getText();
        if (posicion != null && !nuevoNombre.isEmpty()) {
            posicion.setNombre(nuevoNombre);
            boolean resultado = gestorTurnos.modificarPosicion(posicion);
            manejarRespuestaOperacion(resultado, "modificar la posición", this::cargarTodasPosiciones);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TurnosGUI().setVisible(true));
    }
}