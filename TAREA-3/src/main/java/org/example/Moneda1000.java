package org.example;

/**
 * Clase hija de {@link Moneda} que representa una moneda de valor 1000.
 */
public class Moneda1000 extends Moneda{
    private int valor;

    /**
     * Crea una instancia de {@link Moneda1000} llamando al contructor de la super clase {@link Moneda}
     * y le asigna un valor.
     */
    public Moneda1000(){
        super();
        this.valor = 1000;
        super.getSerie();
    }
    /**
     * Metodo getter el cuál retorna el valor de la moneda.
     * @return un entero igual a 1000.
     */
    public int getValor(){
        return this.valor;
    }
}