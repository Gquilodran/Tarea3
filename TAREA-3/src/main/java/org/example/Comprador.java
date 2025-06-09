package org.example;
/**
 * Clase que representa al un comprador que hace uso del expendedor
 */
public class Comprador {
    /**
     * Esta variable almacena el sabor de loq ue compre
     */

    private Monedero monedero; // llenar desde el cosntructor de aca se scaran las monedas

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
     * @param cualProducto  Índice o tipo de producto que se desea comprar.
     * @param exp           Referencia al expendedor donde se realiza la compra.
     * @throws PagoInsuficienteExcepcion  Si el dinero entregado no es suficiente.
     * @throws NoHayProductoExcepcion     Si no hay producto disponible del tipo solicitado.
     * @throws PagoIncorrectoExcepcion    Si la moneda entregada es inválida (por ejemplo, null).
     */
    public Comprador(Monedero monedero ,int pago, int cualProducto, Expendedor exp)       throws PagoInsuficienteExcepcion, NoHayProductoExcepcion, PagoIncorrectoExcepcion {
        this.monedero = monedero;
        this.expendedor = exp;
        Moneda m = monedero.getMoneda(pago);
        // Guarda el expendedor
        Producto producto = null; //almacena el producto
        exp.comprarProducto(m, cualProducto); //intenta comprar el producto (PRODUCTO QUEDA GUARDADO EN EXPENDEDOR)
        vuelto = exp.getUltimoVuelto();
        producto = exp.getProductoComprado();
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
    public void guardaVuelto(int i) {
        while (i != 0) {
            if (i >= 1000) {
                monedero.addProducto(new Moneda1000());
                i-=1000;
            } else if (i >= 500) {
                monedero.addProducto(new Moneda500());
                i-=500;
            } else if (i >= 100) {
                monedero.addProducto(new Moneda100());
                i-=100;
            }
        }
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