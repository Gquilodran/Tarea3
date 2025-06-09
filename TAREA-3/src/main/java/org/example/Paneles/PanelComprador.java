package org.example.Paneles;

import org.example.Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Panel que gestiona la interacción del usuario comprador con la máquina expendedora.
 * Permite seleccionar monedas, elegir productos, realizar compras y visualizar el vuelto.
 */
public class PanelComprador extends JPanel implements ActionListener {
    // Constantes de estado del panel
    private static final int ESTADO_SELECCION_MONEDA = 0;
    private static final int ESTADO_SELECCION_PRODUCTO = 1;
    private static final int ESTADO_RECEPCION_PRODUCTO = 2;
    private static final int ESTADO_RECEPCION_VUELTO = 3;

    // Identificadores de tipo de moneda
    private static final int MONEDA_100 = 1;
    private static final int MONEDA_500 = 2;
    private static final int MONEDA_1000 = 3;

    // Estado actual del panel
    private int estadoActual = ESTADO_SELECCION_MONEDA;

    private Expendedor expendedor;
    private PanelExpendedor panelExpendedor;
    private Comprador comprador;

    private int monedaSeleccionadaID = 0; // Identificador de la moneda seleccionada
    private Producto productoComprado = null;
    private int vueltoTotal = 0;
    private ArrayList<Moneda> monedasVuelto = new ArrayList<>();

    // Paneles principales
    private JPanel panelMonedas;
    private JPanel panelResultado;
    private PanelNumerico panelNumerico;
    private PanelConsola panelConsola;
    private PanelMonedero panelMonedero;

    // Componentes para monedas
    private JButton botonMoneda100;
    private JButton botonMoneda500;
    private JButton botonMoneda1000;
    private JButton botonReset;
    private JButton botonCompra;
    private JLabel labelEstado;

    // Etiquetas para mostrar cantidad de monedas
    private JLabel labelContador100;
    private JLabel labelContador500;
    private JLabel labelContador1000;

    // Iconos de monedas
    private ImageIcon iconoMoneda100;
    private ImageIcon iconoMoneda500;
    private ImageIcon iconoMoneda1000;

    // Botón para ver producto comprado
    private PanelProducto panelProductoBtn;

    /**
     * Constructor del panel del comprador.
     *
     * @param expendedor      referencia al expendedor asociado
     * @param panelExpendedor referencia al panel del expendedor
     * @param monedas100      cantidad de monedas de 100 iniciales
     * @param monedas500      cantidad de monedas de 500 iniciales
     * @param monedas1000     cantidad de monedas de 1000 iniciales
     */
    public PanelComprador(Expendedor expendedor, PanelExpendedor panelExpendedor, int monedas100, int monedas500, int monedas1000) {
        this.expendedor = expendedor;
        this.panelExpendedor = panelExpendedor;
        this.comprador = new Comprador(monedas100, monedas500, monedas1000);

        setLayout(new BorderLayout());

        // Inicialización de paneles y componentes
        panelMonedas = new JPanel();
        panelResultado = new JPanel();
        panelMonedero = new PanelMonedero();
        panelMonedero.setComprador(this.comprador);
        panelMonedero.setPanelComprador(this);

        inicializarPanelMonedas();
        inicializarPanelResultado();
        actualizarInterfaz();
        actualizarContadoresMonedas();
    }

    /**
     * Devuelve el panel de monedas.
     *
     * @return el panel de monedas
     */
    public JPanel getPanelMonedas() {
        return panelMonedas;
    }

    /**
     * Devuelve el panel de resultado.
     *
     * @return el panel de resultado
     */
    public JPanel getPanelResultado() {
        return panelResultado;
    }

