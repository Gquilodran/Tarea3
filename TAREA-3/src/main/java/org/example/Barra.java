package org.example;



import javax.swing.*;
import java.awt.*;

public class Barra extends JProgressBar {

    public Barra(int x, int y, int ancho, int alto) {
        super();
        // color de la barra
        this.setIndeterminate(true);
        this.setBorderPainted(false);
        this.setOpaque(true);
        this.setBackground(Color.DARK_GRAY);

        this.setBounds(x, y, ancho, alto);   // psocion y tamaño de la barra
    }
}