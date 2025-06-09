package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.example.Paneles.PanelComprador;
import org.example.Paneles.PanelExpendedor;
import org.example.Paneles.PanelMonedero;


public class PanelPrincipal extends JPanel {
    private Deposito monedero;
    private Expendedor expendedor;
    private PanelExpendedor panelExpendedor;
    private PanelComprador panelComprador;
    private PanelMonedero panelMonedero;



    public PanelPrincipal(int numProduc,int presupuesto) {
        monedero = new Deposito<Moneda>();
        setLayout(new BorderLayout());
        //aca creamos las clases de la logica

        Monedero monedero = new Monedero(presupuesto);

        // Crear el expendedor con  productos de cada tipo
        expendedor = new Expendedor(numProduc);

        // Crear los paneles
        panelExpendedor  = new PanelExpendedor(expendedor);
        panelComprador = new PanelComprador(expendedor,panelExpendedor,monedero);
        panelMonedero = new PanelMonedero(presupuesto);

        // Añadir paneles al layout
        add(panelComprador.getPanelMonedas(), BorderLayout.WEST);
        add(panelExpendedor, BorderLayout.CENTER);
        add(panelComprador.getPanelResultado(), BorderLayout.EAST);
        add(panelMonedero, BorderLayout.EAST);

        // Configurar el manejo de eventos del mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Delegar el evento al panel correspondiente
                panelComprador.procesarClick(e);
                panelExpendedor.actualizarInventarioVisual();
                repaint(); // Actualizar la visualización
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
    public int getMon(){
        return panelComprador.getMoneda();
    }

}