package org.example;

/**
 * Clase hija de {@link Moneda} que representa una moneda de valor 100.
 */
public class Moneda100 extends Moneda{
    private int valor;

    /**
     * Crea una instancia de {@link Moneda100} llamando al contructor de la super clase {@link Moneda}
     * y le asigna un valor.
     */
    public Moneda100(){
        super();
        this.valor = 100;
        ;
    }
    /**
     * Método getter el cuál retorna el valor de la moneda.
     * @return un entero igual a 100.
     */
    public int getValor(){
        return this.valor;
    }
}