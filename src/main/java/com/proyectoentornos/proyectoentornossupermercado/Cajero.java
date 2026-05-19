package com.lorenzo.pr4entornos;

import java.util.ArrayList;

/**
 *
 * @author loren
 */
public class Cajero {

    private static final double IVA = 0.21;

    private String nombreCajero;
    private int ticketsEmitidos;
    private double totalDia;
    private ArrayList<Producto> productos;

    public Cajero(String nombreCajero) {
        this.nombreCajero = nombreCajero;
        this.ticketsEmitidos = 0;
        this.totalDia = 0;
        this.productos = new ArrayList<>();
    }

    public void anadirProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public void cobrar() {
        double subtotal = calcularSubtotal();
        double iva = calcularIva(subtotal);
        double total = calcularTotal(subtotal, iva);

        imprimirTicket(subtotal, iva, total);
        registrarVenta(total);
        productos.clear();
    }

    private double calcularSubtotal() {
        double subtotal = 0;

        for (Producto producto : productos) {
            subtotal = subtotal + producto.calcularImporte();
        }

        return subtotal;
    }

    private double calcularIva(double subtotal) {
        return subtotal * IVA;
    }

    private double calcularTotal(double subtotal, double iva) {
        return subtotal + iva;
    }

    private void imprimirTicket(double subtotal, double iva, double total) {
        System.out.println("===== TICKET =====");
        System.out.println("Cajero: " + nombreCajero);

        for (Producto producto : productos) {
            System.out.println(producto.getNombre() + " x" + producto.getCantidad()
                    + " = " + String.format("%.2f", producto.calcularImporte()) + " EUR");
        }

        System.out.println("------------------");
        System.out.println("Subtotal: " + String.format("%.2f", subtotal) + " EUR");
        System.out.println("IVA (21%): " + String.format("%.2f", iva) + " EUR");
        System.out.println("TOTAL: " + String.format("%.2f", total) + " EUR");
        System.out.println("==================");
    }

    private void registrarVenta(double total) {
        ticketsEmitidos = ticketsEmitidos + 1;
        totalDia = totalDia + total;
    }

    public void cierreCaja() {
        double ivaRecaudado = calcularIvaRecaudado();

        System.out.println("===== CIERRE DE CAJA =====");
        System.out.println("Cajero: " + nombreCajero);
        System.out.println("--------------------------");
        System.out.println("Tickets emitidos: " + ticketsEmitidos);
        System.out.println("Total facturado:  " + String.format("%.2f", totalDia) + " EUR");
        System.out.println("IVA recaudado:    " + String.format("%.2f", ivaRecaudado) + " EUR");
        System.out.println("==========================");
    }

    private double calcularIvaRecaudado() {
        return totalDia - (totalDia / (1 + IVA));
    }

    public boolean ticketVacio() {
        return productos.isEmpty();
    }

    public int getTicketsEmitidos() {
        return ticketsEmitidos;
    }

    public double getTotalDia() {
        return totalDia;
    }
}