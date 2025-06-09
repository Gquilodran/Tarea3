package org.example.Paneles;

import org.example.Logica.*;

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

    // Valores para las monedas en el monedero
    private static final int MONEDA_100 = 1;
    private static final int MONEDA_500 = 2;
    private static final int MONEDA_1000 = 3;

    // Estado actual
    private int estadoActual = ESTADO_SELECCION_MONEDA;

    private Expendedor expendedor;
    private PanelExpendedor panelExpendedor;
    private Monedero monedero;

    private int monedaSeleccionadaID = 0; // Para identificar qué moneda se seleccionó (1:100, 2:500, 3:1000)
    private Producto productoComprado = null;
    private int vueltoTotal = 0;
    private ArrayList<Moneda> monedasVuelto = new ArrayList<>();

    // Paneles principales
    private JPanel panelMonedas;
    private JPanel panelResultado;
    private PanelNumerico panelNumerico;
    private PanelConsola panelConsola;

    // Componentes para monedas
    private JButton botonMoneda100;
    private JButton botonMoneda500;
    private JButton botonMoneda1000;
    private JButton botonReset;
    private JButton botonCompra;
    private JLabel labelEstado;

    // Iconos de monedas
    private ImageIcon iconoMoneda100;
    private ImageIcon iconoMoneda500;
    private ImageIcon iconoMoneda1000;

    /**
     * Constructor del panel de comprador
     * @param expendedor referencia al expendedor
     * @param panelExpendedor referencia al panel del expendedor
     * @param monedero referencia al monedero del usuario
     */
    public PanelComprador(Expendedor expendedor, PanelExpendedor panelExpendedor, Monedero monedero) {
        this.expendedor = expendedor;
        this.panelExpendedor = panelExpendedor;
        this.monedero = monedero;

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

        // Crear e inicializar el panel de consola
        panelConsola = new PanelConsola();

        // Crear un panel central que contendrá monedas y panel numérico
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));

        // Panel para los botones de monedas (en fila horizontal)
        JPanel panelBotonesMonedas = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        // Cargar imágenes para las monedas
        iconoMoneda100 = new ImageIcon(getClass().getClassLoader().getResource("moneda100.png"));
        iconoMoneda500 = new ImageIcon(getClass().getClassLoader().getResource("moneda500.png"));
        iconoMoneda1000 = new ImageIcon(getClass().getClassLoader().getResource("moneda1000.png"));

        // Redimensiona las imágenes
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

        // Panel de información y botones de acción
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

        // Estado actual
        labelEstado = new JLabel("Seleccione una moneda");
        labelEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEstado.setFont(new Font("Arial", Font.BOLD, 14));

        // Botón de compra
        botonCompra = new JButton("Comprar");
        botonCompra.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonCompra.addActionListener(this);
        botonCompra.setEnabled(false); // Inicialmente desactivado hasta seleccionar moneda

        // Botón de reset
        botonReset = new JButton("Reiniciar");
        botonReset.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonReset.addActionListener(this);

        panelInfo.add(labelEstado);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(botonCompra);
        panelInfo.add(Box.createVerticalStrut(5));
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
        // Crear instancia de tu PanelNumerico personalizado
        panelNumerico = new PanelNumerico();

        // Registrar listener para actualizar consola inmediatamente
        panelNumerico.setOnNumberSelectedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(e.getActionCommand());
                panelConsola.actualizarInformacionProducto(codigo, PrecioProductos.values());
            }
        });

        // Crear panel contenedor con borde
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createTitledBorder("Panel Numérico"));
        contenedor.add(panelNumerico, BorderLayout.CENTER);

        return contenedor;
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

        // Este panel se completará más adelante con el vuelto
        JPanel panelVuelto = new JPanel();
        panelVuelto.setLayout(new BoxLayout(panelVuelto, BoxLayout.Y_AXIS));
        panelResultado.add(panelVuelto, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonMoneda100) {
            monedaSeleccionadaID = MONEDA_100;
            panelConsola.setMonedaSeleccionada("$100");
            avanzarEstado();
        } else if (e.getSource() == botonMoneda500) {
            monedaSeleccionadaID = MONEDA_500;
            panelConsola.setMonedaSeleccionada("$500");
            avanzarEstado();
        } else if (e.getSource() == botonMoneda1000) {
            monedaSeleccionadaID = MONEDA_1000;
            panelConsola.setMonedaSeleccionada("$1000");
            avanzarEstado();
        } else if (e.getSource() == botonCompra && estadoActual == ESTADO_SELECCION_PRODUCTO) {
            realizarCompra();
        } else if (e.getSource() == botonReset) {
            reiniciarCompra();
        }
    }

    private void realizarCompra() {
        int productoSeleccionado = panelNumerico.getValorSeleccionado();

        if (productoSeleccionado <= 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un producto");
            return;
        }

        try {
            // Usamos la clase Comprador para realizar la compra
            Comprador comprador = new Comprador(monedero, monedaSeleccionadaID, productoSeleccionado, expendedor);

            // Actualizar la información después de la compra
            vueltoTotal = comprador.cuantoVuelto();
            panelConsola.setVuelto(vueltoTotal);

            String sabor = comprador.queCompraste();
            if (sabor != null) {
                panelConsola.setProductoSeleccionado(sabor);
                JOptionPane.showMessageDialog(this, "¡Compra exitosa! Recibió: " + sabor);
                panelExpendedor.actualizarInventarioVisual(); // Actualizar el inventario visual

                // En lugar de avanzar al siguiente estado, reiniciamos directamente
                // para permitir una nueva selección de moneda
                reiniciarEstadoParaNuevaCompra();
            }
        } catch (PagoInsuficienteExcepcion e) {
            JOptionPane.showMessageDialog(this, "El pago es insuficiente para este producto");
        } catch (NoHayProductoExcepcion e) {
            JOptionPane.showMessageDialog(this, "No hay stock de este producto");
        } catch (PagoIncorrectoExcepcion e) {
            JOptionPane.showMessageDialog(this, "Moneda inválida");
        }
    }

    // Método nuevo para reiniciar solo el estado sin afectar otros valores
    private void reiniciarEstadoParaNuevaCompra() {
        estadoActual = ESTADO_SELECCION_MONEDA;
        monedaSeleccionadaID = 0;
        panelConsola.setMonedaSeleccionada("Ninguna");
        labelEstado.setText("Seleccione una moneda");

        if (panelNumerico != null) {
            panelNumerico.resetSeleccion();
        }

        actualizarInterfaz();
    }

    public void procesarClick(MouseEvent e) {
        // Método mantenido para compatibilidad, pero no es necesario
    }

    private void avanzarEstado() {
        if (estadoActual == ESTADO_SELECCION_MONEDA) {
            estadoActual = ESTADO_SELECCION_PRODUCTO;
            labelEstado.setText("Seleccione un producto");
        } else if (estadoActual == ESTADO_SELECCION_PRODUCTO) {
            estadoActual = ESTADO_RECEPCION_PRODUCTO;
            labelEstado.setText("Producto comprado");
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
        monedaSeleccionadaID = 0;
        productoComprado = null;
        vueltoTotal = 0;
        monedasVuelto.clear();
        estadoActual = ESTADO_SELECCION_MONEDA;

        // Restaurar etiquetas de la consola
        panelConsola.setMonedaSeleccionada("Ninguna");
        panelConsola.setProductoSeleccionado("Ninguno");
        panelConsola.setPrecio(0);
        panelConsola.setVuelto(0);
        labelEstado.setText("Seleccione una moneda");

        if (panelNumerico != null) {
            panelNumerico.resetSeleccion();
        }
        actualizarInterfaz();
    }

    private void actualizarInterfaz() {
        // Activar/desactivar componentes según el estado
        botonMoneda100.setEnabled(estadoActual == ESTADO_SELECCION_MONEDA);
        botonMoneda500.setEnabled(estadoActual == ESTADO_SELECCION_MONEDA);
        botonMoneda1000.setEnabled(estadoActual == ESTADO_SELECCION_MONEDA);
        botonCompra.setEnabled(estadoActual == ESTADO_SELECCION_PRODUCTO);

        // Refrescar componentes
        panelMonedas.revalidate();
        panelMonedas.repaint();
        panelResultado.revalidate();
        panelResultado.repaint();
    }

    public int getMoneda() {
        return monedaSeleccionadaID;
    }
}