package org.example.Logica;

/**
 * Clase que representa a un comprador que tiene un monedero
 */
public class Comprador {
    /**
     * Monedero del comprador
     */
    private Deposito<Moneda> monedero;

    /**
     * Variable que almacena el sabor del producto comprado
     */
    private String sabor;

    /**
     * Variable que almacena el vuelto recibido
     */
    private int vuelto = 0;

    /**
     * Constructor de la clase Comprador
     *
     * @param cantidadMonedas100  Cantidad inicial de monedas de 100
     * @param cantidadMonedas500  Cantidad inicial de monedas de 500
     * @param cantidadMonedas1000 Cantidad inicial de monedas de 1000
     */
    public Comprador(int cantidadMonedas100, int cantidadMonedas500, int cantidadMonedas1000) {
        this.monedero = new Deposito<>();

        // Inicializar el monedero con las cantidades especificadas
        for (int i = 0; i < cantidadMonedas100; i++) {
            this.monedero.addProducto(new Moneda100());
        }

        for (int i = 0; i < cantidadMonedas500; i++) {
            this.monedero.addProducto(new Moneda500());
        }

        for (int i = 0; i < cantidadMonedas1000; i++) {
            this.monedero.addProducto(new Moneda1000());
        }
    }

    /**
     * Obtiene una moneda del monedero según el tipo solicitado
     *
     * @param tipoMoneda tipo de moneda (1:100, 2:500, 3:1000)
     * @return la moneda seleccionada o null si no hay disponible
     */
    public Moneda sacarMoneda(int tipoMoneda) {
        // Crear un depósito temporal para guardar las monedas que no coinciden
        Deposito<Moneda> depositoTemp = new Deposito<>();
        Moneda monedaEncontrada = null;

        // Buscar la moneda del tipo solicitado
        while (true) {
            Moneda moneda = monedero.getProducto();
            if (moneda == null) {
                // No hay más monedas en el depósito
                break;
            }

            boolean esMonedaCorrecta =
                    (tipoMoneda == 1 && moneda.getValor() == 100) ||
                            (tipoMoneda == 2 && moneda.getValor() == 500) ||
                            (tipoMoneda == 3 && moneda.getValor() == 1000);

            if (esMonedaCorrecta && monedaEncontrada == null) {
                // Encontramos una moneda del tipo correcto
                monedaEncontrada = moneda;
            } else {
                // Esta moneda no es del tipo correcto o ya encontramos una
                depositoTemp.addProducto(moneda);
            }
        }

        // Devolver todas las monedas que no coinciden al depósito original
        while (true) {
            Moneda moneda = depositoTemp.getProducto();
            if (moneda == null) {
                break;
            }
            monedero.addProducto(moneda);
        }

        return monedaEncontrada;
    }

    /**
     * Registra la compra realizada
     *
     * @param producto       Producto comprado
     * @param vueltoRecibido Valor del vuelto recibido
     */
    public void registrarCompra(Producto producto, int vueltoRecibido) {
        if (producto != null) {
            this.sabor = producto.getSabor();
        }
        this.vuelto = vueltoRecibido;
    }

    /**
     * Añade monedas al monedero como vuelto
     *
     * @param moneda La moneda a añadir al monedero
     */
    public void agregarMoneda(Moneda moneda) {
        if (moneda != null) {
            monedero.addProducto(moneda);
        }
    }

    /**
     * Devuelve el monto total del vuelto recibido
     *
     * @return Valor total del vuelto
     */
    public int cuantoVuelto() {
        return this.vuelto;
    }

    /**
     * Devuelve el sabor del producto adquirido
     *
     * @return Sabor del producto comprado, o null si no se compró ninguno
     */
    public String queCompraste() {
        return this.sabor;
    }

    /**
     * Retorna la cantidad de monedas en el monedero
     *
     * @return número de monedas disponibles
     */
    public int cantidadMonedas() {
        return monedero.size();
    }

    /**
     * Cuenta la cantidad de monedas de un tipo específico en el monedero
     *
     * @param tipoMoneda tipo de moneda a contar (1:100, 2:500, 3:1000)
     * @return cantidad de monedas del tipo especificado
     */
    public int contarMonedas(int tipoMoneda) {
        // Guardar las monedas temporalmente mientras contamos
        Deposito<Moneda> depositoTemp = new Deposito<>();
        int contador = 0;

        // Contar monedas del tipo especificado
        while (true) {
            Moneda moneda = monedero.getProducto();
            if (moneda == null) {
                break;  // No hay más monedas
            }

            // Verificar si es del tipo buscado
            boolean esMonedaCorrecta =
                    (tipoMoneda == 1 && moneda.getValor() == 100) ||
                            (tipoMoneda == 2 && moneda.getValor() == 500) ||
                            (tipoMoneda == 3 && moneda.getValor() == 1000);

            if (esMonedaCorrecta) {
                contador++;
            }

            // Guardar la moneda en depósito temporal
            depositoTemp.addProducto(moneda);
        }

        // Devolver todas las monedas al depósito original
        while (true) {
            Moneda moneda = depositoTemp.getProducto();
            if (moneda == null) {
                break;
            }
            monedero.addProducto(moneda);
        }
        return contador;
    }
}