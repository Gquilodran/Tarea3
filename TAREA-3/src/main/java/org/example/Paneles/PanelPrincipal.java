package org.example.Paneles;

import org.example.Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * PanelPrincipal: Panel principal que organiza y muestra los paneles del expendedor y del comprador.
 */
public class PanelPrincipal extends JPanel {
    private Expendedor expendedor;
    private PanelExpendedor panelExpendedor;
    private PanelComprador panelComprador;

    /**
     * Crea el panel principal y organiza los subpaneles.
     * @param numProduc cantidad de productos por tipo
     * @param monedas100 cantidad inicial de monedas de 100
     * @param monedas500 cantidad inicial de monedas de 500
     * @param monedas1000 cantidad inicial de monedas de 1000
     */
    public PanelPrincipal(int numProduc, int monedas100, int monedas500, int monedas1000) {
        setLayout(new BorderLayout());

        // Crear el expendedor con productos de cada tipo
        expendedor = new Expendedor(numProduc);

        // Crear los paneles
        panelExpendedor = new PanelExpendedor(expendedor);
        panelComprador = new PanelComprador(expendedor, panelExpendedor, monedas100, monedas500, monedas1000);

        // Añadir paneles al layout
        add(panelComprador.getPanelMonedas(), BorderLayout.WEST);
        add(panelExpendedor, BorderLayout.CENTER);
        add(panelComprador.getPanelResultado(), BorderLayout.EAST);

        // Configurar el manejo de eventos del mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelComprador.procesarClick(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelExpendedor.paintComponent(g);
        panelComprador.paintComponent(g);
    }

    /** Devuelve el identificador del producto seleccionado. */
    public int getItem() {
        return panelExpendedor.getItem();
    }
}