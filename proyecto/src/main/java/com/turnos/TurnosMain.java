package com.turnos;
import com.turnos.ui.TurnosGUI;

import javax.swing.SwingUtilities;

public class TurnosMain {
    public static void main(String[] args) {
        // Instanciar la GUI principal de la biblioteca, te jakee
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TurnosGUI().setVisible(true);
        });

    }

}
