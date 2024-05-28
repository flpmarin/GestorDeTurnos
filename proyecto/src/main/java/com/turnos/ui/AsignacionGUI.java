package com.turnos.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

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
        // Inicializar el panel de vista
        JPanel vistaPanel = new JPanel(new BorderLayout());
        vistaPanel.setPreferredSize(new Dimension(400, 300));
        initVistaPanel(departamento, vistaPanel);

        // Inicializar el panel de asignación
        initAsignacionPanel(departamento);

        // Inicializar el panel de gráficos
        initGraficosPanel(departamento);

        // Agregar los paneles al CardLayout
        cards.add(vistaPanel, "Vista");
        cards.revalidate();
        cards.repaint();

        // Agregar el panel de cards al frame principal
        add(cards, BorderLayout.CENTER);
    }

    private void initVistaPanel(Departamento departamento, JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Añade esta línea
        JDateChooser startDateChooser = new JDateChooser();
        JDateChooser endDateChooser = new JDateChooser();

        // Crea un modelo de tabla vacío con los nombres de las columnas
        DefaultTableModel tableModel = new DefaultTableModel();
        // Crea un JTable con el modelo de tabla
        JTable table = new JTable(tableModel);
        int totalRows = gestorTurnos.calcularCantidadPosiciones() * gestorTurnos.obtenerNumeroDeTurnosUnicos();

        JButton bGenerarColumna = new JButton("Generar columnas");
        bGenerarColumna.addActionListener(e -> {
            java.util.Date startDateUtil = startDateChooser.getDate();
            java.util.Date endDateUtil = endDateChooser.getDate();

            if (startDateUtil != null && endDateUtil != null) {
                LocalDate startDate = startDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endDate = endDateUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // Limpia el modelo de la tabla
                tableModel.setRowCount(0);
                tableModel.setColumnCount(0);

                generarColumnasFecha(startDate, endDate, tableModel, totalRows);

                // tableModel.addRow(new Object[] { "Trabajador 1", "01/01/2022", "Turno 1" });
                // tableModel.addRow(new Object[] { "Trabajador 2", "02/01/2022", "Turno 2" });
                // tableModel.addRow(new Object[] { "Trabajador 3", "03/01/2022", "Turno 3" });

                // genera un mensaje de error si no se selecciona una fecha
                table.setModel(tableModel);
            } else {
                JOptionPane.showMessageDialog(null, "No se seleccionó ninguna fecha");
            }
        });

        // Añade la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Crea un JComboBox para los trabajadores
        JComboBox<Trabajador> trabajadorComboBox = new JComboBox<>();

        // Añade cada trabajador del departamento al JComboBox
        for (Trabajador trabajador : gestorTurnos.obtenerTrabajadoresPorDepartamento(departamento.getId())) {
            trabajadorComboBox.addItem(trabajador);
        }

        // Añade un ActionListener al JComboBox
        trabajadorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtiene el trabajador seleccionado
                Trabajador selectedTrabajador = (Trabajador) trabajadorComboBox.getSelectedItem();

                // Código para manejar la selección del trabajador
                // Por ejemplo, podrías actualizar la tabla aquí
            }
        });

        // Añade el JComboBox al panel principal
        add(trabajadorComboBox, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
        panel.add(startDateChooser);
        panel.add(endDateChooser);
        panel.add(bGenerarColumna); // Añade el botón de generarColum
        panel.add(scrollPane);

        // Añade un botón para guardar los cambios
        // JButton guardarButton = new JButton("Guardar");
        // guardarButton.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // // Código para guardar los cambios
        // // Por ejemplo, podrías guardar los datos de la tabla en la base de datos
        // // y mostrar un mensaje de éxito o error
        // if (gestorTurnos.guardarCambios()) {
        // JOptionPane.showMessageDialog(AsignacionGUI.this, String.format(MSG_EXITO,
        // "guardar los cambios"),
        // "Éxito", JOptionPane.INFORMATION_MESSAGEgenerarColumnasFecha);
        // } else {
        // JOptionPane.showMessageDialog(AsignacionGUI.this, String.format(MSG_ERROR,
        // "guardar los cambios"),
        // "Error", JOptionPane.ERROR_MESSAGE);
        // }
        // }
        // });
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

    // Método para generar las columnas de la tabla de acuerdo a las fechas
    // seleccionadas
    public void generarColumnasFecha(LocalDate startDate, LocalDate endDate, DefaultTableModel tableModel, int totalRows) {

        // Validar que la diferencia entre las fechas no sea mayor a 33 días
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        if (daysBetween > 33) {
            JOptionPane.showMessageDialog(null, "La diferencia entre las fechas no puede superar los 33 días");
            return;
        }

        // Generar una lista de fechas entre la fecha de inicio y la fecha de fin
        List<LocalDate> dates = new ArrayList<>();
        while (!startDate.isAfter(endDate)) {
            dates.add(startDate);
            startDate = startDate.plusDays(1);
        }

        // Agregar las fechas como columnas a la tabla
        for (LocalDate date : dates) {
            tableModel.addColumn(date.toString());
        }

        // Añade las filas
        for (int i = 0; i < totalRows; i++) {
            tableModel.addRow(new Object[tableModel.getColumnCount()]);
        }
    }

}
