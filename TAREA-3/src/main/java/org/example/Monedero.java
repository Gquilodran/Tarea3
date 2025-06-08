package org.example;

import java.util.ArrayList;

public class Monedero extends Deposito {
    private ArrayList<Moneda> monederos;

    Monedero(int dinero) {
        monederos = new ArrayList<>();
        while (dinero > 0) {
            if (dinero >= 1000) {
                dinero = dinero - 1000;
                monederos.add(new Moneda1000());
            } else if (dinero >= 500) {
                dinero = dinero - 500;
                monederos.add(new Moneda500());
            } else if (dinero >= 100) {
                dinero = dinero - 100;
                monederos.add(new Moneda100());

            }
        }
    }

    public Moneda getMoneda(int numero) {
        for (int i = 0; i < monederos.size(); i++) {
            Moneda moneda = monederos.get(i);
            if (numero == 1 && moneda instanceof Moneda100) {
                return monederos.remove(i);
            } else if (numero == 2 && moneda instanceof Moneda500) {
                return monederos.remove(i);
            } else if (numero == 3 && moneda instanceof Moneda1000) {
                return monederos.remove(i);
            }
        }
        return null;//no ahy moneda

    }
    public void  setMoneda(Moneda moneda) {
        monederos.add(moneda);
    }
}