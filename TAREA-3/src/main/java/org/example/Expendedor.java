package org.example;

/**
 * Clase que representa una expendedora, la cual entrega cinco tipos distintos de productos.
 */
public class Expendedor {
    private Deposito coca;
    private Deposito sprite;
    private Deposito fanta;
    private Deposito snikers;
    private Deposito super8;
    private Deposito<Moneda> monVu;
    private Deposito<Moneda> monHis;
    private int precio;
    private Producto productoComprado;
    private int vuelto;

    /**
     * Constructor que crea una instancia de {@link Expendedor}.
     * Inicializando cinco instancias de {@link Deposito}, para cada uno de los productos,
     * y uno extra para guardar las monedas del vuelto.
     *
     * @param numProductos la cantidad de productos que habra en cada {@link Deposito}.
     * Se agrega esa cantidad de elementos a cada depósito correspondiente.
     */
    public Expendedor(int numProductos) {
        coca = new Deposito<Bebida>();
        sprite = new Deposito<Bebida>();
        fanta = new Deposito<Bebida>();
        snikers = new Deposito<Dulce>();
        super8 = new Deposito<Dulce>();
        monVu = new Deposito<Moneda>();
        monHis = new Deposito<Moneda>();

        rellenarDeposito(PrecioProductos.COCA, numProductos);
        rellenarDeposito(PrecioProductos.SPRITE, numProductos);
        rellenarDeposito(PrecioProductos.FANTA, numProductos);
        rellenarDeposito(PrecioProductos.SNIKERS, numProductos);
        rellenarDeposito(PrecioProductos.SUPER8, numProductos);
    }

    /**
     * Método que permite comprar un producto de {@link Expendedor}.
     *
     * @param moneda la moneda utilizada para realizar la compra, solo si es válida y su valor es suficiente.
     * @param que el número que representara el producto solicitado:
     *            <ul>
     *  *              <li>1 - CocaCola</li>
     *  *              <li>2 - Sprite</li>
     *  *              <li>3 - Fanta</li>
     *  *              <li>5 - Snikers</li>
     *  *              <li>6 - Super8</li>
     *  *            </ul>
     * CAMBIAR ESTO (RETURN VOID) @return el producto comprado si la moneda es válida, si su valor es mayor o igual al del precio,
     * y si hay stock, en caso contrario se lanza una excepción o retorna {@code null}.
     * @throws PagoInsuficienteExcepcion si el valor de la moneda es menor al precio del producto seleccionado.
     * @throws PagoIncorrectoExcepcion si el valor de la moneda es {@code null}.
     * @throws NoHayProductoExcepcion si no queda stock del producto deseado.
     */
    public void comprarProducto(Moneda moneda, int que) throws PagoInsuficienteExcepcion, PagoIncorrectoExcepcion, NoHayProductoExcepcion {
        Producto Producto = null;

        if (moneda == null){
            throw new PagoIncorrectoExcepcion();
        }else{
            if(PrecioProductos.COCA.getNum()==que) {   //Cocacola
                precio = PrecioProductos.COCA.getPrecio();
                if (precio > moneda.getValor()) {
                    monVu.addProducto(moneda);
                    throw new PagoInsuficienteExcepcion();
                }
                Producto = (Bebida) coca.getProducto();
                if (Producto == null) {
                    monVu.addProducto(moneda);
                    throw new NoHayProductoExcepcion();
                }
            }else if(PrecioProductos.SPRITE.getNum()==que) { //SPRITE
                precio = PrecioProductos.SPRITE.getPrecio();
                if (precio > moneda.getValor()) {
                    monVu.addProducto(moneda);
                    throw new PagoInsuficienteExcepcion();
                }
                Producto = (Bebida) sprite.getProducto();
                if (Producto == null) {
                    monVu.addProducto(moneda);
                    throw new NoHayProductoExcepcion();
                }
            }else if(PrecioProductos.FANTA.getNum()==que) { //FANTA
                precio = PrecioProductos.FANTA.getPrecio();
                if (precio > moneda.getValor()) {
                    monVu.addProducto(moneda);
                    throw new PagoInsuficienteExcepcion();
                }
                Producto = (Bebida) fanta.getProducto();
                if (Producto == null) {
                    monVu.addProducto(moneda);
                    throw new NoHayProductoExcepcion();
                }
            }else if(PrecioProductos.SNIKERS.getNum()==que) { //SNIKERS
                precio = PrecioProductos.SNIKERS.getPrecio();
                if (precio > moneda.getValor()) {
                    monVu.addProducto(moneda);
                    throw new PagoInsuficienteExcepcion();
                }
                Producto = (Dulce) snikers.getProducto();
                if (Producto == null) {
                    monVu.addProducto(moneda);
                    throw new NoHayProductoExcepcion();
                }
            }else if(PrecioProductos.SUPER8.getNum()==que) { //SUPER8
                precio = PrecioProductos.SUPER8.getPrecio();
                if (precio > moneda.getValor()) {
                    monVu.addProducto(moneda);
                    throw new PagoInsuficienteExcepcion();
                }
                Producto = (Dulce) super8.getProducto();
                if (Producto == null) {
                    monVu.addProducto(moneda);
                    throw new NoHayProductoExcepcion();
                }
            } else {
                monVu.addProducto(moneda); // se devuelve la misma moneda si se solicita producto que no existe.
            }
        }


        //LOGICA DEL VUELTO

        vuelto = moneda.getValor() - precio;
        monHis.addProducto(moneda);
        guardarVuelto(vuelto); // Guarda el vuelto en el deposito monVu

        // Guarda el producto comprado
        productoComprado = Producto;

    }


