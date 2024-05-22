package com.turnos.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        JMenu salirMenu = new JMenu("Menu");

        // Crear un nuevo submenú "Graficos"
        JMenuItem graficosItem = new JMenuItem("Graficos");
        graficosItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambia al panel de gráficos
                cardLayout.show(cards, "Graficos");
            }
        });
        salirMenu.add(graficosItem);

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
        initGraficosPanel(departamento);

        add(cards, BorderLayout.CENTER);
    }

    private void initVistaPanel(Departamento departamento) {
        JPanel vistaPanel = new JPanel();
        vistaPanel.setLayout(new BoxLayout(vistaPanel, BoxLayout.X_AXIS)); // Alinear los componentes horizontalmente

        // Crea un panel para los botones de los trabajadores
        JPanel trabajadoresPanel = new JPanel();
        trabajadoresPanel.setLayout(new BoxLayout(trabajadoresPanel, BoxLayout.Y_AXIS)); // Alinear los botones
                                                                                         // verticalmente

        for (Trabajador trabajador : gestorTurnos.obtenerTrabajadoresPorDepartamento(departamento.getId())) {
            JButton trabajadorButton = new JButton(trabajador.getNombre());
            trabajadorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Código para manejar el clic en el botón del trabajador
                }
            });
            trabajadoresPanel.add(trabajadorButton);
        }

        // Obtén la cantidad de turnos únicos (turnoIdGrupo) de la base de datos
        int numTurnos = gestorTurnos.obtenerNumeroDeTurnosUnicos();

        // Crea un array con los nombres de los días de la semana
        String[] daysOfWeek = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };

        // Crea un panel para cada día de la semana
        for (int i = 0; i < 7; i++) {
            JPanel dayPanel = new JPanel(new GridLayout(numTurnos, 1)); // Crea un panel con el número de filas igual al
                                                                        // número de turnos
            dayPanel.setBorder(BorderFactory.createTitledBorder(daysOfWeek[i])); // Agrega un borde con el título del
                                                                                 // día de la semana

            // Crea un JLabel para cada turno
            for (int j = 0; j < numTurnos; j++) {
                JLabel turnoLabel = new JLabel("Turno " + (j + 1));
                turnoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                dayPanel.add(turnoLabel);
            }

            vistaPanel.add(dayPanel);
        }

        // Agrega el panel de trabajadores al panel principal
        vistaPanel.add(trabajadoresPanel);

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


    private void initGraficosPanel(Departamento departamento) {
        JPanel asignacionPanel = new JPanel();
        asignacionPanel.add(new JLabel("Gráficos Panel"));

        @SuppressWarnings("rawtypes")
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Supongamos que tienes una lista de todos los departamentos
        List<Departamento> departamentos = gestorTurnos.obtenerTodosDepartamentos();

        // Y una instancia de GestorTurnos
        GestorTurnos gestorTurnos = new GestorTurnos();

        for (Departamento dept : departamentos) {
            int numTrabajadores = gestorTurnos.NumeroDeTrabajadoresPorDepartamento(dept.getId());
            dataset.setValue(dept.getNombre(), numTrabajadores);
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Porcentaje de trabajadores por departamento",
                dataset,
                true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {1} ({2})"));

        ChartPanel chartPanel = new ChartPanel(chart);
        asignacionPanel.add(chartPanel);
        cards.add(asignacionPanel, "Graficos");
    }

}
