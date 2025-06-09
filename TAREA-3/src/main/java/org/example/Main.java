package org.example;

import org.example.Paneles.Ventana;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ventana(6,10, 5, 5); // Aquí se lanza la GUI
        });
    }
}
