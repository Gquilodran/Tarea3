package org.example.Paneles;

import org.example.Logica.Comprador;
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
    private Comprador comprador; // Referencia al comprador

    private ImageIcon iconoMoneda100;
    private ImageIcon iconoMoneda500;
    private ImageIcon iconoMoneda1000;

    private PanelComprador panelComprador;

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
     * Establece el comprador asociado con este panel
     * @param comprador el objeto Comprador que recibirá las monedas recogidas
     */
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
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
        // No limpiar el panel ni la lista de botones existentes

        // Añadir las nuevas monedas a la colección existente
        if (this.monedasVuelto == null) {
            this.monedasVuelto = new ArrayList<>();
        }

        // Añadir las nuevas monedas a la lista existente
        this.monedasVuelto.addAll(monedas);

        // Ordenar todas las monedas usando el Comparable de Moneda (de menor a mayor)
        Collections.sort(this.monedasVuelto);

        // Limpiar el panel y la lista de botones para recrearlos
        removeAll();
        botonesMonedas.clear();

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
     * Método para limpiar completamente el panel de monedas
     */
    public void limpiarVuelto() {
        if (this.monedasVuelto != null) {
            this.monedasVuelto.clear();
        }
        removeAll();
        botonesMonedas.clear();
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
     * Establece el panel comprador asociado con este panel
     * @param panelComprador el PanelComprador que maneja las acciones del comprador
     */

    public void setPanelComprador(PanelComprador panelComprador) {
        this.panelComprador = panelComprador;
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

            // Agregar la moneda al monedero del comprador si existe
            // Eliminar esta línea para evitar agregar la misma moneda dos veces
            // comprador.agregarMoneda(moneda);

            // Mostrar mensaje informativo
            JOptionPane.showMessageDialog(this,
                    "Has recogido una moneda de $" + moneda.getValor() +
                            " (Serie: " + moneda.getSerie() + ")" +
                            "\nLa moneda ha sido agregada a tu monedero.",
                    "Moneda recogida",
                    JOptionPane.INFORMATION_MESSAGE);

            // Agregar la moneda según su tipo (solo mantener esta forma)
            if (moneda.getValor() == 100) {
                comprador.agregarMonedaEspecifica(1);  // MONEDA_100
            } else if (moneda.getValor() == 500) {
                comprador.agregarMonedaEspecifica(2);  // MONEDA_500
            } else if (moneda.getValor() == 1000) {
                comprador.agregarMonedaEspecifica(3);  // MONEDA_1000
            }

            // Actualizar contadores de monedas en el panel comprador
            if (panelComprador != null) {
                panelComprador.actualizarContadoresMonedas();
            }

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