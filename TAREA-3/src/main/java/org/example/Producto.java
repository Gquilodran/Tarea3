package org.example;

/** Clase abstracta que simula a un producto y almacena su sabor
 * Es la base para la creacion de los multiples productos de la maquina
 */
public abstract class Producto {
    private final int id;
    private static int ultimoProducto = 0;
    /** String que almacena el sabor del producto */
    protected String sabor;

    /** constructor vacio para el uso de clases hijas  */
    public Producto(){
        this.id = ultimoProducto++;
    }
    /** Metodo getter para obtener el sabor del producto
     * @return el sabor del producto
     */
    public String getSabor() {
        return sabor;
    }
    public int getId() {
        return id;
    }
}