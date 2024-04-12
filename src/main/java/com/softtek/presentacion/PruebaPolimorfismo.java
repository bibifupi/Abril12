package com.softtek.presentacion;

import com.softtek.modelo.polimorfismo.Factura;
import com.softtek.modelo.polimorfismo.Producto;
import com.softtek.modelo.polimorfismo.ivaGeneral;
import com.softtek.modelo.polimorfismo.ivaSuperReducido;

import java.util.ArrayList;
import java.util.List;

public class PruebaPolimorfismo {
    public static void main(String[] args) {
        List<Producto> lista = new ArrayList<>();
        lista.add(new Producto("Lápiz", 0.5));
        lista.add(new Producto("Bolígrafo", 1.5));
        lista.add(new Producto("Carpeta", 4.5));
        lista.add(new Producto("Ceras de colores", 5));
        lista.add(new Producto("Escritorio", 300));
        ivaGeneral iva21 = new ivaGeneral();
        ivaSuperReducido iva4 = new ivaSuperReducido();
        Factura facturaReducida = new Factura(iva4, lista);
        Factura facturaNormal = new Factura(iva21, lista);

        System.out.println("Factura iva reducido:\n" + facturaReducida.calcularTotalFactura());
        System.out.println("Factura iva normal:\n" + facturaNormal.calcularTotalFactura());
    }
}
