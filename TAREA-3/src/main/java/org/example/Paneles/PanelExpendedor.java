package org.example.Paneles;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import org.example.Logica.*;

/**
 * PanelExpendedor: Panel gráfico para mostrar y seleccionar productos del expendedor.
 */
public class PanelExpendedor extends JPanel implements ActionListener {

    private Expendedor expendedor;
    private int item;
    private JButton COCA;
    private JButton SPRITE;
    private JButton FANTA;
    private JButton SNICKER;
    private JButton SUPER8;

    private String cualProd;
    private JPanel panelCocaInventario;
    private JPanel panelSpriteInventario;
    private JPanel panelFantaInventario;
    private JPanel panelSnickerInventario;
    private JPanel panelSuper8Inventario;

    private ImageIcon iconoCoca;
    private ImageIcon iconoSprite;
    private ImageIcon iconoFanta;
    private ImageIcon iconoSnicker;
    private ImageIcon iconoSuper8;

    /**
     * Crea el panel expendedor y configura los componentes gráficos.
     * @param expendedor instancia de Expendedor
     */
    public PanelExpendedor(Expendedor expendedor) {
        this.expendedor = expendedor;
        inicializarComponentes();
        cargarImagenesProductos();
        actualizarInventarioVisual();
    }

    /** Devuelve el expendedor asociado. */
    public Expendedor getExpendedor() {
        return expendedor;
    }

