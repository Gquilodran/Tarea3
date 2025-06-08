package org.example.Paneles;
import javax.swing.JFrame;
import java.awt.*;

/**
 * Clase Ventana que extiende JFrame para crear la ventana principal del expendedor.
 * Configura el layout, título, tamaño y comportamiento de cierre.
 * Agrega el panel principal al JFrame.
 */

public class Ventana extends JFrame{
    /**
     * Constructor de la ventana principal del expendedor.
     * Configura el layout, título, tamaño y comportamiento de cierre.
     * Agrega el panel principal al JFrame.
     */
    public Ventana() {
        super();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout((new BorderLayout()));
        this.setTitle("EXPENDEDOR");
        this.setSize(900, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        PanelPrincipal panelPrincipal = new PanelPrincipal();
        this.add(panelPrincipal);

        this.setVisible(true);

    }
}
