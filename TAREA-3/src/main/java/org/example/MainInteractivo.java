package org.example;

import java.util.Scanner;

/**
 * Clase principal que ejecuta un menú interactivo para simular una máquina expendedora.
 * <p>
 * Los usuarios pueden insertar monedas, seleccionar productos y recibir su compra el vuelto correspondiente.
 */

public class MainInteractivo {

    /**
     * Método principal que ejecuta el ciclo de compra.
     */
    public static void main(String[] args) {

        Expendedor expendedor = new Expendedor(10);
        Scanner sc = new Scanner(System.in);
        boolean seguir = true;


        System.out.println("----Máquina expendedora----");

        while (seguir) {
            Moneda monedaComprador = null;

            //Menú de selección de moneda
            System.out.println("Ingrese su moneda: ");
            System.out.println("1. 100");
            System.out.println("2. 500");
            System.out.println("3. 1000");
            System.out.println("4. No ingresar moneda.");
            System.out.println("5. Salir.");
            int seleccionMenu = sc.nextInt();

            switch (seleccionMenu) {
                case 1:
                    monedaComprador = new Moneda100();
                    break;
                case 2:
                    monedaComprador = new Moneda500();
                    break;
                case 3:
                    monedaComprador = new Moneda1000();
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    seguir = false;
                    return;
            }

            // Menú de selección de producto
            System.out.println("Escoja el tipo de producto:");
            System.out.println("1. Bebidas");
            System.out.println("2. Dulces");
            System.out.println("3. Salir");
            seleccionMenu = sc.nextInt();
            int seleccionProducto = 0;

            switch (seleccionMenu) {
                case 1:
                    System.out.println("Escoja el tipo de bebida:");
                    System.out.println("1. Coca Cola -- $" + PrecioProductos.COCA.getPrecio());
                    System.out.println("2. Sprite -- $" + PrecioProductos.SPRITE.getPrecio());
                    System.out.println("3. Fanta -- $" + PrecioProductos.FANTA.getPrecio());
                    System.out.println("9. Regresar");
                    seleccionProducto = sc.nextInt();
                    break;
                case 2:
                    System.out.println("Escoja el tipo de dulce:");
                    System.out.println("5. Snicker -- $" + PrecioProductos.SNIKERS.getPrecio());
                    System.out.println("6. Super 8 -- $" + PrecioProductos.SUPER8.getPrecio());
                    System.out.println("9. Regresar");
                    seleccionProducto = sc.nextInt();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    return;
            }

            if (seleccionProducto == 9) {
                System.out.println("Regresando al menú principal...");
                continue;
            }

            // Se realiza la compra
            Comprador comprador = null;
            try {
                comprador = new Comprador(monedaComprador, seleccionProducto, expendedor);
                System.out.println("Compra exitosa!");
                System.out.println("Su vuelto es: " + comprador.cuantoVuelto());
                System.out.println("Usted compró: " + comprador.queCompraste() + "\n");
            } catch (PagoInsuficienteExcepcion | NoHayProductoExcepcion e) {
                System.out.println(e.getMessage());
                System.out.println("Su vuelto es: " + expendedor.getVuelto().getValor() + "\n");
            } catch (PagoIncorrectoExcepcion e) {
                System.out.println(e.getMessage());
            }


        }
    }
}