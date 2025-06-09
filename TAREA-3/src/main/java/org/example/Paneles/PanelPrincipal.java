package org.example.Paneles;

import org.example.Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPrincipal extends JPanel {
    private Expendedor expendedor;
    private PanelExpendedor panelExpendedor;
    private PanelComprador panelComprador;

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
            // Código adicional para dibujar si es necesario
        }

        public int getItem() {
            return panelExpendedor.getItem();
        }
    }
