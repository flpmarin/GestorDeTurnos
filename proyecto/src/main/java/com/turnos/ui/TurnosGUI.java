package com.turnos.ui;

import com.turnos.dto.Departamento;
import com.turnos.dao.DepartamentoDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TurnosGUI extends JFrame {
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private JComboBox<Departamento> comboDepartamentoEliminar;
    private JTextField txtDepartamentoId, txtDepartamentoNombre;

    public TurnosGUI() {
        super("Sistema de Gestión de Turnos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initUI();
        cargarDepartamentos();

    }

    private void initUI() {
        // Sección de departamentos
        add(new JLabel("Departamento ID:"));
        txtDepartamentoId = new JTextField();
        add(txtDepartamentoId);

        add(new JLabel("Nombre del Departamento:"));
        txtDepartamentoNombre = new JTextField();
        add(txtDepartamentoNombre);

        JPanel panelAgregar = new JPanel(new GridLayout(0, 2));

        txtDepartamentoNombre = new JTextField();
        JButton btnAgregarDepartamento = new JButton("Agregar Departamento");
        btnAgregarDepartamento.addActionListener(e -> agregarDepartamento());
        panelAgregar.add(new JLabel("Nombre:"));
        panelAgregar.add(txtDepartamentoNombre);
        panelAgregar.add(new JLabel(""));
        panelAgregar.add(btnAgregarDepartamento);

        add(panelAgregar, BorderLayout.CENTER);

        // Sección de eliminar departamentos
        JPanel panelEliminar = new JPanel(new GridLayout(0, 2));
        comboDepartamentoEliminar = new JComboBox<>();
        JButton btnEliminarDepartamento = new JButton("Eliminar Departamento");
        btnEliminarDepartamento.addActionListener(e -> eliminarDepartamento());
        panelEliminar.add(new JLabel("Departamento:"));
        panelEliminar.add(comboDepartamentoEliminar);
        panelEliminar.add(new JLabel(""));
        panelEliminar.add(btnEliminarDepartamento);

        // Añadir los paneles a tabbedPane
        tabbedPane.add("Agregar", panelAgregar);
        tabbedPane.add("Eliminar", panelEliminar);

        // Añadir tabbedPane a la GUI
        add(tabbedPane, BorderLayout.CENTER);;

    }

    public void cargarDepartamentos() {
        List<Departamento> departamentos = departamentoDAO.obtenerTodosDepartamentos();
        comboDepartamentoEliminar.removeAllItems();
        for (Departamento departamento : departamentos) {
            comboDepartamentoEliminar.addItem(departamento);
        }
    }

    private void agregarDepartamento() {
        String nombre = txtDepartamentoNombre.getText();
        if (!nombre.isEmpty()) {
            Departamento departamento = new Departamento(0, nombre);
            if (departamentoDAO.agregarDepartamento(departamento)) {
                JOptionPane.showMessageDialog(this, "Departamento agregado exitosamente.");
                cargarDepartamentos(); // Recargar lista de autores
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el departamento.");
            }
            txtDepartamentoNombre.setText("");
        }
    }

    private void eliminarDepartamento() {
        Departamento departamento = (Departamento) comboDepartamentoEliminar.getSelectedItem();
        if (departamento != null) {
            if (departamentoDAO.eliminarDepartamento(departamento.getId())) {
                JOptionPane.showMessageDialog(this, "Departamento eliminado exitosamente.");
                cargarDepartamentos(); // Recargar lista de departamentos
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el departamento.");
            }
        }
    }

    JTabbedPane tabbedPane = new JTabbedPane();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TurnosGUI().setVisible(true));
    }
}