package org.example;

/**
 * Clase extendida de  {@link Exception} que crea una excepción cuando no quedan productos disponibles.
 */
public class NoHayProductoExcepcion extends Exception {

    /**
     * Constructor que crea una instancia de la excepción {@link NoHayProductoExcepcion}.
     * Usa un mensaje por defecto.
     */
    public NoHayProductoExcepcion(){
        super("El producto se encuentra agotado");
    }

    /**
     * Constructor que crea una instancia de la excepción con un mensaje personalizado
     * @param texto el mensaje personalizado que se ocupara en esta instancia de <code>NoHayProductoExcepcion</code>
     */
    public NoHayProductoExcepcion(String texto){
        super(texto);
    }
}