    /**
     * Inicializa el panel de selección de monedas y sus componentes.
     */
    private void inicializarPanelMonedas() {
        panelMonedas.setLayout(new BorderLayout(10, 10));
        panelMonedas.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panelConsola = new PanelConsola();

        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        JPanel panelBotonesMonedas = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        // Carga y redimensiona los iconos de las monedas
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

        // Crea los botones de monedas
        botonMoneda100 = new JButton(iconoMoneda100);
        botonMoneda500 = new JButton(iconoMoneda500);
        botonMoneda1000 = new JButton(iconoMoneda1000);

        configurarBotonMoneda(botonMoneda100);
        configurarBotonMoneda(botonMoneda500);
        configurarBotonMoneda(botonMoneda1000);

        // Etiquetas para mostrar la cantidad de monedas
        labelContador100 = new JLabel("0", SwingConstants.CENTER);
        labelContador500 = new JLabel("0", SwingConstants.CENTER);
        labelContador1000 = new JLabel("0", SwingConstants.CENTER);

        Font fuenteContador = new Font("Arial", Font.BOLD, 12);
        labelContador100.setFont(fuenteContador);
        labelContador500.setFont(fuenteContador);
        labelContador1000.setFont(fuenteContador);

        // Paneles para cada moneda y su contador
        JPanel panelMoneda100 = new JPanel(new BorderLayout(0, 2));
        JPanel panelMoneda500 = new JPanel(new BorderLayout(0, 2));
        JPanel panelMoneda1000 = new JPanel(new BorderLayout(0, 2));

        panelMoneda100.add(botonMoneda100, BorderLayout.CENTER);
        panelMoneda100.add(labelContador100, BorderLayout.SOUTH);

        panelMoneda500.add(botonMoneda500, BorderLayout.CENTER);
        panelMoneda500.add(labelContador500, BorderLayout.SOUTH);

        panelMoneda1000.add(botonMoneda1000, BorderLayout.CENTER);
        panelMoneda1000.add(labelContador1000, BorderLayout.SOUTH);

        panelBotonesMonedas.add(panelMoneda100);
        panelBotonesMonedas.add(panelMoneda500);
        panelBotonesMonedas.add(panelMoneda1000);

        panelCentral.add(panelBotonesMonedas, BorderLayout.NORTH);
        panelCentral.add(inicializarPanelNumerico(), BorderLayout.CENTER);

        // Panel de información y botones de acción
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

        labelEstado = new JLabel("Seleccione una moneda");
        labelEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEstado.setFont(new Font("Arial", Font.BOLD, 14));

        botonCompra = new JButton("Comprar");
        botonCompra.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonCompra.addActionListener(this);
        botonCompra.setEnabled(false);

        botonReset = new JButton("Reiniciar");
        botonReset.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonReset.addActionListener(this);

        panelInfo.add(labelEstado);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(botonCompra);
        panelInfo.add(Box.createVerticalStrut(5));
        panelInfo.add(botonReset);

        panelExpendedor.agregarBotonProducto(comprador);

        panelMonedas.add(panelConsola, BorderLayout.NORTH);
        panelMonedas.add(panelCentral, BorderLayout.CENTER);
        panelMonedas.add(panelInfo, BorderLayout.SOUTH);
    }

    /**
     * Actualiza los contadores de monedas en el panel.
     */
    public void actualizarContadoresMonedas() {
        labelContador100.setText(contarMonedasPorTipo(MONEDA_100) + "");
        labelContador500.setText(contarMonedasPorTipo(MONEDA_500) + "");
        labelContador1000.setText(contarMonedasPorTipo(MONEDA_1000) + "");
    }

    /**
     * Cuenta las monedas de un tipo específico en el monedero del comprador.
     *
     * @param tipoMoneda el tipo de moneda a contar (1: 100, 2: 500, 3: 1000)
     * @return la cantidad de monedas de ese tipo
     */
    private int contarMonedasPorTipo(int tipoMoneda) {
        if (comprador != null) {
            return comprador.contarMonedas(tipoMoneda);
        }
        return 0;
    }

