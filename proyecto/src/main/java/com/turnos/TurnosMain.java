package com.turnos;

import com.turnos.negocio.GestorTurnos;
import com.turnos.ui.TurnosGUI;
import java.net.URL;
import javax.swing.SwingUtilities;

public class TurnosMain {

    public static void main(String[] args) {

        // Instanciar la GUI principal en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            new TurnosGUI().setVisible(true);
        });

        // Cargar departamentos desde un archivo JSON - se usa class.getResource para obtener la URL del archivo en el classpath del proyecto 
        URL url = TurnosMain.class.getResource("/departamentos.json");
        GestorTurnos gestorTurnos = new GestorTurnos();
        gestorTurnos.cargarDepartamentosDesdeJson(url); 

    }

}