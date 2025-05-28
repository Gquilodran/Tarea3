package org.example;
/**
 * Clase que representa al un comprador que hace uso del expendedor
 */
public class Comprador {
    /**
     * Esta variable almacena el sabor de loq ue compre
     */
    private String sabor;
    /**
     * Esta variable almacena el vuelto
     */
    private int vuelto = 0;
    /**
     * Hace referencia de a que expenderor hace la compra
     */
    Expendedor expendedor = null;
    /**
     * Constructor de la clase Comprador.
     *
     * @param m             Moneda utilizada para realizar la compra.
     * @param cualProducto  Índice o tipo de producto que se desea comprar.
     * @param exp           Referencia al expendedor donde se realiza la compra.
     * @throws PagoInsuficienteExcepcion  Si el dinero entregado no es suficiente.
     * @throws NoHayProductoExcepcion     Si no hay producto disponible del tipo solicitado.
     * @throws PagoIncorrectoExcepcion    Si la moneda entregada es inválida (por ejemplo, null).
     */
    public Comprador(Moneda m, int cualProducto, Expendedor exp) throws PagoInsuficienteExcepcion, NoHayProductoExcepcion, PagoIncorrectoExcepcion {
        this.expendedor = exp; // Guarda el expendedor
        Producto producto = null; //almacena el producto
        producto = exp.comprarProducto(m, cualProducto); //intenta comprar el producto
        Moneda monedaVuelto = exp.getVuelto(); // obttiene el calor de la moneda
        while (monedaVuelto != null) {
            vuelto += monedaVuelto.getValor(); //suma el valor de cada moneda y lo  almacena
            monedaVuelto = exp.getVuelto(); // ve cuanto es el vuelto
        }
        if (producto != null) {
            this.sabor = producto.getSabor(); // guarda sabor si se pudo comprar
        }
    }
    /**
     * Devuelve el monto total del vuelto recibido por el comprador.
     *
     * @return Valor total del vuelto.
     */
    public int cuantoVuelto(){
        return this.vuelto;
    }
    /**
     * Devuelve el sabor del producto que el comprador adquirió.
     *
     * @return Sabor del producto comprado, o {@code null} si no se compró ninguno.
     */
    public String queCompraste(){
        return this.sabor;
    }

}