package com.turnos.ui;

import com.turnos.dto.Departamento;
import com.turnos.dto.Trabajador;
import com.turnos.negocio.GestorTurnos;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TurnosGUI extends JFrame {
    private GestorTurnos gestorTurnos = new GestorTurnos(); // Instancia de la clase GestorTurnos en lugar de llamar a
                                                            // los métodos del paquete dao, permite separar la lógica de
                                                            // negocio de la lógica de acceso a datos.
    private JComboBox<Departamento> comboDepartamentoEliminar, comboDepartamentoModificar, comboDepartamentos;
    private JComboBox<Trabajador> comboTrabajadorEliminar, comboTrabajadorModificar;
    private JTextField txtDepartamentoNombre, txtModificarNombreDepartamento, txtTrabajadorNombre,txtModificarNombreTrabajador;
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
        cargarTrabajadores();
    }

    private void initUI() {
        initAgregarDepartamentoPanel();
        initEliminarDepartamentoPanel();
        initModificarDepartamentoPanel();

        initAgregarTrabajadorPanel();
        initEliminarTrabajadorPanel();
        initModificarTrabajadorPanel();

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

    // Método para cargar los departamentos en los combos
    public void cargarDepartamentos() {
        try {
            List<Departamento> departamentos = gestorTurnos.obtenerTodosDepartamentos();
            comboDepartamentoEliminar.removeAllItems();
            comboDepartamentoModificar.removeAllItems(); // Limpia los items de comboDepartamentoModificar
            comboDepartamentos.removeAllItems();
            for (Departamento departamento : departamentos) {
                comboDepartamentoEliminar.addItem(departamento);
                comboDepartamentoModificar.addItem(departamento);
                comboDepartamentos.addItem(departamento);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la pila de llamadas de la excepción
            JOptionPane.showMessageDialog(this, "Error al cargar los departamentos: " + e.getMessage());
        }
    }

    //  Este método toma el resultado de una operación (true/false), 
    // el nombre de la operación (agregar, eliminar, modificar, etc.)
    //  y una función para actualizar la interfaz de usuario. Muestra un mensaje de 
    //  éxito o error dependiendo del resultado de la operación. 
    private void manejarRespuestaOperacion(boolean resultado, String operacion, Runnable cargarDatos) {
        String mensajeExito = String.format(MSG_EXITO, operacion);
        String mensajeError = String.format(MSG_ERROR, operacion);
        if (resultado) {
            JOptionPane.showMessageDialog(this, mensajeExito); 
            cargarDatos.run();// Actualiza la interfaz de usuario.

        } else {
            JOptionPane.showMessageDialog(this, mensajeError); 
        }
    }

    private void agregarDepartamento() {
        String nombre = txtDepartamentoNombre.getText();
        if (!nombre.isEmpty()) {
            Departamento departamento = new Departamento(0, nombre);
            boolean resultado = gestorTurnos.agregarDepartamento(departamento);
            manejarRespuestaOperacion(resultado, "agregar el departamento", this::cargarDepartamentos);
            txtDepartamentoNombre.setText("");
        }
    }

    private void eliminarDepartamento() {
        Departamento departamento = (Departamento) comboDepartamentoEliminar.getSelectedItem();
        if (departamento != null) {
            boolean resultado = gestorTurnos.eliminarDepartamento(departamento.getId());
            manejarRespuestaOperacion(resultado, "eliminar el departamento", this::cargarDepartamentos);
        }
    }

    private void modificarDepartamento() {
        Departamento departamento = (Departamento) comboDepartamentoModificar.getSelectedItem();
        String nuevoNombre = txtModificarNombreDepartamento.getText();
        if (departamento != null && !nuevoNombre.isEmpty()) {
            departamento.setNombre(nuevoNombre);
            boolean resultado = gestorTurnos.modificarDepartamento(departamento);
            manejarRespuestaOperacion(resultado, "modificar el departamento", this::cargarDepartamentos);
        }
    }

    // seccion trabajadores

    // panel para agregar un trabajador
    private void initAgregarTrabajadorPanel() {
        JPanel panelAgregar = new JPanel(new GridLayout(0, 2));
        comboDepartamentos = new JComboBox<>();// cambio
        txtTrabajadorNombre = new JTextField();
        JButton btnAgregarTrabajador = new JButton("Agregar Trabajador");
        btnAgregarTrabajador.addActionListener(e -> agregarTrabajador());
        panelAgregar.add(new JLabel("Nombre:"));
        panelAgregar.add(txtTrabajadorNombre);
        panelAgregar.add(new JLabel("Departamento:")); // Etiqueta para el JComboBox
        panelAgregar.add(comboDepartamentos); // Agrega el JComboBox al panel
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
        comboTrabajadorModificar = new JComboBox<>();
        txtModificarNombreTrabajador = new JTextField();
        JButton btnModificarTrabajador = new JButton("Modificar Trabajador");
        btnModificarTrabajador.addActionListener(e -> modificarTrabajador());
        panelModificar.add(new JLabel("Trabajador:"));
        panelModificar.add(comboTrabajadorModificar);
        panelModificar.add(new JLabel("Nuevo Nombre:"));
        panelModificar.add(txtModificarNombreTrabajador);
        panelModificar.add(new JLabel()); // Componente invisible para ocupar la celda
        panelModificar.add(btnModificarTrabajador);
        cards.add(panelModificar, "ModificarTrabajador");
    }

    // Método para cargar los trabajadores en los combos
    public void cargarTrabajadores() {
        try {
            List<Trabajador> trabajadores = gestorTurnos.obtenerTodosTrabajadores();
            comboTrabajadorEliminar.removeAllItems();
            comboTrabajadorModificar.removeAllItems(); // Limpia los items de comboTrabajadorModificar
            for (Trabajador trabajador : trabajadores) {
                comboTrabajadorEliminar.addItem(trabajador);
                comboTrabajadorModificar.addItem(trabajador);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la pila de llamadas de la excepción
            JOptionPane.showMessageDialog(this, "Error al cargar los trabajadores: " + e.getMessage());
        }
    }


    // Métodos para agregar un trabajador, mostrando un JComboBox con los departamentos disponibles para asignar al trabajador y permitiendo seleccionar uno y obtener su id para asociarlo al trabajador.
    private void agregarTrabajador() {
        String nombre = txtTrabajadorNombre.getText();
        Departamento departamentoSeleccionado = (Departamento) comboDepartamentos.getSelectedItem();
        if (!nombre.isEmpty() && departamentoSeleccionado != null) {
            Trabajador trabajador = new Trabajador(0, nombre, departamentoSeleccionado.getId());
            boolean resultado = gestorTurnos.agregarTrabajador(trabajador);
            manejarRespuestaOperacion(resultado, "agregar el trabajador", this::cargarTrabajadores);
            txtTrabajadorNombre.setText("");
        }
    }

    private void eliminarTrabajador() {
        Trabajador trabajador = (Trabajador) comboTrabajadorEliminar.getSelectedItem();
        if (trabajador != null) {
            boolean resultado = gestorTurnos.eliminarTrabajador(trabajador.getId());
            manejarRespuestaOperacion(resultado, "eliminar el trabajador", this::cargarTrabajadores);
        }
    }

    private void modificarTrabajador() {
        Trabajador trabajador = (Trabajador) comboTrabajadorModificar.getSelectedItem();
        String nuevoNombre = txtModificarNombreTrabajador.getText();
        if (trabajador != null && !nuevoNombre.isEmpty()) {
            trabajador.setNombre(nuevoNombre);
            boolean resultado = gestorTurnos.modificarTrabajador(trabajador);
            manejarRespuestaOperacion(resultado, "modificar el trabajador", this::cargarTrabajadores);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TurnosGUI().setVisible(true));
    }
}