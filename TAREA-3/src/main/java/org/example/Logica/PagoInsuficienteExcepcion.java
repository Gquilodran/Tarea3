package org.example.Logica;

/**
 * Clase extendida de  {@link Exception} que crea una excepción cuando el pago es insuficiente.
 * es decir cuando la moneda tiene un valor inferior al del precio del producto deseado.
 */
public class PagoInsuficienteExcepcion extends Exception {

    /**
     * Constructor que crea una instancia de {@link PagoInsuficienteExcepcion}.
     * con un mensaje por defecto.
     */
    public PagoInsuficienteExcepcion(){
        super("Pago insuficiente");
    }
}


