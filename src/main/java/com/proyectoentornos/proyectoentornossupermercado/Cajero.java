package com.lorenzo.pr4entornos;

import java.util.ArrayList;

/**
 * Representa un cajero de supermercado.
 *
 * Permite gestionar los productos de un ticket, cobrar compras, imprimir el
 * ticket por pantalla y mostrar el cierre de caja con el total facturado y el
 * IVA recaudado.
 *
 * @author loren
 */
public class Cajero {

    /**
     * Porcentaje de IVA aplicado a los productos.
     */
    private static final double IVA = 0.21;

    /**
     * Nombre del cajero.
     */
    private String nombreCajero;

    /**
     * Número de tickets emitidos durante la jornada.
     */
    private int ticketsEmitidos;

    /**
     * Total facturado durante la jornada, incluyendo IVA.
     */
    private double totalDia;

    /**
     * Lista de productos del ticket actual.
     */
    private ArrayList<Producto> productos;

    /**
     * Crea un cajero con el nombre indicado.
     *
     * Inicializa el contador de tickets, el total facturado y la lista de
     * productos del ticket actual.
     *
     * @param nombreCajero nombre del cajero
     */
    public Cajero(String nombreCajero) {
        this.nombreCajero = nombreCajero;
        this.ticketsEmitidos = 0;
        this.totalDia = 0;
        this.productos = new ArrayList<>();
    }

    /**
     * Añade un producto al ticket actual.
     *
     * @param producto producto que se desea añadir al ticket
     */
    public void anadirProducto(Producto producto) {
        productos.add(producto);
    }

    /**
     * Método mantenido para que Main.java siga compilando sin modificarlo.
     *
     * @param producto producto que se desea añadir al ticket
     */
    public void ANADIRPRODUCTO(Producto producto) {
        anadirProducto(producto);
    }

    /**
     * Elimina un producto del ticket actual.
     *
     * Si el producto existe en la lista, se elimina. Si no existe, el ticket
     * permanece sin cambios.
     *
     * @param producto producto que se desea eliminar del ticket
     */
    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    /**
     * Método mantenido para que Main.java siga compilando sin modificarlo.
     *
     * @param producto producto que se desea eliminar del ticket
     */
    public void eliminarProDUCTO(Producto producto) {
        eliminarProducto(producto);
    }

    /**
     * Cobra el ticket actual.
     *
     * Calcula el subtotal, calcula el IVA, obtiene el total del ticket, imprime
     * el ticket, registra la venta y vacía la lista de productos.
     */
    public void cobrar() {
        double subtotal = calcularSubtotal();
        double iva = calcularIva(subtotal);
        double totalTicket = calcularTotal(subtotal, iva);

        imprimirTicket(subtotal, iva, totalTicket);
        registrarVenta(totalTicket);
        vaciarTicket();
    }

    /**
     * Calcula el subtotal del ticket actual sumando el importe de todos los
     * productos.
     *
     * @return subtotal del ticket sin IVA
     */
    private double calcularSubtotal() {
        double subtotal = 0;

        for (Producto producto : productos) {
            subtotal = subtotal + producto.calcularImporte();
        }

        return subtotal;
    }

    /**
     * Calcula el IVA correspondiente a una cantidad.
     *
     * @param subtotal cantidad sobre la que se calcula el IVA
     * @return importe del IVA
     */
    private double calcularIva(double subtotal) {
        return subtotal * IVA;
    }

    /**
     * Calcula el total del ticket sumando subtotal e IVA.
     *
     * @param subtotal importe del ticket sin IVA
     * @param iva importe del IVA
     * @return total del ticket con IVA
     */
    private double calcularTotal(double subtotal, double iva) {
        return subtotal + iva;
    }

    /**
     * Imprime por pantalla el ticket actual.
     *
     * Muestra el nombre del cajero, los productos comprados, el subtotal, el IVA
     * y el total del ticket.
     *
     * @param subtotal importe del ticket sin IVA
     * @param iva importe del IVA
     * @param totalTicket importe total del ticket con IVA
     */
    private void imprimirTicket(double subtotal, double iva, double totalTicket) {
        System.out.println("===== TICKET =====");
        System.out.println("Cajero: " + nombreCajero);

        for (Producto producto : productos) {
            System.out.println(producto.getNombre() + " x" + producto.getCantidad()
                    + " = " + String.format("%.2f", producto.calcularImporte()) + " EUR");
        }

        System.out.println("------------------");
        System.out.println("Subtotal: " + String.format("%.2f", subtotal) + " EUR");
        System.out.println("IVA (21%): " + String.format("%.2f", iva) + " EUR");
        System.out.println("TOTAL: " + String.format("%.2f", totalTicket) + " EUR");
        System.out.println("==================");
    }

    /**
     * Registra una venta realizada.
     *
     * Incrementa el número de tickets emitidos y acumula el total facturado en
     * el día.
     *
     * @param totalTicket importe total del ticket cobrado
     */
    private void registrarVenta(double totalTicket) {
        ticketsEmitidos = ticketsEmitidos + 1;
        totalDia = totalDia + totalTicket;
    }

    /**
     * Vacía la lista de productos del ticket actual.
     */
    private void vaciarTicket() {
        productos.clear();
    }

    /**
     * Muestra el cierre de caja.
     *
     * Calcula el IVA recaudado y muestra por pantalla el nombre del cajero, los
     * tickets emitidos, el total facturado y el IVA recaudado.
     */
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

    /**
     * Calcula el IVA recaudado a partir del total facturado del día.
     *
     * @return IVA recaudado durante la jornada
     */
    private double calcularIvaRecaudado() {
        return totalDia - (totalDia / (1 + IVA));
    }

    /**
     * Comprueba si el ticket actual está vacío.
     *
     * @return {@code true} si no hay productos en el ticket; {@code false} si
     * contiene al menos un producto
     */
    public boolean ticketVacio() {
        return productos.isEmpty();
    }

    /**
     * Devuelve el número de tickets emitidos.
     *
     * @return número de tickets emitidos durante la jornada
     */
    public int getTicketsEmitidos() {
        return ticketsEmitidos;
    }

    /**
     * Devuelve el total facturado durante el día.
     *
     * @return total facturado durante la jornada, incluyendo IVA
     */
    public double getTotalDia() {
        return totalDia;
    }
}