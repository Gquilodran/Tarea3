package org.example;
import java.util.ArrayList;
import java.awt.*;
import javax.print.event.PrintJobAttributeListener;
import javax.swing.*;
// hacer que esto funcione con orientadoa  objetos
// convetrtir muchas de las cosas de abajo a clases y subclases
public class Dispen extends JFrame {
    public static void CrearVnetan(String titu) {
        Dimension tamano = Toolkit.getDefaultToolkit().getScreenSize();
        double ancho = tamano.getWidth();
        double alto = tamano.getHeight();
        JFrame ventana = new JFrame(titu);
        ventana.setSize(tamano);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);


        //vemos las diferentes partes de la maquina

        ventana.setLayout(new BorderLayout());

        JPanel inventario = new JPanel();
        inventario.setBackground(Color.gray);
        inventario.add(new JLabel("aqui deberia ir una lista con cada productos"));
        inventario.setPreferredSize(new Dimension(1200, (int) alto));
        ventana.add(inventario, BorderLayout.WEST);

        //zonas de interaccio

        JPanel interac = new JPanel();
        interac.setLayout(new GridLayout(3, 1));
        interac.setPreferredSize(new Dimension(400, (int) alto));

        JPanel selector = new JPanel();
        selector.setBackground(Color.black);
        selector.add(new JLabel("aqi los selectores que elijan el enum del codigo"));
        selector.setPreferredSize((new Dimension(400, 100)));
        interac.add(selector);

        JPanel entra = new JPanel();
        entra.setBackground(Color.yellow);
        entra.add(new Label("ver como hacer para que ingresen las monedas"));
        entra.setPreferredSize(new Dimension(400, 100));
        interac.add(entra);

        JPanel deposito = new JPanel();
        deposito.setBackground(Color.CYAN);
        deposito.add(new JLabel("aqui ver como hacer par obtener vuelto y producto"));
        deposito.setPreferredSize(new Dimension(400, 100));
        interac.add(deposito);

        //selector de que producto

        JPanel product = new JPanel();
        product.setLayout(new GridLayout(3, 2));
        product.setPreferredSize(new Dimension(200, 200));


        //INSERTAR imagen en ves del texto

        JPanel foto1 = new JPanel();
        foto1.add(new JLabel("foto1"));
        foto1.setBackground(Color.GREEN);
        product.add(foto1);

        JPanel foto2 = new JPanel();
        foto2.add(new JLabel("foto2"));
        product.add(foto2);

        JPanel foto3 = new JPanel();
        foto3.add(new JLabel("foto3"));
        product.add(foto3);

        JPanel foto4 = new JPanel();
        foto4.add(new JLabel("foto4"));
        product.add(foto4);

        JPanel foto5 = new JPanel();
        foto5.add(new JLabel("foto5"));
        product.add(foto5);


        //obtener objetos
        JPanel extrac = new JPanel();
        extrac.setLayout(new GridLayout(1, 2));

        JPanel vuelto = new JPanel();
        vuelto.add(new JLabel("ver omo al tocar aca obtenemos el vuelto"));
        vuelto.setPreferredSize(new Dimension(150, 200));
        vuelto.setBackground(Color.GREEN);
        extrac.add(vuelto);

        JPanel compra = new JPanel();
        compra.add(new JLabel("al tocar aca otener lo que comprmos y ver que apareza"));
        extrac.add(compra);


        // ver lista de producttos

        ArrayList<String> coca = new ArrayList<String>();
        coca.add(new String("coca1"));
        coca.add(new String("coca2"));
        coca.add(new String("coca3"));
        coca.add(new String("coca4"));




        deposito.add(extrac, BorderLayout.CENTER);

        selector.add(product, BorderLayout.CENTER);

        ventana.add(interac);







    }


}
