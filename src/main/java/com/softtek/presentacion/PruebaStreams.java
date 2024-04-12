package com.softtek.presentacion;

import com.softtek.modelo.DetallesOrdenes;
import com.softtek.persistencia.AccesoADatos;

import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PruebaStreams {
    public static void main(String[] args) {

        AccesoADatos ad = new AccesoADatos();

        try {
            //obtenerDetallesMayor30(ad);
            //ordenarPrecioDescendente(ad);
            //obtenerPrecioMinimo(ad);
            //obtenerEstadisticas(ad);
            obtenerCantidadProductos(ad);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void obtenerDetallesMayor30(AccesoADatos ad) throws SQLException, ClassNotFoundException {
        Predicate<DetallesOrdenes> precioMayor30 = x -> x.getUnit_price() > 30;

        ad.mostrarTodos().stream()
                .filter(precioMayor30)
                .forEach(System.out::println);
    }

    public static void ordenarPrecioDescendente(AccesoADatos ad) throws SQLException, ClassNotFoundException {
        List<DetallesOrdenes> pedidosOrdenados = ad.mostrarTodos().stream()
                .sorted(Comparator.comparing(DetallesOrdenes::getUnit_price).reversed())
                .collect(Collectors.toList());

        pedidosOrdenados.stream().forEach(System.out::println);
    }

    public static void obtenerPrecioMinimo(AccesoADatos ad) throws SQLException, ClassNotFoundException {

        List<DetallesOrdenes> ordenes = ad.mostrarTodos();

        Optional<Double> precioMinimo = ordenes.stream()
                .map(DetallesOrdenes::getUnit_price)
                .min(Double::compareTo);

        List<DetallesOrdenes> ordenesPrecioMinimo = ordenes.stream()
                .filter(orden -> orden.getUnit_price() == precioMinimo.orElse(Double.MAX_VALUE))
                .collect(Collectors.toList());

        ordenesPrecioMinimo.forEach(orden -> System.out.println(orden));
    }

    public static void obtenerEstadisticas(AccesoADatos ad) throws SQLException, ClassNotFoundException {

        List<DetallesOrdenes> detallesOrdenes = ad.mostrarTodos();

        DoubleSummaryStatistics estadisticas = detallesOrdenes.stream()
                .mapToDouble(DetallesOrdenes::getQuantity)
                .summaryStatistics();

        System.out.println("Cantidad total: " + estadisticas.getCount());
        System.out.println("Suma total: " + estadisticas.getSum());
        System.out.println("Media: " + estadisticas.getAverage());
        System.out.println("Mínimo: " + estadisticas.getMin());
        System.out.println("Máximo: " + estadisticas.getMax());
    }

    public static void obtenerCantidadProductos(AccesoADatos ad) throws SQLException, ClassNotFoundException {

        List<DetallesOrdenes> detallesOrdenes = ad.mostrarTodos();

        Map<Integer, Integer> sumaCantidadesProducto = detallesOrdenes.stream()
                .collect(Collectors.groupingBy(DetallesOrdenes::getProduct_id, Collectors.summingInt(DetallesOrdenes::getQuantity)));

        System.out.println("Productos y la suma de sus cantidades:");
        sumaCantidadesProducto.forEach((producto, suma) -> System.out.println(producto + ": " + suma));
    }
}