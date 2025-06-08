package org.example;

/**
 * Clase hija de {@link Moneda} que representa una moneda de valor 500.
 */
public class Moneda500 extends Moneda{
    private int valor;

    /**
     * Crea una instancia de {@link Moneda500} llamando al contructor de la super clase {@link Moneda}
     * y le asigna un valor.
     */
    public Moneda500(){
        super();
        this.valor = 500;

    }

    /**
     * Metodo getter el cuál retorna el valor de la moneda.
     * @return un entero igual a 500.
     */
    public int getValor(){
        return this.valor;
    }
}