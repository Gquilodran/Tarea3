package org.example.Paneles;

import org.example.Logica.PrecioProductos;

import javax.swing.*;
import java.awt.*;

/**
 * PanelConsola muestra información relevante sobre la selección de moneda,
 * producto, precio y vuelto en la interfaz gráfica de la máquina expendedora.
 */
public class PanelConsola extends JPanel {

    /** Etiqueta que muestra la moneda seleccionada */
    private JLabel labelMonedaSeleccionada;
    /** Etiqueta que muestra el producto seleccionado */
    private JLabel labelProductoSeleccionado;
    /** Etiqueta que muestra el precio del producto seleccionado */
    private JLabel labelPrecio;
    /** Etiqueta que muestra el vuelto entregado */
    private JLabel labelVuelto;

    /**
     * Constructor del panel de consola.
     * Inicializa el layout y los componentes visuales.
     */
    public PanelConsola() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Consola"));
        inicializarComponentes();
    }

    /**
     * Pinta el fondo del panel con un color lavanda claro.
     * @param g el contexto gráfico
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(230, 230, 250)); // Lavanda claro
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Inicializa y agrega los componentes de información al panel.
     */
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

    /**
     * Actualiza la etiqueta de moneda seleccionada.
     * @param moneda nombre de la moneda seleccionada
     */
    public void setMonedaSeleccionada(String moneda) {
        labelMonedaSeleccionada.setText(moneda);
    }

    /**
     * Actualiza la etiqueta de producto seleccionado.
     * @param producto nombre del producto seleccionado
     */
    public void setProductoSeleccionado(String producto) {
        labelProductoSeleccionado.setText(producto);
    }

    /**
     * Actualiza la etiqueta de precio.
     * @param precio valor del producto seleccionado
     */
    public void setPrecio(int precio) {
        labelPrecio.setText("$" + precio);
    }

    /**
     * Actualiza la etiqueta de vuelto.
     * @param vuelto cantidad de vuelto entregado
     */
    public void setVuelto(int vuelto) {
        labelVuelto.setText("$" + vuelto);
    }

    /**
     * Actualiza la información del producto seleccionado y su precio,
     * buscando por el código en el arreglo de productos.
     * @param codigo código del producto seleccionado
     * @param productos arreglo de productos disponibles
     */
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