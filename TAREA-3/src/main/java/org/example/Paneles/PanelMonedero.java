package org.example.Paneles;

import org.example.Logica.Moneda;
import org.example.Logica.Moneda100;
import org.example.Logica.Moneda500;
import org.example.Logica.Moneda1000;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Panel que muestra visualmente las monedas del vuelto
 */
public class PanelMonedero extends JPanel implements ActionListener {

    private ArrayList<Moneda> monedasVuelto = new ArrayList<>();
    private ArrayList<JButton> botonesMonedas = new ArrayList<>();

    private ImageIcon iconoMoneda100;
    private ImageIcon iconoMoneda500;
    private ImageIcon iconoMoneda1000;

    /**
     * Constructor que inicializa el panel del monedero
     */
    public PanelMonedero() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(150, 300));
        setBackground(new Color(245, 245, 245));

        // Cargar imágenes de monedas
        cargarImagenesMonedas();
    }

    /**
     * Carga las imágenes de las monedas desde los recursos
     */
    private void cargarImagenesMonedas() {
        // Cargar iconos
        iconoMoneda100 = cargarImagen("moneda100.png", 50, 50);
        iconoMoneda500 = cargarImagen("moneda500.png", 50, 50);
        iconoMoneda1000 = cargarImagen("moneda1000.png", 50, 50);
    }

    /**
     * Método auxiliar para cargar y redimensionar una imagen
     * @param nombreArchivo nombre del archivo de imagen
     * @param ancho ancho deseado
     * @param alto alto deseado
     * @return ImageIcon redimensionado
     */
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

    /**
     * Actualiza la visualización del vuelto con las monedas recibidas
     * @param monedas lista de monedas que representan el vuelto
     */
    public void actualizarVueltoVisual(ArrayList<Moneda> monedas) {
        // Limpiar el panel y la lista de botones
        removeAll();
        botonesMonedas.clear();

        // Guardar las monedas y ordenarlas usando el Comparable de Moneda (de menor a mayor)
        this.monedasVuelto = new ArrayList<>(monedas);
        Collections.sort(this.monedasVuelto);

        // Crear un botón para cada moneda
        for (Moneda moneda : this.monedasVuelto) {
            JButton botonMoneda = crearBotonMoneda(moneda);
            botonesMonedas.add(botonMoneda);
            add(botonMoneda);
        }

        // Actualizar el panel
        revalidate();
        repaint();
    }

    /**
     * Crea un botón con la imagen correspondiente al valor de la moneda
     * @param moneda la moneda a representar
     * @return un botón configurado con la imagen de la moneda
     */
    private JButton crearBotonMoneda(Moneda moneda) {
        JButton boton = new JButton();
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);

        // Asignar imagen según valor de moneda
        if (moneda instanceof Moneda100) {
            boton.setIcon(iconoMoneda100);
            boton.setToolTipText("$100");
        } else if (moneda instanceof Moneda500) {
            boton.setIcon(iconoMoneda500);
            boton.setToolTipText("$500");
        } else if (moneda instanceof Moneda1000) {
            boton.setIcon(iconoMoneda1000);
            boton.setToolTipText("$1000");
        }

        // Añadir información al pasar el cursor
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setToolTipText("Serie: " + moneda.getSerie() + " - Valor: $" + moneda.getValor());
            }
        });

        // Acción al hacer clic (recoger la moneda)
        boton.setActionCommand(String.valueOf(monedasVuelto.indexOf(moneda)));
        boton.addActionListener(this);

        return boton;
    }

    /**
     * Maneja los eventos de clic en los botones de monedas
     * @param e evento de acción
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int indice = Integer.parseInt(e.getActionCommand());
        if (indice >= 0 && indice < monedasVuelto.size()) {
            Moneda moneda = monedasVuelto.get(indice);

            // Mostrar mensaje informativo
            JOptionPane.showMessageDialog(this,
                    "Has recogido una moneda de $" + moneda.getValor() +
                            " (Serie: " + moneda.getSerie() + ")",
                    "Moneda recogida",
                    JOptionPane.INFORMATION_MESSAGE);

            // Eliminar la moneda y su botón
            monedasVuelto.remove(indice);
            remove(botonesMonedas.get(indice));
            botonesMonedas.remove(indice);

            // Actualizar índices para los botones restantes
            for (int i = indice; i < botonesMonedas.size(); i++) {
                botonesMonedas.get(i).setActionCommand(String.valueOf(i));
            }

            // Actualizar el panel
            revalidate();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujamos un fondo para el panel
        g.setColor(new Color(245, 245, 245));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dibujamos un borde
        g.setColor(Color.DARK_GRAY);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}