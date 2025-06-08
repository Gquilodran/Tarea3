package org.example;

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
    public Ventana(int numeroProducto,int presupuesto) {
        super();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout((new BorderLayout()));
        this.setTitle("EXPENDEDOR");
        this.setSize(900, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        PanelPrincipal panelPrincipal = new PanelPrincipal(numeroProducto,presupuesto);
        this.add(panelPrincipal);

        this.setVisible(true);

    }
    public int getItem() {
        // Buscar entre los componentes del JFrame
        for (Component c : this.getContentPane().getComponents()) {
            if (c instanceof PanelPrincipal) {
                return ((PanelPrincipal) c).getItem();
            }
        }
        return -1; // o cualquier valor por defecto si no se encuentra
    }
    public int getPago(){
        for (Component c : this.getContentPane().getComponents()) {
            if (c instanceof PanelPrincipal) {
                return ((PanelPrincipal) c).getMon();
            }
        }
        return -1;
        }


}
