package org.example.Paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelNumerico extends JPanel implements ActionListener {
    private JButton[] botones;
    private int valorSeleccionado = 0;
    private ActionListener listener;

    public PanelNumerico() {
        // Configuramos el panel
        setLayout(new GridLayout(2, 3, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Creamos los botones numéricos
        botones = new JButton[6];
        for (int i = 0; i < 6; i++) {
            int numero = i + 1;
            botones[i] = new JButton(String.valueOf(numero));
            botones[i].setFont(new Font("Arial", Font.BOLD, 16));
            botones[i].addActionListener(this);
            add(botones[i]);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujamos un fondo para el panel
        g.setColor(new Color(240, 240, 240));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dibujamos un borde alrededor del panel
        g.setColor(Color.GRAY);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }


    /**
     * Obtiene el valor del botón seleccionado
     * @return el número seleccionado (1-6) o 0 si no hay selección
     */
    public int getValorSeleccionado() {
        return valorSeleccionado;
    }

    /**
     * Resetea la selección actual
     */
    public void resetSeleccion() {
        valorSeleccionado = 0;
        for (JButton boton : botones) {
            boton.setBackground(UIManager.getColor("Button.background"));
        }
        repaint();
    }

    public void setOnNumberSelectedListener(ActionListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Identificamos qué botón se presionó
        for (int i = 0; i < botones.length; i++) {
            if (e.getSource() == botones[i]) {
                valorSeleccionado = i + 1; // Guardamos el valor del botón

                // Resaltamos visualmente el botón seleccionado
                for (int j = 0; j < botones.length; j++) {
                    if (j == i) {
                        botones[j].setBackground(new Color(173, 216, 230)); // Azul claro
                    } else {
                        botones[j].setBackground(UIManager.getColor("Button.background"));
                    }
                }

                // NUEVO: Notificar al listener externo con el valor seleccionado
                if (listener != null) {
                    listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                            String.valueOf(valorSeleccionado)));
                }

                break;
            }
        }

        repaint();
    }
}