    /**
     * Método getter que devuelve una moneda del deposito monVu.
     *
     * @return una instancia de {@link Moneda} que se encuentra en el depósito monVu,
     * o en caso de que este vacío retornara {@code null}.
     */
    public Moneda getVuelto() {
        return (Moneda) monVu.getProducto();
    }

    public void guardarVuelto(int vueltoTotal) {

        // Agrega las monedas al dep del vuelto
        while (vueltoTotal >= 1000) {
            monVu.addProducto(new Moneda1000());
            vueltoTotal -= 1000;
        }
        while (vueltoTotal >= 500) {
            monVu.addProducto(new Moneda500());
            vueltoTotal -= 500;
        }
        while (vueltoTotal >= 100) {
            monVu.addProducto(new Moneda100());
            vueltoTotal -= 100;
        }

    }

    public Producto getProductoDep() { //SACA EL PRODUCTO GUARDADO EN EL DEPOSITO
        Producto p = this.productoComprado;
        this.productoComprado = null; //Vacía el deposito del producto
        return p;
    }

    public Producto getProductoComprado() { // DEVUELVE EL PRODUCTO COMPRADO (SIN SACARLO DEL DEPOSITO)
        return productoComprado;
    }

    public int getUltimoVuelto(){
        return vuelto;
    }
    public Moneda VueltoMon(){
        return monVu.getProducto();
    }

    /**
     * Devuelve la cantidad de productos disponibles según el tipo.
     * @param tipoProducto el tipo de producto del enum PrecioProductos
     * @return la cantidad disponible
     */
    public int getCantidadDisponible(PrecioProductos tipoProducto) {
        switch (tipoProducto) {
            case COCA:
                return coca.size();
            case SPRITE:
                return sprite.size();
            case FANTA:
                return fanta.size();
            case SNIKERS:
                return snikers.size();
            case SUPER8:
                return super8.size();
            default:
                return 0;
        }
    }

    public int rellenarDeposito(PrecioProductos tipoProducto, int cantidad) {
        switch (tipoProducto) {
            case COCA:
                for (int i = 0; i < cantidad; i++) {
                    coca.addProducto(new CocaCola());
                }
                return coca.size();
            case SPRITE:
                for (int i = 0; i < cantidad; i++) {
                    sprite.addProducto(new Sprite());
                }
                return sprite.size();
            case FANTA:
                for (int i = 0; i < cantidad; i++) {
                    fanta.addProducto(new Fanta());
                }
                return fanta.size();
            case SNIKERS:
                for (int i = 0; i < cantidad; i++) {
                    snikers.addProducto(new Sniker());
                }
                return snikers.size();
            case SUPER8:
                for (int i = 0; i < cantidad; i++) {
                    super8.addProducto(new Super8());
                }
                return super8.size();
            default:
                return 0;
        }
    }

}