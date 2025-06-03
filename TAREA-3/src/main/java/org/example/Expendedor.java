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
    private Deposito monVu;
    private int Precio;


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

        for (int i = 0; i < numProductos; i++) {
            Bebida cocacola = new CocaCola();
            coca.addProducto(cocacola);
            Bebida bsprite = new Sprite();
            sprite.addProducto(bsprite);
            Bebida bfanta = new Fanta();
            fanta.addProducto(bfanta);
            Dulce dsniker = new Sniker();
            snikers.addProducto(dsniker);
            Dulce dsuper8 = new Super8();
            super8.addProducto(dsuper8);
        }
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
     * @return el producto comprado si la moneda es válida, si su valor es mayor o igual al del precio,
     * y si hay stock, en caso contrario se lanza una excepción o retorna {@code null}.
     * @throws PagoInsuficienteExcepcion si el valor de la moneda es menor al precio del producto seleccionado.
     * @throws PagoIncorrectoExcepcion si el valor de la moneda es {@code null}.
     * @throws NoHayProductoExcepcion si no queda stock del producto deseado.
     */
    public Producto comprarProducto(Moneda moneda, int que) throws PagoInsuficienteExcepcion, PagoIncorrectoExcepcion, NoHayProductoExcepcion {
        Producto Producto = null;

        if (moneda == null){
            throw new PagoIncorrectoExcepcion();
        }else{
            if(PrecioProductos.COCA.getNum()==que) {   //Cocacola
                Precio = PrecioProductos.COCA.getPrecio();
                if (Precio > moneda.getValor()) {
                    monVu.addProducto(moneda);
                    throw new PagoInsuficienteExcepcion();
                }
                Producto = (Bebida) coca.getProducto();
                if (Producto == null) {
                    monVu.addProducto(moneda);
                    throw new NoHayProductoExcepcion();
                }
            }else if(PrecioProductos.SPRITE.getNum()==que) { //SPRITE
                Precio = PrecioProductos.SPRITE.getPrecio();
                if (Precio > moneda.getValor()) {
                    monVu.addProducto(moneda);
                    throw new PagoInsuficienteExcepcion();
                }
                Producto = (Bebida) sprite.getProducto();
                if (Producto == null) {
                    monVu.addProducto(moneda);
                    throw new NoHayProductoExcepcion();
                }
            }else if(PrecioProductos.FANTA.getNum()==que) { //FANTA
                Precio = PrecioProductos.FANTA.getPrecio();
                if (Precio > moneda.getValor()) {
                    monVu.addProducto(moneda);
                    throw new PagoInsuficienteExcepcion();
                }
                Producto = (Bebida) fanta.getProducto();
                if (Producto == null) {
                    monVu.addProducto(moneda);
                    throw new NoHayProductoExcepcion();
                }
            }else if(PrecioProductos.SNIKERS.getNum()==que) { //SNIKERS
                Precio = PrecioProductos.SNIKERS.getPrecio();
                if (Precio > moneda.getValor()) {
                    monVu.addProducto(moneda);
                    throw new PagoInsuficienteExcepcion();
                }
                Producto = (Dulce) snikers.getProducto();
                if (Producto == null) {
                    monVu.addProducto(moneda);
                    throw new NoHayProductoExcepcion();
                }
            }else if(PrecioProductos.SUPER8.getNum()==que) { //SUPER8
                Precio = PrecioProductos.SUPER8.getPrecio();
                if (Precio > moneda.getValor()) {
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
                return null;
            }
        }


        int vuelto = moneda.getValor() - Precio;
        while (vuelto != 100) {
            if(vuelto>=1000) {
                monVu.addProducto(new Moneda1000());
                vuelto -= 1000;
            }else if(vuelto>=500){
                monVu.addProducto(new Moneda500());
                vuelto -= 500;
            }else{
                monVu.addProducto(new Moneda100());
                vuelto -= 100;
            }
        }
        return Producto;
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
}
