package org.example.Paneles;

import org.example.Deposito;
import org.example.Moneda;
import org.example.Monedero;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class PanelMonedero extends JPanel{

    private JPanel panelMonedasVueltoDisplay;
    private JPanel panelMonedero;
    private JPanel panelMonederoDisplay;
    private JButton obtenerVueltoButton;
    private Deposito<Moneda> monedasVueltoDeposito;
    private Monedero monedero;
    public PanelMonedero(int presupuesto) {
        super();
        this.monedasVueltoDeposito = new Deposito<Moneda>();
        inicializarComponentes();
        monedero = new Monedero(presupuesto);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(5, 5)); // Cambiado a BorderLayout para un solo panel central
        setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 10));

        JPanel panelMonederoPrincipal = new JPanel();
        panelMonederoPrincipal.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
        panelMonederoPrincipal.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Monedero",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 10)));
        add(panelMonederoPrincipal, BorderLayout.CENTER); // Añadido al centro del BorderLayout
        // actualizarVueltoVisual(new ArrayList<>()); // Esta línea probablemente no es necesaria si solo es el monedero
    }


    public void actualizarVueltoVisual(List<Moneda> monedas) {
        this.monedasVueltoDeposito = new Deposito<>();
        if (monedas != null) {
            for (Moneda m : monedas) {
                this.monedasVueltoDeposito.addProducto(m);
            }
        }
        }
}