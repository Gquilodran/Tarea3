package org.example;

/**
 * Clase extendida de  {@link Exception} que crea una excepción cuando el pago es incorrecto
 * (cuando moneda vale null).
 */
public class PagoIncorrectoExcepcion extends Exception {

    /**
     * Constructor que crea una instancia de {@link PagoIncorrectoExcepcion},
     * con un mensaje por defecto.
     */
    public PagoIncorrectoExcepcion(){
        super("Debe ingresar una moneda valida");
    }
}