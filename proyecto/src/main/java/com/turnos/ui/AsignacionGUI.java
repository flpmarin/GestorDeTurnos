package com.turnos.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;

import com.toedter.calendar.JDateChooser;
import com.turnos.dto.Departamento;
import com.turnos.dto.Trabajador;
import com.turnos.negocio.GestorTurnos;

public class AsignacionGUI extends JFrame {
    private GestorTurnos gestorTurnos = new GestorTurnos();
    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);
    private static final String MSG_EXITO = "%s exitosamente.";
    private static final String MSG_ERROR = "Error al %s.";
    private JDateChooser dateChooserInicio;
    private JDateChooser dateChooserFin;

    public void showCard(String cardName) {
        cardLayout.show(cards, cardName);

    }

    public AsignacionGUI(Departamento departamento, JMenuBar menuBar, JFrame turnosGUI) {
        super("Sistema de Gestión de Turnos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initUI(departamento);

        // Crear un nuevo menú "Salir"
        JMenu salirMenu = new JMenu("Salir");
        JMenuItem salirItem = new JMenuItem("salir");
        salirItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar AsignacionGUI
                dispose();
                // Abrir una nueva instancia de TurnosGUI
                new TurnosGUI().setVisible(true);
            }
        });
        salirMenu.add(salirItem);
        // Crear una nueva JMenuBar y añadirle el menú "Salir"
        menuBar = new JMenuBar();
        menuBar.add(salirMenu);
        setJMenuBar(menuBar);
        // Agrega un WindowListener para manejar el cierre de la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Crea una nueva instancia de TurnosGUI y la hace visible
                new TurnosGUI().setVisible(true);
            }
        });
    }

    private void initUI(Departamento departamento) {
        initVistaPanel(departamento);
        initAsignacionPanel(departamento);

        add(cards, BorderLayout.CENTER);
    }

    private void initVistaPanel(Departamento departamento) {
        JPanel vistaPanel = new JPanel();
        vistaPanel.setLayout(new BoxLayout(vistaPanel, BoxLayout.Y_AXIS)); // Alinear los botones verticalmente

        for (Trabajador trabajador : gestorTurnos.obtenerTrabajadoresPorDepartamento(departamento.getId())) {
            JButton trabajadorButton = new JButton(trabajador.getNombre());
            trabajadorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Código para manejar el clic en el botón del trabajador
                }
            });
            vistaPanel.add(trabajadorButton);
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(vistaPanel);
        cards.add(scrollPane, "Vista");
    }

    private void initAsignacionPanel(Departamento departamento) {
        JPanel asignacionPanel = new JPanel();
        asignacionPanel.add(new JLabel("Asignacion Panel"));
        dateChooserInicio = new JDateChooser();
        dateChooserFin = new JDateChooser();
        asignacionPanel.add(new JLabel("Fecha de inicio:"));
        asignacionPanel.add(dateChooserInicio);
        asignacionPanel.add(new JLabel("Fecha de fin:"));
        asignacionPanel.add(dateChooserFin);
        cards.add(asignacionPanel, "Asignacion");
    }
}
