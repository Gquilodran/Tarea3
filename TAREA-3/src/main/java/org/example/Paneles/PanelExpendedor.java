package org.example.Paneles;

import org.example.Expendedor;
import org.example.PrecioProductos;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class PanelExpendedor extends JPanel implements ActionListener {

    private Expendedor expendedor;
    private int item;
    private JButton COCA;
    private JButton SPRITE;
    private JButton FANTA;
    private JButton SNICKER;
    private JButton SUPER8;

    // Paneles para mostrar el inventario gráficamente
    private JPanel panelCocaInventario;
    private JPanel panelSpriteInventario;
    private JPanel panelFantaInventario;
    private JPanel panelSnickerInventario;
    private JPanel panelSuper8Inventario;

    // Imágenes de productos
    private ImageIcon iconoCoca;
    private ImageIcon iconoSprite;
    private ImageIcon iconoFanta;
    private ImageIcon iconoSnicker;
    private ImageIcon iconoSuper8;

    // Constructor
    public PanelExpendedor(Expendedor expendedor) {
        this.expendedor = expendedor;
        inicializarComponentes();
        cargarImagenesProductos();
        actualizarInventarioVisual();
    }

    // Método para obtener el expendedor
    public Expendedor getExpendedor() {
        return expendedor;
    }

    private void cargarImagenesProductos() {
        // Cargar imágenes de productos
        iconoCoca = cargarImagenProducto("coca.png", 30, 30);
        iconoSprite = cargarImagenProducto("sprite.png", 30, 30);
        iconoFanta = cargarImagenProducto("fanta.png", 30, 30);
        iconoSnicker = cargarImagenProducto("snicker.png", 30, 30);
        iconoSuper8 = cargarImagenProducto("super8.png", 30, 30);
    }


    private void inicializarComponentes() {
        // Layout principal
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Paneles para visualizar el inventario
        panelCocaInventario = crearPanelInventario();
        panelSpriteInventario = crearPanelInventario();
        panelFantaInventario = crearPanelInventario();
        panelSnickerInventario = crearPanelInventario();
        panelSuper8Inventario = crearPanelInventario();

        // Panel principal para productos
        JPanel panelProductos = new JPanel(new BorderLayout(0, 10));

        // Panel para bebidas con sus inventarios
        JPanel panelBebidasContenedor = new JPanel(new GridLayout(2, 3, 10, 5));
        panelBebidasContenedor.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "BEBIDAS",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)));

        // Panel para dulces con sus inventarios
        JPanel panelDulcesContenedor = new JPanel(new GridLayout(2, 2, 10, 5));
        panelDulcesContenedor.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "DULCES",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)));

        // Tamaño para los botones
        int tamanoCuadrado = 80;

        // imagenes
        COCA = crearBotonProducto("Coca Cola", PrecioProductos.COCA.getPrecio(), Color.RED, tamanoCuadrado, iconoCoca);
        SPRITE = crearBotonProducto("Sprite", PrecioProductos.SPRITE.getPrecio(), Color.GREEN, tamanoCuadrado, iconoSprite);
        FANTA = crearBotonProducto("Fanta", PrecioProductos.FANTA.getPrecio(), Color.ORANGE, tamanoCuadrado, iconoFanta);
        SNICKER = crearBotonProducto("Snicker", PrecioProductos.SNIKERS.getPrecio(), new Color(139, 69, 19), tamanoCuadrado, iconoSnicker);
        SUPER8 = crearBotonProducto("Super 8", PrecioProductos.SUPER8.getPrecio(), new Color(210, 105, 30), tamanoCuadrado, iconoSuper8);

        // Registra los actionlistener
        COCA.addActionListener(this);
        SPRITE.addActionListener(this);
        FANTA.addActionListener(this);
        SNICKER.addActionListener(this);
        SUPER8.addActionListener(this);

        // Añadir botones y los paneles de inventario a los contenedores
        panelBebidasContenedor.add(COCA);
        panelBebidasContenedor.add(SPRITE);
        panelBebidasContenedor.add(FANTA);
        panelBebidasContenedor.add(panelCocaInventario);
        panelBebidasContenedor.add(panelSpriteInventario);
        panelBebidasContenedor.add(panelFantaInventario);

        // Añadir botones de dulces y sus inventarios
        panelDulcesContenedor.add(SNICKER);
        panelDulcesContenedor.add(SUPER8);
        panelDulcesContenedor.add(panelSnickerInventario);
        panelDulcesContenedor.add(panelSuper8Inventario);

        // Añadir los paneles al panel principal
        panelProductos.add(panelBebidasContenedor, BorderLayout.NORTH);
        panelProductos.add(panelDulcesContenedor, BorderLayout.CENTER);

        // Añadir el panel de productos al centro
        add(panelProductos, BorderLayout.CENTER);

        // Configura los tamaños
        panelBebidasContenedor.setPreferredSize(new Dimension(400, 200));
        panelDulcesContenedor.setPreferredSize(new Dimension(400, 150));
    }

    private JPanel crearPanelInventario() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return panel;
    }

    private ImageIcon cargarImagenProducto(String nombreArchivo, int ancho, int alto) { // Reutilizar en panel comprador dps
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
        // Crear el botón principal
        JButton boton = new JButton();
        boton.setLayout(new BorderLayout(5, 5));
        boton.setBackground(color);
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
        boton.setPreferredSize(new Dimension(tamano, tamano));

        // Agregar la imagen arriba
        JLabel imagenLabel = new JLabel(icono);
        imagenLabel.setHorizontalAlignment(JLabel.CENTER);
        boton.add(imagenLabel, BorderLayout.CENTER);

        // Crear etiqueta con nombre y precio abajo
        JLabel etiqueta = new JLabel("<html><center>" + nombre + "<br>$" + precio + "</center></html>");
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 12));
        etiqueta.setHorizontalAlignment(JLabel.CENTER);
        boton.add(etiqueta, BorderLayout.SOUTH);

        return boton;
    }

    // Actualiza visualmente todos los paneles de inventario
    public void actualizarInventarioVisual() {
        actualizarPanelInventario(panelCocaInventario, iconoCoca, expendedor.getCantidadDisponible(PrecioProductos.COCA));
        actualizarPanelInventario(panelSpriteInventario, iconoSprite, expendedor.getCantidadDisponible(PrecioProductos.SPRITE));
        actualizarPanelInventario(panelFantaInventario, iconoFanta, expendedor.getCantidadDisponible(PrecioProductos.FANTA));
        actualizarPanelInventario(panelSnickerInventario, iconoSnicker, expendedor.getCantidadDisponible(PrecioProductos.SNIKERS));
        actualizarPanelInventario(panelSuper8Inventario, iconoSuper8, expendedor.getCantidadDisponible(PrecioProductos.SUPER8));
    }

    private void actualizarPanelInventario(JPanel panel, ImageIcon icono, int cantidad) {
        panel.removeAll();

        // Usar GridLayout para distribuir los productos en una sola fila
        panel.setLayout(new GridLayout(1, 6, 2, 0));
        // limitador de mostrar 5 imagenes max
        int limite = Math.min(5, cantidad);  // Si hay más de 5, solo muestra 5
        for (int i = 0; i < limite; i++) {
            JLabel label = new JLabel(icono);
            label.setHorizontalAlignment(JLabel.CENTER);
            panel.add(label);
        }

        // Si hay menos de 5 productos, añadir espacios vacíos para mantener el tamaño
        for (int i = cantidad; i < 5; i++) {
            panel.add(new JLabel());
        }

        //agregar contador de productos
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // Identificar qué botón de producto se presionó
        PrecioProductos tipoProducto = null;

        if (e.getSource() == COCA) {
            tipoProducto = PrecioProductos.COCA;
            item=1;
        } else if (e.getSource() == SPRITE) {
            tipoProducto = PrecioProductos.SPRITE;
            item=2;
        } else if (e.getSource() == FANTA) {
            tipoProducto = PrecioProductos.FANTA;
            item=3;
        } else if (e.getSource() == SNICKER) {
            tipoProducto = PrecioProductos.SNIKERS;
            item=4;
        } else if (e.getSource() == SUPER8) {
            tipoProducto = PrecioProductos.SUPER8;
            item=5;
        }



        // Si es un botón de producto y está vacío, rellenar // quitar esto
        if (tipoProducto != null && expendedor.getCantidadDisponible(tipoProducto) == 0) {
            expendedor.rellenarDeposito(tipoProducto, 8); // Rellenar con 5 unidades
            JOptionPane.showMessageDialog(this,
                    "Se ha rellenado el depósito de " + tipoProducto.name(),
                    "Depósito Rellenado",
                    JOptionPane.INFORMATION_MESSAGE);
            actualizarInventarioVisual();
        }
    }
    //retorna el item para el main
    public int getItem() {
        return item;
    }

    private Expendedor getExpendedorPanel() {
        return expendedor; // Para hacer la compra en el panel comprador
    }

}