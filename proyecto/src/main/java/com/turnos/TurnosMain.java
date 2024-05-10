package com.turnos;

import com.turnos.ui.TurnosGUI; 

import javax.swing.SwingUtilities; //clase SwingUtilities de javax.swing, proporciona métodos estáticos y de instancia para interactuar con el sistema de eventos de Swing.

public class TurnosMain { 

    public static void main(String[] args) { 

        // Instanciar la GUI principal en el hilo de eventos de Swing
        javax.swing.SwingUtilities.invokeLater(() -> { // Utiliza el método invokeLater de SwingUtilities para asegurar que la GUI se crea en el hilo de eventos de Swing.
            new TurnosGUI().setVisible(true); // Crea una nueva instancia de TurnosGUI y la hace visible.
        });

    }

}