    /**
     * Inicializa el panel numérico para seleccionar productos.
     *
     * @return el panel numérico configurado
     */
    private JPanel inicializarPanelNumerico() {
        panelNumerico = new PanelNumerico();

        panelNumerico.setOnNumberSelectedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(e.getActionCommand());
                panelConsola.actualizarInformacionProducto(codigo, PrecioProductos.values());
            }
        });

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createTitledBorder("Panel Numérico"));
        contenedor.add(panelNumerico, BorderLayout.CENTER);

        return contenedor;
    }

    /**
     * Configura un botón de moneda con sus propiedades y acción.
     *
     * @param boton el botón a configurar
     */
    private void configurarBotonMoneda(JButton boton) {
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.addActionListener(this);
    }

    /**
     * Inicializa el panel de resultado que muestra el vuelto y las monedas.
     */
    private void inicializarPanelResultado() {
        panelResultado.setLayout(new BorderLayout());
        panelResultado.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelResultado.setPreferredSize(new Dimension(150, 400));

        JLabel etiquetaTemporal = new JLabel("MONEDAS VUELTO");
        etiquetaTemporal.setHorizontalAlignment(SwingConstants.CENTER);
        panelResultado.add(etiquetaTemporal, BorderLayout.NORTH);

        panelResultado.add(panelMonedero, BorderLayout.CENTER);
    }

    /**
     * Maneja los eventos de los botones del panel.
     *
     * @param e evento de acción
     */
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

    /**
     * Realiza la compra del producto seleccionado, gestiona el vuelto y actualiza la interfaz.
     */
    private void realizarCompra() {
        try {
            comprador.verificarDepositoVacio();
        } catch (DepositoLlenoExcepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }

        int productoSeleccionado = panelNumerico.getValorSeleccionado();

        if (productoSeleccionado <= 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un producto");
            return;
        }

        Moneda monedaUsada = comprador.sacarMoneda(monedaSeleccionadaID);

        if (monedaUsada == null) {
            JOptionPane.showMessageDialog(this,
                    "No hay monedas de este tipo disponibles en su monedero");
            reiniciarEstadoParaNuevaCompra();
            return;
        }

        try {
            expendedor.comprarProducto(monedaUsada, productoSeleccionado);

            Producto producto = expendedor.getProductoDep();
            int vueltoRecibido = expendedor.getUltimoVuelto();

            comprador.registrarCompra(producto, vueltoRecibido);

            Moneda monedaVuelto;
            monedasVuelto.clear();

            while ((monedaVuelto = expendedor.getVuelto()) != null) {
                monedasVuelto.add(monedaVuelto);
            }

            productoComprado = producto;
            vueltoTotal = vueltoRecibido;
            panelConsola.setVuelto(vueltoTotal);

            panelMonedero.actualizarVueltoVisual(monedasVuelto);

            String sabor = comprador.queCompraste();
            if (sabor != null) {
                panelConsola.setProductoSeleccionado(sabor);
                JOptionPane.showMessageDialog(this, "¡Compra exitosa! Recibió: " + sabor);
                panelExpendedor.actualizarInventarioVisual();
                actualizarContadoresMonedas();
                comprador.guardarProductoComprado(productoComprado);

                if (panelProductoBtn != null) {
                    panelProductoBtn.repaint();
                }

                reiniciarCompra();
            }
        } catch (PagoInsuficienteExcepcion e) {
            JOptionPane.showMessageDialog(this, "El pago es insuficiente para este producto");
            comprador.agregarMoneda(monedaUsada);
            actualizarContadoresMonedas();
            reiniciarCompra();
        } catch (NoHayProductoExcepcion e) {
            JOptionPane.showMessageDialog(this, "No hay stock de este producto");
            comprador.agregarMoneda(monedaUsada);
            actualizarContadoresMonedas();
            reiniciarCompra();
        } catch (PagoIncorrectoExcepcion e) {
            JOptionPane.showMessageDialog(this, "Moneda inválida");
            comprador.agregarMoneda(monedaUsada);
            actualizarContadoresMonedas();
            reiniciarCompra();
        }
    }

    /**
     * Reinicia el estado del panel para una nueva compra.
     */
    private void reiniciarEstadoParaNuevaCompra() {
        estadoActual = ESTADO_SELECCION_MONEDA;
        monedaSeleccionadaID = 0;
        panelConsola.setMonedaSeleccionada("Ninguna");
        labelEstado.setText("Seleccione una moneda");

        if (panelNumerico != null) {
            panelNumerico.resetSeleccion();
        }

        if (panelProductoBtn != null) {
            panelProductoBtn.repaint();
        }

        actualizarInterfaz();
    }

    /**
     * Reinicia todos los campos y la interfaz para comenzar una nueva compra.
     */
    private void reiniciarCompra() {
        monedaSeleccionadaID = 0;
        productoComprado = null;
        vueltoTotal = 0;
        estadoActual = ESTADO_SELECCION_MONEDA;

        panelConsola.setMonedaSeleccionada("Ninguna");
        panelConsola.setProductoSeleccionado("Ninguno");
        panelConsola.setPrecio(0);
        panelConsola.setVuelto(0);
        labelEstado.setText("Seleccione una moneda");

        if (panelNumerico != null) {
            panelNumerico.resetSeleccion();
        }

        if (panelProductoBtn != null) {
            panelProductoBtn.repaint();
        }

        actualizarInterfaz();
    }

    /**
     * Método para procesar clics del mouse (no utilizado actualmente).
     *
     * @param e evento de mouse
     */
    public void procesarClick(MouseEvent e) {
        // Método mantenido para compatibilidad, pero no es necesario
    }

    /**
     * Avanza el estado del panel según la etapa de la compra.
     */
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

    /**
     * Actualiza la interfaz gráfica según el estado actual.
     */
    private void actualizarInterfaz() {
        botonMoneda100.setEnabled(estadoActual == ESTADO_SELECCION_MONEDA);
        botonMoneda500.setEnabled(estadoActual == ESTADO_SELECCION_MONEDA);
        botonMoneda1000.setEnabled(estadoActual == ESTADO_SELECCION_MONEDA);
        botonCompra.setEnabled(estadoActual == ESTADO_SELECCION_PRODUCTO);

        panelMonedas.revalidate();
        panelMonedas.repaint();
        panelResultado.revalidate();
        panelResultado.repaint();
    }

    /**
     * Devuelve el identificador de la moneda seleccionada.
     *
     * @return identificador de la moneda seleccionada
     */
    public int getMoneda() {
        return monedaSeleccionadaID;
    }

     @Override
     public void paintComponent(Graphics g) {
     super.paintComponent(g);}
     }