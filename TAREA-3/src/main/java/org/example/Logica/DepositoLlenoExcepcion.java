package org.example.Logica;

/**
 * Clase extendida de  {@link Exception} que crea una excepción cuando el deposito de productos está lleno
 */
public class DepositoLlenoExcepcion extends Exception {

    /**
     * Constructor que crea una instancia de {@link PagoIncorrectoExcepcion},
     * con un mensaje por defecto.
     */
    public DepositoLlenoExcepcion(){
        super("Por favor, retire el producto antes de hacer otra compra");
    }
}