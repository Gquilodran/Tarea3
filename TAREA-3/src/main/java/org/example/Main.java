package org.example;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


/**
 * Clase Main, donde se ejecutara y provara el funcionamiento de las distintas clases.
 */
public class Main {
    @ParameterizedTest
    @CsvSource({
            "1, 500, CocaCola",
            "2, 500, Sprite",
            "3, 400, Fanta",
            "5, 600, Snicker",
            "6, 700, Super8",
    })

    public void testCompraExitosa(int idProducto, int vuelto, String sabor) throws NoHayProductoExcepcion, PagoInsuficienteExcepcion, PagoIncorrectoExcepcion {
        Expendedor exp = new Expendedor(1);
        Moneda m1000 = new Moneda1000();
        Comprador comprador = new Comprador(m1000, idProducto, exp);

        assertEquals(sabor, comprador.queCompraste());
        assertEquals(vuelto, comprador.cuantoVuelto());
    }

    /**
     * Test para comprobar que sucede cuando no queda stock del producto deseado.
     * Se espera que lance una {@link NoHayProductoExcepcion} cuando no quede stock de un producto.
     *
     * @throws NoHayProductoExcepcion si no queda stock del producto deseado.
     * @throws PagoInsuficienteExcepcion si el valor de la moneda es menor al precio del producto seleccionado.
     * @throws PagoIncorrectoExcepcion si el valor de la moneda es {@code null}.
     */
    @Test
    public void testProductoAgotado() throws NoHayProductoExcepcion, PagoInsuficienteExcepcion, PagoIncorrectoExcepcion {
        Expendedor exp = new Expendedor(0);
        Moneda m1000 = new Moneda1000();

        try {
            new Comprador(m1000, 1, exp);
        } catch (NoHayProductoExcepcion e) {
            assertEquals("El producto se encuentra agotado", e.getMessage());
        }
    }

    /**
     * Test para verificar el comportamiento de las clases en caso de que el pago sea insuficiente.
     * Se espera que lance una {@link PagoInsuficienteExcepcion} cuando el valor de la moneda sea menor al precio del producto.
     *
     * @throws NoHayProductoExcepcion si no queda stock del producto deseado.
     * @throws PagoInsuficienteExcepcion si el valor de la moneda es menor al precio del producto seleccionado.
     * @throws PagoIncorrectoExcepcion si el valor de la moneda es {@code null}.
     */
    @Test
    public void testPagoInsuficiente() throws NoHayProductoExcepcion, PagoInsuficienteExcepcion, PagoIncorrectoExcepcion {
        Expendedor exp = new Expendedor(1);
        Moneda m100 = new Moneda100();

        try {
            new Comprador(m100, 1, exp);
        } catch (PagoInsuficienteExcepcion e) {
            assertEquals("Pago insuficiente", e.getMessage());
        }
    }

    /**
     * Test para verificar el comportamiento de las clases en caso de que la moneda ingresada a {@link Expendedor} no sea válida.
     * Se espera que lance una {@link PagoIncorrectoExcepcion} cuando el valor de la moneda sea {@code null}.
     * @throws NoHayProductoExcepcion si no queda stock del producto deseado.
     * @throws PagoInsuficienteExcepcion si el valor de la moneda es menor al precio del producto seleccionado.
     * @throws PagoIncorrectoExcepcion si el valor de la moneda es {@code null}.
     */
    @Test
    public void testPagoIncorrecto() throws NoHayProductoExcepcion, PagoInsuficienteExcepcion, PagoIncorrectoExcepcion {
        Expendedor exp = new Expendedor(1);
        Moneda m = null;

        try {
            new Comprador(m, 1, exp);
        } catch (PagoIncorrectoExcepcion e) {
            assertEquals("Debe ingresar una moneda valida", e.getMessage());
        }
    }

    /**
     * Test que verifica el funcionamiento del método {@code sort} ordenando una lista de monedas,
     * de menor a mayor valor, utilizando la implementacion de {@link Comparable} en {@link Moneda
     */
    @Test
    public void testMonedas() {
        ArrayList<Moneda> monederoV = new ArrayList<Moneda>();
        for(int i=0; i<3; i++){
            monederoV.add(new Moneda100());
            monederoV.add(new Moneda500());
            monederoV.add(new Moneda1000());
        }
        monederoV.sort(null);
        int count = monederoV.size();
        for(int i=0; i< count - 1; i++){
            assertTrue(monederoV.get(i).getValor() <= monederoV.get(i+1).getValor());
        }
    }
}
