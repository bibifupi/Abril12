package com.softtek.modelo.polimorfismo;

public class ivaSuperReducido implements IImpuesto{
    @Override
    public double calcularImpuesto(Producto p1) {
        return p1.getPrecio() * (1+ 0.04);
    }
}
