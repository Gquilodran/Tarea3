    package org.example.Logica;

    /**
     * Enum con los precios y los identificadores unicos de los distintos productos
     */
    public enum PrecioProductos {
        COCA(1,500),
        SPRITE(2,500),
        FANTA(3,600),
        SNIKERS(4,400),
        SUPER8(5,300);

        private final int precio;
        private final int num;

        /**
         * Constructor que asigna a cada producto su precio.
         * @param precio el valor monetario del producto.
         */
        PrecioProductos(int num, int precio){
            this.num = num;
            this.precio=precio;
        }

        /**
         * Devuelve el precio de cada elemento.
         * @return el precio asociado al producto.
         */
        public int getPrecio(){
            return precio;
        }

        /**
         * Método getter que devuelve el identificador unico de cada producto.
         * @return el número asociado a un producto específico.
         */
        public int getNum(){
            return num;
        }
    }
