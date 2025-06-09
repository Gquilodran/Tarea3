package org.example.Paneles;

import org.example.Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * PanelProducto: Botón visual que permite al usuario ver y recoger el producto comprado.
 */
public class PanelProducto extends JButton {
    private Comprador comprador;

    /**
     * Crea un botón para visualizar el producto comprado.
     * @param comprador referencia al comprador que contiene el producto
     */
    public PanelProducto(Comprador comprador) {
        this.comprador = comprador;

        // Configurar apariencia del botón
        setText("Ver Producto");
        setFont(new Font("Arial", Font.BOLD, 14));
        setBackground(new Color(70, 130, 180)); // Azul acero
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(true);

        // Agregar listener para mostrar ventana al hacer clic
        addActionListener(e -> mostrarVentanaProducto());
    }

    /**
     * Muestra una ventana emergente con la imagen e información del producto comprado.
     * Permite al usuario recoger el producto haciendo clic en la imagen.
     */
    private void mostrarVentanaProducto() {
        Producto producto = comprador.verProductoComprado();
        if (producto == null) {
            JOptionPane.showMessageDialog(this, "No hay producto para mostrar",
                    "Sin producto", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JFrame ventanaProducto = new JFrame("Producto Comprado");
        ventanaProducto.setSize(300, 350);
        ventanaProducto.setLayout(new BorderLayout());
        ventanaProducto.setLocationRelativeTo(this);

        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String nombreImagen = determinarNombreImagen(producto.getSabor());
        ImageIcon iconoProducto = cargarImagen(nombreImagen, 200, 200);

        JLabel etiquetaImagen = new JLabel(iconoProducto);
        etiquetaImagen.setHorizontalAlignment(JLabel.CENTER);

        JLabel etiquetaInfo = new JLabel(producto.getSabor(), JLabel.CENTER);
        etiquetaInfo.setFont(new Font("Arial", Font.BOLD, 16));

        etiquetaImagen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                etiquetaInfo.setText("Serie: " + producto.getSerie());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                etiquetaInfo.setText(producto.getSabor());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Producto productoObtenido = comprador.getProductoComprado();
                if (productoObtenido != null) {
                    JOptionPane.showMessageDialog(ventanaProducto,
                            "Agarraste tu " + productoObtenido.getSabor(),
                            "Producto agarrado", JOptionPane.INFORMATION_MESSAGE);
                    ventanaProducto.dispose();
                }
            }
        });

        panelImagen.add(etiquetaImagen, BorderLayout.CENTER);
        ventanaProducto.add(panelImagen, BorderLayout.CENTER);
        ventanaProducto.add(etiquetaInfo, BorderLayout.SOUTH);

        ventanaProducto.setVisible(true);
    }

    // Métodos privados auxiliares

    private String determinarNombreImagen(String sabor) {
        switch (sabor.toLowerCase()) {
            case "coca cola":
                return "coca.png";
            case "sprite":
                return "sprite.png";
            case "fanta":
                return "fanta.png";
            case "snicker":
                return "snicker.png";
            case "super8":
                return "super8.png";
        }
        return null;
    }

    private ImageIcon cargarImagen(String nombreArchivo, int ancho, int alto) {
        URL imgURL = getClass().getClassLoader().getResource(nombreArchivo);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        }
        return null;
    }
}