    private void cargarImagenesProductos() {
        iconoCoca = cargarImagenProducto("coca.png", 30, 30);
        iconoSprite = cargarImagenProducto("sprite.png", 30, 30);
        iconoFanta = cargarImagenProducto("fanta.png", 30, 30);
        iconoSnicker = cargarImagenProducto("snicker.png", 30, 30);
        iconoSuper8 = cargarImagenProducto("super8.png", 30, 30);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelCocaInventario = crearPanelInventario();
        panelSpriteInventario = crearPanelInventario();
        panelFantaInventario = crearPanelInventario();
        panelSnickerInventario = crearPanelInventario();
        panelSuper8Inventario = crearPanelInventario();

        JPanel panelProductos = new JPanel(new BorderLayout(0, 10));
        JPanel panelBebidasContenedor = new JPanel(new GridLayout(2, 3, 10, 5));
        panelBebidasContenedor.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "BEBIDAS",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)));
        JPanel panelDulcesContenedor = new JPanel(new GridLayout(2, 2, 10, 5));
        panelDulcesContenedor.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "DULCES",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)));
        int tamanoCuadrado = 80;
        COCA = crearBotonProducto("Coca Cola", PrecioProductos.COCA.getPrecio(), Color.RED, tamanoCuadrado, iconoCoca);
        SPRITE = crearBotonProducto("Sprite", PrecioProductos.SPRITE.getPrecio(), Color.GREEN, tamanoCuadrado, iconoSprite);
        FANTA = crearBotonProducto("Fanta", PrecioProductos.FANTA.getPrecio(), Color.ORANGE, tamanoCuadrado, iconoFanta);
        SNICKER = crearBotonProducto("Snicker", PrecioProductos.SNIKERS.getPrecio(), new Color(139, 69, 19), tamanoCuadrado, iconoSnicker);
        SUPER8 = crearBotonProducto("Super 8", PrecioProductos.SUPER8.getPrecio(), new Color(210, 105, 30), tamanoCuadrado, iconoSuper8);

        COCA.addActionListener(this);
        SPRITE.addActionListener(this);
        FANTA.addActionListener(this);
        SNICKER.addActionListener(this);
        SUPER8.addActionListener(this);

        panelBebidasContenedor.add(COCA);
        panelBebidasContenedor.add(SPRITE);
        panelBebidasContenedor.add(FANTA);
        panelBebidasContenedor.add(panelCocaInventario);
        panelBebidasContenedor.add(panelSpriteInventario);
        panelBebidasContenedor.add(panelFantaInventario);

        panelDulcesContenedor.add(SNICKER);
        panelDulcesContenedor.add(SUPER8);
        panelDulcesContenedor.add(panelSnickerInventario);
        panelDulcesContenedor.add(panelSuper8Inventario);

        panelProductos.add(panelBebidasContenedor, BorderLayout.NORTH);
        panelProductos.add(panelDulcesContenedor, BorderLayout.CENTER);
        add(panelProductos, BorderLayout.CENTER);

        panelBebidasContenedor.setPreferredSize(new Dimension(400, 200));
        panelDulcesContenedor.setPreferredSize(new Dimension(400, 150));
    }

    private JPanel crearPanelInventario() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return panel;
    }

    private ImageIcon cargarImagenProducto(String nombreArchivo, int ancho, int alto) {
        URL imgURL = getClass().getClassLoader().getResource(nombreArchivo);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        }
        return null;
    }

    private JButton crearBotonProducto(String nombre, int precio, Color color, int tamano, ImageIcon icono) {
        JButton boton = new JButton();
        boton.setLayout(new BorderLayout(5, 5));
        boton.setBackground(color);
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
        boton.setPreferredSize(new Dimension(tamano, tamano));
        JLabel imagenLabel = new JLabel(icono);
        imagenLabel.setHorizontalAlignment(JLabel.CENTER);
        boton.add(imagenLabel, BorderLayout.CENTER);
        JLabel etiqueta = new JLabel("<html><center>" + nombre + "<br>$" + precio + "</center></html>");
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 12));
        etiqueta.setHorizontalAlignment(JLabel.CENTER);
        boton.add(etiqueta, BorderLayout.SOUTH);
        return boton;
    }

    /** Actualiza visualmente los paneles de inventario. */
    public void actualizarInventarioVisual() {
        actualizarPanelInventario(panelCocaInventario, iconoCoca, expendedor.getCantidadDisponible(PrecioProductos.COCA));
        actualizarPanelInventario(panelSpriteInventario, iconoSprite, expendedor.getCantidadDisponible(PrecioProductos.SPRITE));
        actualizarPanelInventario(panelFantaInventario, iconoFanta, expendedor.getCantidadDisponible(PrecioProductos.FANTA));
        actualizarPanelInventario(panelSnickerInventario, iconoSnicker, expendedor.getCantidadDisponible(PrecioProductos.SNIKERS));
        actualizarPanelInventario(panelSuper8Inventario, iconoSuper8, expendedor.getCantidadDisponible(PrecioProductos.SUPER8));
    }

    private void actualizarPanelInventario(JPanel panel, ImageIcon icono, int cantidad) {
        panel.removeAll();
        panel.setLayout(new GridLayout(1, 6, 2, 0));
        int limite = Math.min(5, cantidad);
        for (int i = 0; i < limite; i++) {
            JLabel label = new JLabel(icono);
            label.setHorizontalAlignment(JLabel.CENTER);
            panel.add(label);
        }
        for (int i = cantidad; i < 5; i++) {
            panel.add(new JLabel());
        }
        JLabel contador = new JLabel(String.valueOf(cantidad), SwingConstants.CENTER);
        contador.setOpaque(true);
        contador.setBackground(Color.LIGHT_GRAY);
        contador.setForeground(Color.BLACK);
        contador.setFont(new Font("Arial", Font.BOLD, 14));
        contador.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.add(contador);
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Maneja la selección de productos y rellena el inventario si está vacío.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        PrecioProductos tipoProducto = null;
        if (e.getSource() == COCA) {
            tipoProducto = PrecioProductos.COCA;
            item=1;
            cualProd="COCA";
        } else if (e.getSource() == SPRITE) {
            tipoProducto = PrecioProductos.SPRITE;
            item=2;
            cualProd="SPRITE";
        } else if (e.getSource() == FANTA) {
            tipoProducto = PrecioProductos.FANTA;
            item=3;
            cualProd="FANTA";
        } else if (e.getSource() == SNICKER) {
            tipoProducto = PrecioProductos.SNIKERS;
            item=4;
            cualProd="SNIKERS";
        } else if (e.getSource() == SUPER8) {
            tipoProducto = PrecioProductos.SUPER8;
            item=5;
            cualProd="super8";
        }
        if (tipoProducto != null && expendedor.getCantidadDisponible(tipoProducto) == 0) {
            expendedor.rellenarDeposito(tipoProducto, 6);
            JOptionPane.showMessageDialog(this,
                    "Se ha rellenado el depósito de " + tipoProducto.name(),
                    "Depósito Rellenado",
                    JOptionPane.INFORMATION_MESSAGE);
            actualizarInventarioVisual();
        }
    }

    /** Devuelve el identificador del producto seleccionado. */
    public int getItem() {
        return item;
    }

    /** Devuelve el nombre del producto seleccionado. */
    public String getcualProd() {
        return this.cualProd;
    }

    /**
     * Agrega un botón visual para mostrar el producto comprado.
     * @param comprador objeto Comprador con el producto comprado
     */
    public void agregarBotonProducto(Comprador comprador) {
        PanelProducto botonProducto = new PanelProducto(comprador);
        JPanel panelEsquina = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelEsquina.setOpaque(false);
        panelEsquina.add(botonProducto);
        this.add(panelEsquina, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }

    /** Pinta el fondo del panel con color lavanda claro. */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(230, 230, 250));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}