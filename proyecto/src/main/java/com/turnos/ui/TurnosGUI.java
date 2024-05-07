package com.turnos.ui;

import com.turnos.negocio.GestorTurnos;
import com.turnos.dto.Departamento;
import com.turnos.dao.DepartamentoDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TurnosGUI extends JFrame {
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private JComboBox<Departamento> comboDepartamentoModificar, comboDepartamentoEliminar;
    private JTextField txtDepartamentoId, txtDepartamentoNombre;

    public TurnosGUI() {
        super("Sistema de Gestión de Turnos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initUI();
        

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

    }

    private void cargarDepartamentos() {
        List<Departamento> departamentos = departamentoDAO.obtenerTodosDepartamentos();
        comboDepartamentoModificar.removeAllItems();
        comboDepartamentoEliminar.removeAllItems();
        for (Departamento departamento : departamentos) {
            comboDepartamentoModificar.addItem(departamento);
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

    JTabbedPane tabbedPane = new JTabbedPane();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TurnosGUI().setVisible(true));
    }
}