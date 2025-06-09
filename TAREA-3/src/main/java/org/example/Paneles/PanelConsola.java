package org.example.Paneles;

import org.example.Logica.PrecioProductos;

import javax.swing.*;
import java.awt.*;

public class PanelConsola extends JPanel {

    // Etiquetas de la consola
    private JLabel labelMonedaSeleccionada;
    private JLabel labelProductoSeleccionado;
    private JLabel labelPrecio;
    private JLabel labelVuelto;

    public PanelConsola() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Consola"));
        inicializarComponentes();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(230, 230, 250)); // Lavanda claro
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void inicializarComponentes() {
        JLabel labelTituloMoneda = new JLabel("MONEDA SELECCIONADA: ");
        JLabel labelTituloProducto = new JLabel("PRODUCTO SELECCIONADO: ");
        JLabel labelTituloPrecio = new JLabel("PRECIO: ");
        JLabel labelTituloVuelto = new JLabel("VUELTO: ");

        labelMonedaSeleccionada = new JLabel("Ninguna");
        labelProductoSeleccionado = new JLabel("Ninguno");
        labelPrecio = new JLabel("$0");
        labelVuelto = new JLabel("$0");

        JPanel panelInfoMoneda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfoMoneda.setOpaque(false);
        panelInfoMoneda.add(labelTituloMoneda);
        panelInfoMoneda.add(labelMonedaSeleccionada);

        JPanel panelInfoProducto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfoProducto.setOpaque(false);
        panelInfoProducto.add(labelTituloProducto);
        panelInfoProducto.add(labelProductoSeleccionado);

        JPanel panelInfoPrecio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfoPrecio.setOpaque(false);
        panelInfoPrecio.add(labelTituloPrecio);
        panelInfoPrecio.add(labelPrecio);

        JPanel panelInfoVuelto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfoVuelto.setOpaque(false);
        panelInfoVuelto.add(labelTituloVuelto);
        panelInfoVuelto.add(labelVuelto);

        add(panelInfoMoneda);
        add(panelInfoProducto);
        add(panelInfoPrecio);
        add(panelInfoVuelto);
    }

    // Métodos para actualizar la información
    public void setMonedaSeleccionada(String moneda) {
        labelMonedaSeleccionada.setText(moneda);
    }

    public void setProductoSeleccionado(String producto) {
        labelProductoSeleccionado.setText(producto);
    }

    public void setPrecio(int precio) {
        labelPrecio.setText("$" + precio);
    }

    public void setVuelto(int vuelto) {
        labelVuelto.setText("$" + vuelto);
    }

    public void actualizarInformacionProducto(int codigo, PrecioProductos[] productos) {
        for (PrecioProductos producto : productos) {
            if (producto.getNum() == codigo) {
                setProductoSeleccionado(producto.name());
                setPrecio(producto.getPrecio());
                return;
            }
        }
        setProductoSeleccionado("No disponible");
        setPrecio(0);
    }

}
