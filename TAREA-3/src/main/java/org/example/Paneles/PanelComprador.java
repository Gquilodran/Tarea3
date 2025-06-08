package org.example.Paneles;

import org.example.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PanelComprador extends JPanel implements ActionListener {
    // Estados posibles
    private static final int ESTADO_SELECCION_MONEDA = 0;
    private static final int ESTADO_SELECCION_PRODUCTO = 1;
    private static final int ESTADO_RECEPCION_PRODUCTO = 2;
    private static final int ESTADO_RECEPCION_VUELTO = 3;

    // Estado actual
    private int estadoActual = ESTADO_SELECCION_MONEDA;

    private Expendedor expendedor;
    private Moneda monedaSeleccionada = null;
    private Producto productoComprado = null;
    private int vueltoTotal = 0;
    private ArrayList<Moneda> monedasVuelto = new ArrayList<>();

    // Paneles principales
    private JPanel panelMonedas;
    private JPanel panelResultado;

    // Componentes para monedas
    private JButton botonMoneda100;
    private JButton botonMoneda500;
    private JButton botonMoneda1000;
    private JButton botonReset;
    private JLabel labelEstado;

    // Iconos de monedas
    private ImageIcon iconoMoneda100;
    private ImageIcon iconoMoneda500;
    private ImageIcon iconoMoneda1000;

    // Etiquetas de la consola
    private JLabel labelMonedaSeleccionada;
    private JLabel labelProductoSeleccionado;
    private JLabel labelPrecio;
    private JLabel labelVuelto;

    /**
     * Constructor del panel de comprador
     * @param expendedor referencia al expendedor
     */
    public PanelComprador(Expendedor expendedor) {
        this.expendedor = expendedor;
        setLayout(new BorderLayout());

        // Crear los paneles principales
        panelMonedas = new JPanel();
        panelResultado = new JPanel();

        inicializarPanelMonedas();
        inicializarPanelResultado();
        actualizarInterfaz();
    }

    /**
     * Devuelve el panel de monedas para colocarlo en el layout
     */
    public JPanel getPanelMonedas() {
        return panelMonedas;
    }

    /**
     * Devuelve el panel de resultado para colocarlo en el layout
     */
    public JPanel getPanelResultado() {
        return panelResultado;
    }

    private void inicializarPanelMonedas() {
        panelMonedas.setLayout(new BorderLayout(10, 10));
        panelMonedas.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel para la consola superior
        JPanel panelConsola = new JPanel();
        panelConsola.setLayout(new BoxLayout(panelConsola, BoxLayout.Y_AXIS));
        panelConsola.setBorder(BorderFactory.createTitledBorder("Consola"));

        // Etiquetas de información
        JLabel labelTituloMoneda = new JLabel("MONEDA SELECCIONADA: ");
        JLabel labelTituloProducto = new JLabel("PRODUCTO SELECCIONADO: ");
        JLabel labelTituloPrecio = new JLabel("PRECIO: ");
        JLabel labelTituloVuelto = new JLabel("VUELTO: ");

        labelMonedaSeleccionada = new JLabel("Ninguna");
        labelProductoSeleccionado = new JLabel("Ninguno");
        labelPrecio = new JLabel("$0");
        labelVuelto = new JLabel("$0");

        // Panel para cada línea de información
        JPanel panelInfoMoneda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfoMoneda.add(labelTituloMoneda);
        panelInfoMoneda.add(labelMonedaSeleccionada);

        JPanel panelInfoProducto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfoProducto.add(labelTituloProducto);
        panelInfoProducto.add(labelProductoSeleccionado);

        JPanel panelInfoPrecio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfoPrecio.add(labelTituloPrecio);
        panelInfoPrecio.add(labelPrecio);

        JPanel panelInfoVuelto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfoVuelto.add(labelTituloVuelto);
        panelInfoVuelto.add(labelVuelto);

        // Añadir paneles de información a la consola
        panelConsola.add(panelInfoMoneda);
        panelConsola.add(panelInfoProducto);
        panelConsola.add(panelInfoPrecio);
        panelConsola.add(panelInfoVuelto);

        // Crear un panel central que contendrá monedas y panel numérico
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));

        // Panel para los botones de monedas (ahora en fila horizontal)
        JPanel panelBotonesMonedas = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        // Cargar imágenes para las monedas
        iconoMoneda100 = new ImageIcon(getClass().getClassLoader().getResource("moneda100.png"));
        iconoMoneda500 = new ImageIcon(getClass().getClassLoader().getResource("moneda500.png"));
        iconoMoneda1000 = new ImageIcon(getClass().getClassLoader().getResource("moneda1000.png"));

        // Redimensiona las imagenes
        if (iconoMoneda100 != null) {
            Image img = iconoMoneda100.getImage();
            iconoMoneda100 = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        }
        if (iconoMoneda500 != null) {
            Image img = iconoMoneda500.getImage();
            iconoMoneda500 = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        }
        if (iconoMoneda1000 != null) {
            Image img = iconoMoneda1000.getImage();
            iconoMoneda1000 = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        }

        // Crear botones de monedas con las imágenes
        botonMoneda100 = new JButton(iconoMoneda100);
        botonMoneda500 = new JButton(iconoMoneda500);
        botonMoneda1000 = new JButton(iconoMoneda1000);

        // Configurar botones
        configurarBotonMoneda(botonMoneda100);
        configurarBotonMoneda(botonMoneda500);
        configurarBotonMoneda(botonMoneda1000);

        // Añadir botones de monedas en fila
        panelBotonesMonedas.add(botonMoneda100);
        panelBotonesMonedas.add(botonMoneda500);
        panelBotonesMonedas.add(botonMoneda1000);

        // Añadir el panel de monedas en la parte superior del panel central
        panelCentral.add(panelBotonesMonedas, BorderLayout.NORTH);

        // Añadir el panel numérico en el centro del panel central
        panelCentral.add(inicializarPanelNumerico(), BorderLayout.CENTER);

        // Panel de información
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

        // Estado actual
        labelEstado = new JLabel("Seleccione una moneda");
        labelEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEstado.setFont(new Font("Arial", Font.BOLD, 14));

        // Botón de reset
        botonReset = new JButton("Reiniciar");
        botonReset.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonReset.addActionListener(this);

        panelInfo.add(labelEstado);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(botonReset);

        // Añadir todo al panel principal
        panelMonedas.add(panelConsola, BorderLayout.NORTH);
        panelMonedas.add(panelCentral, BorderLayout.CENTER);
        panelMonedas.add(panelInfo, BorderLayout.SOUTH);
    }

    /**
     * Inicializa el panel numérico
     * @return JPanel con el panel numérico configurado
     */
    private JPanel inicializarPanelNumerico() {

// Crea el panel numérico
        JPanel panelNumerico = new JPanel();
        panelNumerico.setPreferredSize(new Dimension(300, 150));
        panelNumerico.setBorder(BorderFactory.createTitledBorder("Panel Numérico"));
        int i=0;
        panelNumerico.setLayout(new GridLayout(2, 3, 10, 10));
        while(i != 6) {
                JButton boton = new JButton(String.valueOf(i+1));
            boton.setFont(new Font("Arial", Font.PLAIN, 20));
            i++;


            panelNumerico.add(boton);
        }
        return panelNumerico;
    }

    private void configurarBotonMoneda(JButton boton) {
        boton.setFocusPainted(false);           // Quita el borde azul de enfoque
        boton.setBorderPainted(false);          // Quita el borde del botón
        boton.setContentAreaFilled(false);      // Hace transparente el fondo
        boton.addActionListener(this);
    }

    private void inicializarPanelResultado() {
        panelResultado.setLayout(new BorderLayout());
        panelResultado.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelResultado.setPreferredSize(new Dimension(150, 400));

        JLabel etiquetaTemporal = new JLabel("MONEDAS VUELTO");
        etiquetaTemporal.setHorizontalAlignment(SwingConstants.CENTER);
        panelResultado.add(etiquetaTemporal, BorderLayout.NORTH);

        // Este panel se completará más adelante
        JPanel panelVuelto = new JPanel();
        panelVuelto.setLayout(new BoxLayout(panelVuelto, BoxLayout.Y_AXIS));
        panelResultado.add(panelVuelto, BorderLayout.CENTER);
    }

    private JButton crearBotonMoneda(String texto, Color color, ImageIcon icono) {
        JButton boton = new JButton();
        boton.setLayout(new BorderLayout());
        boton.setIcon(icono);

        JLabel etiqueta = new JLabel(texto, SwingConstants.CENTER);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 16));
        etiqueta.setForeground(Color.BLACK);

        boton.add(etiqueta, BorderLayout.CENTER);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.addActionListener(this);

        // Para centrar el botón
        JPanel panelCentrador = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCentrador.setOpaque(false);
        panelCentrador.add(boton);

        return boton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (estadoActual == ESTADO_SELECCION_MONEDA) {
            if (e.getSource() == botonMoneda100) {
                monedaSeleccionada = new Moneda100();
                labelMonedaSeleccionada.setText("$100");
                avanzarEstado();
            } else if (e.getSource() == botonMoneda500) {
                monedaSeleccionada = new Moneda500();
                labelMonedaSeleccionada.setText("$500");
                avanzarEstado();
            } else if (e.getSource() == botonMoneda1000) {
                monedaSeleccionada = new Moneda1000();
                labelMonedaSeleccionada.setText("$1000");
                avanzarEstado();
            }
        }

        if (e.getSource() == botonReset) {
            reiniciarCompra();
        }
    }

    public void procesarClick(MouseEvent e) {
        // Se implementará cuando hagamos la selección de productos
    }

    private void avanzarEstado() {
        if (estadoActual == ESTADO_SELECCION_MONEDA) {
            estadoActual = ESTADO_SELECCION_PRODUCTO;
            labelEstado.setText("Seleccione un producto");
        } else if (estadoActual == ESTADO_SELECCION_PRODUCTO) {
            estadoActual = ESTADO_RECEPCION_PRODUCTO;
            labelEstado.setText("Recoger su producto");
        } else if (estadoActual == ESTADO_RECEPCION_PRODUCTO) {
            estadoActual = ESTADO_RECEPCION_VUELTO;
            labelEstado.setText("Recoger su vuelto");
        } else if (estadoActual == ESTADO_RECEPCION_VUELTO) {
            estadoActual = ESTADO_SELECCION_MONEDA;
            labelEstado.setText("Seleccione una moneda");
        }

        actualizarInterfaz();
    }

    private void reiniciarCompra() {
        monedaSeleccionada = null;
        productoComprado = null;
        vueltoTotal = 0;
        monedasVuelto.clear();
        estadoActual = ESTADO_SELECCION_MONEDA;

        // Restaurar etiquetas
        labelMonedaSeleccionada.setText("Ninguna");
        labelProductoSeleccionado.setText("Ninguno");
        labelPrecio.setText("$0");
        labelVuelto.setText("$0");
        labelEstado.setText("Seleccione una moneda");

        actualizarInterfaz();
    }

    private void actualizarInterfaz() {
        // Activar/desactivar componentes según el estado
        botonMoneda100.setEnabled(estadoActual == ESTADO_SELECCION_MONEDA);
        botonMoneda500.setEnabled(estadoActual == ESTADO_SELECCION_MONEDA);
        botonMoneda1000.setEnabled(estadoActual == ESTADO_SELECCION_MONEDA);

        // Refrescar componentes
        panelMonedas.revalidate();
        panelMonedas.repaint();
        panelResultado.revalidate();
        panelResultado.repaint();
    }
}