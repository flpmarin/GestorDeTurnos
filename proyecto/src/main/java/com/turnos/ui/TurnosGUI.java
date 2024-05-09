package com.turnos.ui;

import com.turnos.dto.Departamento;
import com.turnos.dao.DepartamentoDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TurnosGUI extends JFrame {
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private JComboBox<Departamento> comboDepartamentoEliminar, comboDepartamentoModificar;
    private JTextField txtDepartamentoNombre, txtModificarNombreDepartamento;
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
    }

    private void initUI() {
        initAgregarPanel();
        initEliminarPanel();
        initModificarPanel();
        initMenu();
        add(cards, BorderLayout.CENTER);
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        JMenu submenu = new JMenu("Departamentos");
        JMenuItem miAgregar = new JMenuItem("Agregar");
        JMenuItem miEliminar = new JMenuItem("Eliminar");
        JMenuItem miModificar = new JMenuItem("Modificar");;

        miAgregar.addActionListener(e -> cardLayout.show(cards, "Agregar"));
        miEliminar.addActionListener(e -> cardLayout.show(cards, "Eliminar"));
        miModificar.addActionListener(e -> cardLayout.show(cards, "Modificar"));

        submenu.add(miAgregar);
        submenu.add(miEliminar);
        submenu.add(miModificar);
        menu.add(submenu);
        menuBar.add(menu);

        setJMenuBar(menuBar);
    }

    // Pestaña para agregar un departamento
    private void initAgregarPanel() {
        JPanel panelAgregar = new JPanel(new GridLayout(0, 2));
        txtDepartamentoNombre = new JTextField();
        JButton btnAgregarDepartamento = new JButton("Agregar Departamento");
        btnAgregarDepartamento.addActionListener(e -> crearDepartamento());
        panelAgregar.add(new JLabel("Nombre:"));
        panelAgregar.add(txtDepartamentoNombre);
        panelAgregar.add(new JLabel(""));
        panelAgregar.add(btnAgregarDepartamento);
        cards.add(panelAgregar, "Agregar");
    }

    // Pestaña para eliminar un departamento
    private void initEliminarPanel() {
        JPanel panelEliminar = new JPanel(new GridLayout(0, 2));
        comboDepartamentoEliminar = new JComboBox<>();
        JButton btnEliminarDepartamento = new JButton("Eliminar Departamento");
        btnEliminarDepartamento.addActionListener(e -> eliminarDepartamento());
        panelEliminar.add(new JLabel("Departamento:"));
        panelEliminar.add(comboDepartamentoEliminar);
        panelEliminar.add(new JLabel(""));
        panelEliminar.add(btnEliminarDepartamento);
        cards.add(panelEliminar, "Eliminar");
    }

    // Pestaña para modificar un departamento
    private void initModificarPanel() {
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
        cards.add(panelModificar, "Modificar");
    }

    // Método para cargar los departamentos en los combos
    public void cargarDepartamentos() {
        try {
            List<Departamento> departamentos = departamentoDAO.obtenerTodosDepartamentos();
            comboDepartamentoEliminar.removeAllItems();
            comboDepartamentoModificar.removeAllItems(); // Limpia los items de comboDepartamentoModificar
            for (Departamento departamento : departamentos) {
                comboDepartamentoEliminar.addItem(departamento);
                comboDepartamentoModificar.addItem(departamento); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los departamentos: " + e.getMessage());
        }
    }

    // Método para manejar la respuesta de las operaciones de departamento
    private void manejarRespuestaOperacion(boolean resultado, String operacion) {
        String mensajeExito = String.format(MSG_EXITO, operacion);
        String mensajeError = String.format(MSG_ERROR, operacion);
        if (resultado) {
            JOptionPane.showMessageDialog(this, mensajeExito);
            cargarDepartamentos();
        } else {
            JOptionPane.showMessageDialog(this, mensajeError);
        }
    }

    private void crearDepartamento() {
        String nombre = txtDepartamentoNombre.getText();
        if (!nombre.isEmpty()) {
            Departamento departamento = new Departamento(0, nombre);
            boolean resultado = departamentoDAO.crearDepartamento(departamento);
            manejarRespuestaOperacion(resultado, "agregar el departamento");
            txtDepartamentoNombre.setText("");
        }
    }

    private void eliminarDepartamento() {
        Departamento departamento = (Departamento) comboDepartamentoEliminar.getSelectedItem();
        if (departamento != null) {
            boolean resultado = departamentoDAO.eliminarDepartamento(departamento.getId());
            manejarRespuestaOperacion(resultado, "eliminar el departamento");
        }
    }

    private void modificarDepartamento() {
        Departamento departamento = (Departamento) comboDepartamentoModificar.getSelectedItem();
        String nuevoNombre = txtModificarNombreDepartamento.getText();
        if (departamento != null && !nuevoNombre.isEmpty()) {
            departamento.setNombre(nuevoNombre);
            boolean resultado = departamentoDAO.modificarDepartamento(departamento);
            manejarRespuestaOperacion(resultado, "modificar el departamento");
        }
    }

    JTabbedPane tabbedPane = new JTabbedPane();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TurnosGUI().setVisible(true));
    }
}