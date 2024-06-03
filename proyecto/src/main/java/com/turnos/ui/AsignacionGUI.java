package com.turnos.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import java.awt.image.BufferedImage;
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
        table.setRowHeight(40);
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

                // genera un mensaje de error si no se selecciona una fecha
                table.setModel(tableModel);
            } else {
                JOptionPane.showMessageDialog(null, "No se seleccionó ninguna fecha");
            }
        });
        // Crea un JComboBox para los trabajadores
        JComboBox<Trabajador> trabajadorComboBox = new JComboBox<>();

        // Supongamos que 'asignarButton' es tu botón "Asignar"
        JButton bAsignar = new JButton("Asignar");
        bAsignar.addActionListener(e -> {
            // Obtén el nombre del trabajador seleccionado en el JComboBox
            Trabajador trabajadorSeleccionado = (Trabajador) trabajadorComboBox.getSelectedItem();
            String trabajadorNombre = trabajadorSeleccionado.getNombre();
            int departamentoId = trabajadorSeleccionado.getDepartamentoId();

            // Obtén los índices de la fila y la columna seleccionados
            int selectedRowIndex = table.getSelectedRow();
            int selectedColumnIndex = table.getSelectedColumn();

            // Comprueba si se seleccionó una celda
            if (selectedRowIndex != -1 && selectedColumnIndex != -1) {
                // Calcula el índice del turno
                int cantidadPosiciones = gestorTurnos.calcularCantidadPosiciones();

                // Calcula el id del grupo de turnos, se suma 1 porque los índices de las filas
                // y columnas comienzan en 0
                int turnoIdGrupo = selectedRowIndex / cantidadPosiciones + 1;

                // Calcula el id de la posición, se suma 1 porque los índices de las filas
                // comienzan en 0
                int posicionId = selectedRowIndex % cantidadPosiciones + 1;

                // Obtén la fecha del encabezado de la columna seleccionada
                String fechaEncabezado = table.getColumnName(selectedColumnIndex);

                // Si se seleccionó una celda, establece su valor al nombre del trabajador
                tableModel.setValueAt(
                        trabajadorNombre + " Dep " + departamentoId + " turnoIdGrupo: " + turnoIdGrupo + " posicionId: "
                                + posicionId + " fecha: " + fechaEncabezado,
                        selectedRowIndex,
                        selectedColumnIndex);
            } else {
                // Si no se seleccionó ninguna celda, muestra un mensaje de error
                JOptionPane.showMessageDialog(null, "No se seleccionó ninguna celda");
            }
        });

        // Añade la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

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
        add(trabajadorComboBox, BorderLayout.EAST);
        add(scrollPane, BorderLayout.CENTER);
        panel.add(startDateChooser);
        panel.add(endDateChooser);
        panel.add(bGenerarColumna); // Añade el botón para generar columnasy filas
        panel.add(bAsignar); // Añade el botón de asignar

        // Calcula la cantidad de posiciones
        int totalPositions = gestorTurnos.calcularCantidadPosiciones();

        // Genera un color aleatorio para cada posición
        Color[] colors = new Color[totalPositions];
        Random random = new Random();
        for (int i = 0; i < totalPositions; i++) {
            float r = random.nextFloat() / 2f + 0.5f; // 0.5 y 1.0 color claro
            float g = random.nextFloat() / 2f + 0.5f;
            float b = random.nextFloat() / 2f + 0.5f;
            colors[i] = new Color(r, g, b);
        }

        // Crea un JTextArea para personalizar el renderizador de la tabla, permite que el texto se ajuste al tamaño del JTextArea y se ajuste a las palabras completas.
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true); // Ajusta el texto al tamaño del JTextArea
        textArea.setWrapStyleWord(true); 

        // Personaliza el renderizador de la tabla
        table.setDefaultRenderer(Object.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                textArea.setText((String) value);

                // Calcula la posición de la fila
                int position = row % colors.length;

                // Cambia el color de la fila en función de la posición
                textArea.setBackground(colors[position]);

                // Si la celda está seleccionada, cambia su color de fondo
                if (isSelected) {
                    textArea.setBackground(table.getSelectionBackground());
                }

                return textArea;
            }
        });

        // Crea el panel de la leyenda
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));

        // Añade un título a la leyenda
        legendPanel.add(new JLabel("Leyenda:"));

        // Añade una entrada de la leyenda para cada posición
        for (int i = 0; i < totalPositions; i++) {
            // Crea el panel de la entrada de la leyenda
            JPanel entryPanel = new JPanel();
            entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));

            // Crea el icono de color
            BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(colors[i]);
            graphics.fillOval(0, 0, 16, 16);
            ImageIcon icon = new ImageIcon(image);

            // Obtiene el nombre de la posición
            String positionName = gestorTurnos.obtenerNombrePosicionPorId(i + 1);

            // Crea el label con el icono de color y el nombre de la posición
            JLabel label = new JLabel(" " + positionName, icon, JLabel.LEFT);

            // Añade el label al panel de la entrada de la leyenda
            entryPanel.add(label);

            // Añade el panel de la entrada de la leyenda al panel de la leyenda
            legendPanel.add(entryPanel);
        }

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
        add(legendPanel, BorderLayout.SOUTH);
        panel.add(scrollPane);
        panel.add(trabajadorComboBox);
        panel.add(legendPanel);
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

        // Crea un conjunto de datos de pastel con los nombres de los departamentos y el número de trabajadores
        @SuppressWarnings("rawtypes") // Se suprime la advertencia de tipo sin comprobación, ya que el tipo de datos es correcto en este caso
        DefaultPieDataset dataset = new DefaultPieDataset(); // Se crea un conjunto de datos de pastel

        // con una lista de todos los departamentos
        List<Departamento> departamentos = gestorTurnos.obtenerTodosDepartamentos();

        // Y una instancia de GestorTurnos
        GestorTurnos gestorTurnos = new GestorTurnos();
        
        // Itera sobre la lista de departamentos y añade el nombre y el número de trabajadores de cada departamento al conjunto de datos
        for (Departamento dept : departamentos) {
            int numTrabajadores = gestorTurnos.NumeroDeTrabajadoresPorDepartamento(dept.getId());
            dataset.setValue(dept.getNombre(), numTrabajadores);
        }
        // Crea un gráfico de pastel con los datos del conjunto de datos y lo añade al panel
        JFreeChart chart = ChartFactory.createPieChart(
            "Porcentaje de trabajadores por departamento",dataset,
            true, true, false
        ); // el primer true es para mostrar la leyenda, el segundo true es para mostrar las etiquetas, el false es para no mostrar la URL

        PiePlot plot = (PiePlot) chart.getPlot();
        // Personaliza las etiquetas del gráfico de pastel para mostrar el nombre, el valor y el porcentaje
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {1} ({2})"));

        ChartPanel chartPanel = new ChartPanel(chart);
        asignacionPanel.add(chartPanel); // Añade el panel del gráfico al panel de asignación
        cards.add(asignacionPanel, "Graficos");
    }

    // Método para generar las columnas de la tabla de acuerdo a las fechas
    // seleccionadas
    public void generarColumnasFecha(LocalDate startDate, LocalDate endDate, DefaultTableModel tableModel,
            int totalRows) {

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
