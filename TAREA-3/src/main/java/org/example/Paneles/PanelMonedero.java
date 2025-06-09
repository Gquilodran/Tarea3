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
 * PanelMonedero: Panel visual para mostrar y recoger las monedas del vuelto.
 */
public class PanelMonedero extends JPanel implements ActionListener {

    private ArrayList<Moneda> monedasVuelto = new ArrayList<>();
    private ArrayList<JButton> botonesMonedas = new ArrayList<>();
    private Comprador comprador;
    private ImageIcon iconoMoneda100;
    private ImageIcon iconoMoneda500;
    private ImageIcon iconoMoneda1000;
    private PanelComprador panelComprador;

    /** Constructor: inicializa el panel y carga las imágenes de monedas. */
    public PanelMonedero() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(150, 300));
        setBackground(new Color(245, 245, 245));
        cargarImagenesMonedas();
    }

    /** Asocia un comprador a este panel. */
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    /** Actualiza la visualización del vuelto con las monedas recibidas. */
    public void actualizarVueltoVisual(ArrayList<Moneda> monedas) {
        if (this.monedasVuelto == null) {
            this.monedasVuelto = new ArrayList<>();
        }
        this.monedasVuelto.addAll(monedas);
        Collections.sort(this.monedasVuelto);
        removeAll();
        botonesMonedas.clear();
        for (Moneda moneda : this.monedasVuelto) {
            JButton botonMoneda = crearBotonMoneda(moneda);
            botonesMonedas.add(botonMoneda);
            add(botonMoneda);
        }
        revalidate();
        repaint();
    }

    /** Limpia completamente el panel de monedas. */
    public void limpiarVuelto() {
        if (this.monedasVuelto != null) {
            this.monedasVuelto.clear();
        }
        removeAll();
        botonesMonedas.clear();
        revalidate();
        repaint();
    }

    /** Asocia el panel comprador para actualizar contadores. */
    public void setPanelComprador(PanelComprador panelComprador) {
        this.panelComprador = panelComprador;
    }

    /**
     * Maneja los eventos de clic en los botones de monedas.
     * Al recoger una moneda, la agrega al monedero del comprador y actualiza la interfaz.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int indice = Integer.parseInt(e.getActionCommand());
        if (indice >= 0 && indice < monedasVuelto.size()) {
            Moneda moneda = monedasVuelto.get(indice);
            JOptionPane.showMessageDialog(this,
                    "Has recogido una moneda de $" + moneda.getValor() +
                            " (Serie: " + moneda.getSerie() + ")" +
                            "\nLa moneda ha sido agregada a tu monedero.",
                    "Moneda recogida",
                    JOptionPane.INFORMATION_MESSAGE);

            if (moneda.getValor() == 100) {
                comprador.agregarMonedaEspecifica(1);
            } else if (moneda.getValor() == 500) {
                comprador.agregarMonedaEspecifica(2);
            } else if (moneda.getValor() == 1000) {
                comprador.agregarMonedaEspecifica(3);
            }

            if (panelComprador != null) {
                panelComprador.actualizarContadoresMonedas();
            }

            monedasVuelto.remove(indice);
            remove(botonesMonedas.get(indice));
            botonesMonedas.remove(indice);

            for (int i = indice; i < botonesMonedas.size(); i++) {
                botonesMonedas.get(i).setActionCommand(String.valueOf(i));
            }

            revalidate();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(245, 245, 245));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.DARK_GRAY);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    // Métodos privados auxiliares

    private void cargarImagenesMonedas() {
        iconoMoneda100 = cargarImagen("moneda100.png", 50, 50);
        iconoMoneda500 = cargarImagen("moneda500.png", 50, 50);
        iconoMoneda1000 = cargarImagen("moneda1000.png", 50, 50);
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

    private JButton crearBotonMoneda(Moneda moneda) {
        JButton boton = new JButton();
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);

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

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setToolTipText("Serie: " + moneda.getSerie() + " - Valor: $" + moneda.getValor());
            }
        });

        boton.setActionCommand(String.valueOf(monedasVuelto.indexOf(moneda)));
        boton.addActionListener(this);

        return boton;
    }
}