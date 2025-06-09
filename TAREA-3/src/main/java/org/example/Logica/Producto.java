package org.example.Logica;

/** Clase abstracta que simula a un producto y almacena su sabor
 * Es la base para la creacion de los multiples productos de la maquina
 */
public abstract class Producto {
    /** String que almacena el sabor del producto */
    protected String sabor;
    /** constructor vacio para el uso de clases hijas  */
    public Producto(){
    }
    /** Metodo getter para obtener el sabor del producto
     * @return el sabor del producto
     */
    public String getSabor() {
        return sabor;
    }
}