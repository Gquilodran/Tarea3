package org.example.Logica;

/** Clase abstracta que simula a un producto y almacena su sabor
 * Es la base para la creacion de los multiples productos de la maquina
 */
public abstract class Producto {
    /** String que almacena el sabor del producto */
    protected String sabor;

    /** Número de serie único para cada producto */
    private final int serie;

    /** Contador para generar números de serie únicos */
    private static int contadorSerie = 0;

    /** Constructor para el uso de clases hijas */
    public Producto(){
        this.serie = contadorSerie++;
    }

    /** Metodo getter para obtener el sabor del producto
     * @return el sabor del producto
     */
    public String getSabor() {
        return sabor;
    }

    /** Método para obtener el número de serie del producto
     * @return el número de serie único del producto
     */
    public int getSerie() {
        return serie;
    }
}