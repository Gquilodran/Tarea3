package org.example.Paneles;
import org.example.Expendedor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class PanelPrincipal extends JPanel {
    private Expendedor expendedor;
    private PanelExpendedor panelExpendedor;
    private PanelComprador panelComprador;

    public PanelPrincipal() {
        setLayout(new BorderLayout());

        // Crear el expendedor con 5 productos de cada tipo
        expendedor = new Expendedor(0);

        // Crear los paneles
        panelExpendedor = new PanelExpendedor(expendedor);
        panelComprador = new PanelComprador(expendedor);

        // Añadir paneles al layout
        add(panelComprador.getPanelMonedas(), BorderLayout.WEST);
        add(panelExpendedor, BorderLayout.CENTER);
        add(panelComprador.getPanelResultado(), BorderLayout.EAST);

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
}