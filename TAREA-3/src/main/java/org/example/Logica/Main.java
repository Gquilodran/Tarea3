package org.example.Logica;

import org.example.Paneles.Ventana;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ventana(10,1000); // Aquí se lanza la GUI
        });
    }
}
