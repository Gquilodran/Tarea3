package org.example;

import javax.swing.SwingUtilities; // Solo necesitas importar SwingUtilities

/**
 * Clase principal que ejecuta un menú interactivo para simular una máquina expendedora.
 * <p>
 * Los usuarios pueden insertar monedas, seleccionar productos y recibir su compra el vuelto correspondiente.
 */

public class MainInteractivo {

    /**
     * Método principal que ejecuta el ciclo de compra.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ventana(10, 1100);
            }
        });
    }
}