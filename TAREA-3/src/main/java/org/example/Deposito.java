package org.example;
import java.util.ArrayList;

/**
 *Clase que representa deposito generico que almacena objetos del tipo <code>T</code>
 * @param <T> es el tipo de elemento que almacenara <code>Deposito</code>
 */
public class Deposito<T>{
    private ArrayList<T> deposito;

    /**
     *Crea un objeto <code>Deposito</code> e inicializa un <code>ArrayList</code> vacio.
     */
    public Deposito(){
        deposito = new ArrayList<T>();
    }

    /**
     * Añader un objeto generico a la Array deposito
     * @param producto, este sera el objeto que sera añadido a <code>deposito</code>
     */
    public void addProducto(T producto){
        deposito.add(producto);
    }

    /**
     *Retorna y elimina el objeto que se se encuentra en la posición 0 de <code>deposito</code>,
     * solo si <code>deposito</code> no está vacío.
     * @return el primer objeto de <code>deposito</code> o null en caso de que este vacio.
     */
    public T getProducto(){
        if(!deposito.isEmpty()) {
            return deposito.remove(0);
        }else{
            return null;
        }
    }
    public boolean isEmpty(){
        if(deposito.isEmpty()==true){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Retorna el número de objetos que se encuentran en <code>deposito</code>.
     * @return el tamaño del deposito.
     */
    public int size() {
        return deposito.size();
    }
}