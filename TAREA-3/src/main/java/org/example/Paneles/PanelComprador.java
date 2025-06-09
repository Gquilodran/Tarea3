package org.example.Paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import org.example.Logica.*;

public class PanelComprador extends JPanel implements ActionListener {
    // Estados posibles
    private static final int ESTADO_SELECCION_MONEDA = 0;
    private static final int ESTADO_SELECCION_PRODUCTO = 1;
    private static final int ESTADO_RECEPCION_PRODUCTO = 2;
    private static final int ESTADO_RECEPCION_VUELTO = 3;

    private int producto;
    private int moneda;
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
    private JButton botonCompra;

    // Iconos de monedas
    private ImageIcon iconoMoneda100;
    private ImageIcon iconoMoneda500;
    private ImageIcon iconoMoneda1000;

    // Panel consola
    private PanelConsola panelConsola;

    private Monedero monedero;
    private PanelExpendedor panelExpendedor;

    public PanelComprador(Expendedor expendedor, PanelExpendedor panelExpendedor, Monedero monedero) {
        this.expendedor = expendedor;
        this.monedero = monedero;
        this.panelExpendedor = panelExpendedor;
        setLayout(new BorderLayout());

        panelMonedas = new JPanel();
        panelResultado = new JPanel();

        // Instancia de PanelConsola
        panelConsola = new PanelConsola();

        inicializarPanelMonedas();
        inicializarPanelResultado();
        actualizarInterfaz();
    }

    public JPanel getPanelMonedas() {
        return panelMonedas;
    }

    public JPanel getPanelResultado() {
        return panelResultado;
    }

    private void inicializarPanelMonedas() {
        panelMonedas.setLayout(new BorderLayout(10, 10));
        panelMonedas.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Añadir PanelConsola en la parte superior
        panelMonedas.add(panelConsola, BorderLayout.NORTH);

        // Panel central para monedas y panel numérico
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));

        // Panel para los botones de monedas
        JPanel panelBotonesMonedas = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        // Cargar imágenes para las monedas
        iconoMoneda100 = new ImageIcon(getClass().getClassLoader().getResource("moneda100.png"));
        iconoMoneda500 = new ImageIcon(getClass().getClassLoader().getResource("moneda500.png"));
        iconoMoneda1000 = new ImageIcon(getClass().getClassLoader().getResource("moneda1000.png"));

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

        configurarBotonMoneda(botonMoneda100);
        configurarBotonMoneda(botonMoneda500);
        configurarBotonMoneda(botonMoneda1000);

        panelBotonesMonedas.add(botonMoneda100);
        panelBotonesMonedas.add(botonMoneda500);
        panelBotonesMonedas.add(botonMoneda1000);

        panelCentral.add(panelBotonesMonedas, BorderLayout.NORTH);
        panelCentral.add(inicializarPanelNumerico(), BorderLayout.CENTER);

        // Panel de información
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

        labelEstado = new JLabel("Seleccione una moneda");
        labelEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEstado.setFont(new Font("Arial", Font.BOLD, 14));

        botonReset = new JButton("Reiniciar");
        botonReset.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonReset.addActionListener(this);

        panelInfo.add(labelEstado);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(botonReset);

        // Botón de compra
        botonCompra = new JButton("comprar");
        botonCompra.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonCompra.addActionListener(this);

        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(botonCompra);

        panelMonedas.add(panelCentral, BorderLayout.CENTER);
        panelMonedas.add(panelInfo, BorderLayout.SOUTH);
    }

    private JPanel inicializarPanelNumerico() {
        JPanel panelNumerico = new JPanel();
        panelNumerico.setPreferredSize(new Dimension(300, 150));
        panelNumerico.setBorder(BorderFactory.createTitledBorder("Panel Numérico"));
        // Lógica del panel numérico pendiente
        return panelNumerico;
    }

    private void configurarBotonMoneda(JButton boton) {
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.addActionListener(this);
    }

    private void inicializarPanelResultado() {
        panelResultado.setLayout(new BorderLayout());
        panelResultado.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelResultado.setPreferredSize(new Dimension(150, 400));

        JLabel etiquetaTemporal = new JLabel("MONEDAS VUELTO");
        etiquetaTemporal.setHorizontalAlignment(SwingConstants.CENTER);
        panelResultado.add(etiquetaTemporal, BorderLayout.NORTH);

        JPanel panelVuelto = new JPanel();
        panelVuelto.setLayout(new BoxLayout(panelVuelto, BoxLayout.Y_AXIS));
        panelResultado.add(panelVuelto, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (estadoActual == ESTADO_SELECCION_MONEDA) {
            if (e.getSource() == botonMoneda100) {
                monedaSeleccionada = new Moneda100();
                panelConsola.setMonedaSeleccionada("$100");
                moneda = 1;
                avanzarEstado();
            } else if (e.getSource() == botonMoneda500) {
                monedaSeleccionada = new Moneda500();
                panelConsola.setMonedaSeleccionada("$500");
                moneda = 2;
                avanzarEstado();
            } else if (e.getSource() == botonMoneda1000) {
                monedaSeleccionada = new Moneda1000();
                panelConsola.setMonedaSeleccionada("$1000");
                moneda = 3;
                avanzarEstado();
            }
        }

        if (e.getSource() == botonReset) {
            reiniciarCompra();
        }
        if (e.getSource() == botonCompra && estadoActual == ESTADO_SELECCION_PRODUCTO) {
            try {
                Comprador comprador = new Comprador(monedero, moneda, panelExpendedor.getItem(), expendedor);
                String sabor = comprador.queCompraste();
                int vuelto = comprador.cuantoVuelto();
                panelConsola.setProductoSeleccionado(sabor != null ? sabor : "Ninguno");
                panelConsola.setVuelto(vuelto);
                // Si tienes el precio, también puedes actualizarlo:
                // panelConsola.setPrecio(precio);
                labelEstado.setText("Compra exitosa: " + sabor);
                avanzarEstado();
            } catch (PagoInsuficienteExcepcion | NoHayProductoExcepcion | PagoIncorrectoExcepcion ex) {
                throw new RuntimeException(ex);
            }
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

        // Restaurar consola
        panelConsola.setMonedaSeleccionada("Ninguna");
        panelConsola.setProductoSeleccionado("Ninguno");
        panelConsola.setPrecio(0);
        panelConsola.setVuelto(0);
        labelEstado.setText("Seleccione una moneda");

        actualizarInterfaz();
    }

    private void actualizarInterfaz() {
        botonMoneda100.setEnabled(estadoActual == ESTADO_SELECCION_MONEDA);
        botonMoneda500.setEnabled(estadoActual == ESTADO_SELECCION_MONEDA);
        botonMoneda1000.setEnabled(estadoActual == ESTADO_SELECCION_MONEDA);

        panelMonedas.revalidate();
        panelMonedas.repaint();
        panelResultado.revalidate();
        panelResultado.repaint();
    }

    public int getMoneda() {
        return moneda;
    }
}