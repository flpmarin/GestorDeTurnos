package com.turnos;

import com.turnos.ui.TurnosGUI; 
import javax.swing.SwingUtilities;

public class TurnosMain { 

    public static void main(String[] args) { 

        // Instanciar la GUI principal en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> { 
            new TurnosGUI().setVisible(true); 
        });

    }

}