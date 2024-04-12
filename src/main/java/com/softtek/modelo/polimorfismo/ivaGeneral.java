package com.softtek.modelo.polimorfismo;

public class ivaGeneral implements IImpuesto{
    @Override
    public double calcularImpuesto(Producto p1) {
        return p1.getPrecio() * (1+ 0.21);
    }
}
