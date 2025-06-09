package org.example;

/**
 * Clase abstracta que representa una moneda.
 * Sera utilizada como una super clase para crear distintos tipos de monedad.
 * Implementa la interfaz {@link Comparable} para comparar objetos tipo <code>Moneda</code>
 */
public abstract class Moneda implements Comparable<Moneda>{
    private int valor;
    private final int idMoneda;
    private static int ultimoIdMoneda = 0;
    /**
     * Constructor por defecto que crea una instancia de <code>Moneda</code>
     */
    public Moneda() {
        this.idMoneda = ultimoIdMoneda++;
    }

    /**
     * Retorna la serie de la moneda, que es la instancia actual de <code>Moneda</code>.
     * Este metodo puede ser sobreescrito por sus subclases
     *
     * @return la instancia de la moneda actual.
     */
    public int getSerie(){
        return this.idMoneda;
    }

    /**
     * Compara el valor de esta moneda con otra moneda
     * @param o el objeto con que se compara esta instancia de moneda
     * @return un valor negativo si esta moneda es menor que <code>o</code>,
     * un valor positivo si esta moneda es mayot de <code>o</code>, y
     * cero si ambas son iguales.
     */
    public int compareTo(Moneda o){
        return Integer.compare(this.getValor(), o.getValor());
    }

    /**
     * Devuelve el valor que tiene la moneda.
     * Metodo abstracto que las clases hijas deberan implementar.
     * @return el valor de la moneda.
     */
    public abstract int getValor();
}