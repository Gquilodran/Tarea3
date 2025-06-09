package org.example.Paneles;

import org.example.Deposito; // Importar tu clase Deposito
import org.example.Moneda;   // Importar tu clase Moneda

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List; // Aunque ahora usas Deposito, List aún es útil para el argumento
import java.util.ArrayList; // Para inicializar Deposito con una lista si Deposito lo permite, o para usar en argumento

public class PanelMonedero extends JPanel implements ActionListener {

    private JPanel panelMonedasVueltoDisplay; // Renombrado para evitar confusión con panelMonedero
    private JPanel panelMonederoBase; // Renombrado para ser más claro, si es el panel de las monedas para insertar
    private JButton obtenerVueltoButton; // Renombrado para mayor claridad
    private Deposito<Moneda> monedasVueltoDeposito; // Atributo para almacenar las monedas de vuelto en un Deposito

    // Constructor de la clase PanelMonedero
    public PanelMonedero() {
        super();
        this.monedasVueltoDeposito = new Deposito<Moneda>();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Layout principal para PanelMonedero (el JPanel principal de esta clase)
        setLayout(new GridLayout(2, 1, 5, 5)); // 2 filas, 1 columna, con espacios entre ellas
        setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 10));
        // Este parece ser el panel donde quieres mostrar el vuelto
        panelMonedasVueltoDisplay = new JPanel();
        panelMonedasVueltoDisplay.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelMonedasVueltoDisplay.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Su Vuelto",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 10)));
        panelMonedasVueltoDisplay.setPreferredSize(new Dimension(100, 80));
        add(panelMonedasVueltoDisplay, BorderLayout.CENTER); // Añadir al PanelMonedero principal

        obtenerVueltoButton = new JButton("Obtener Vuelto");
        obtenerVueltoButton.setFont(new Font("Arial", Font.BOLD, 18));
        obtenerVueltoButton.addActionListener(this);
        add(obtenerVueltoButton, BorderLayout.SOUTH);

        actualizarVueltoVisual(new ArrayList<>());
    }


    public void actualizarVueltoVisual(List<Moneda> monedas) {
        this.monedasVueltoDeposito = new Deposito<>();
        if (monedas != null) {
            for (Moneda m : monedas) {
                this.monedasVueltoDeposito.addProducto(m);
            }
        }

        panelMonedasVueltoDisplay.removeAll();

        List<Moneda> monedasEnDeposito = new ArrayList<>();
        Moneda tempMoneda;

        Deposito<Moneda> tempDeposito = new Deposito<>();
        while (true) {
            tempMoneda = this.monedasVueltoDeposito.getProducto();
            if (tempMoneda == null) break;
            monedasEnDeposito.add(tempMoneda);
            tempDeposito.addProducto(tempMoneda);
        }

        this.monedasVueltoDeposito = tempDeposito;


        if (!monedasEnDeposito.isEmpty()) {
            int totalVuelto = 0;
            for (Moneda moneda : monedasEnDeposito) {
                JLabel labelMoneda = new JLabel("$" + moneda.getValor());
                labelMoneda.setFont(new Font("Arial", Font.BOLD, 16));
                labelMoneda.setHorizontalAlignment(SwingConstants.CENTER);
                panelMonedasVueltoDisplay.add(labelMoneda);
                totalVuelto += moneda.getValor();
            }
            JLabel totalLabel = new JLabel("Total: $" + totalVuelto);
            totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
            totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panelMonedasVueltoDisplay.add(totalLabel);
        } else {
            JLabel labelVacio = new JLabel("No hay vuelto.");
            labelVacio.setHorizontalAlignment(SwingConstants.CENTER);
            panelMonedasVueltoDisplay.add(labelVacio);
        }

        panelMonedasVueltoDisplay.revalidate();
        panelMonedasVueltoDisplay.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == obtenerVueltoButton) {
            mostrarMensajeVuelto();

            Moneda monedaRetirada;
            int totalRetirado = 0;
            while (true) { // Bucle para sacar todas las monedas del depósito
                monedaRetirada = monedasVueltoDeposito.getProducto();
                if (monedaRetirada == null) break; // Si ya no hay más monedas
                totalRetirado += monedaRetirada.getValor();
            }
            actualizarVueltoVisual(new ArrayList<>());
        }
    }

    private int calcularTotalVuelto() {
        int total = 0;
        Deposito<Moneda> tempDeposito = new Deposito<>();
        Moneda tempMoneda;
        while (true) {
            tempMoneda = monedasVueltoDeposito.getProducto();
            if (tempMoneda == null) break;
            total += tempMoneda.getValor();
            tempDeposito.addProducto(tempMoneda);
        }
        this.monedasVueltoDeposito = tempDeposito;
        return total;
    }

    private void mostrarMensajeVuelto() {
        int totalVuelto = calcularTotalVuelto();

        if (totalVuelto > 0) {
            JOptionPane.showMessageDialog(this,
                    "Ha retirado su vuelto: $" + totalVuelto,
                    "Vuelto Entregado",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "No hay vuelto para retirar.",
                    "Sin Vuelto",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}