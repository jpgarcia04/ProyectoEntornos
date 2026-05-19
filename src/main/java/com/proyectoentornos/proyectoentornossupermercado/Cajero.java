/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lorenzo.pr4entornos;

import java.util.ArrayList;

/**
 * Representa un cajero de supermercado encargado de gestionar los productos
 * de un ticket, cobrar las compras realizadas y mostrar el cierre de caja.
 *
 * La clase permite añadir y eliminar productos del ticket actual, calcular
 * el importe total con IVA, imprimir el ticket por pantalla y acumular la
 * facturación diaria.
 *
 * @author loren
 */
public class Cajero {

    /**
     * Nombre del cajero.
     */
    String n;

    /**
     * Número de tickets emitidos durante la jornada.
     */
    int c;

    /**
     * Total facturado durante la jornada, incluyendo IVA.
     */
    double t;

    /**
     * Lista de productos incluidos en el ticket actual.
     */
    ArrayList<Producto> ps;

    /**
     * Crea un nuevo cajero con el nombre indicado.
     *
     * Inicializa el número de tickets emitidos a cero, el total facturado a
     * cero y crea una lista vacía para almacenar los productos del ticket
     * actual.
     *
     * @param n nombre del cajero
     */
    public Cajero(String n) {
        this.n = n;
        this.c = 0;
        this.t = 0;
        this.ps = new ArrayList<>();
    }

    /**
     * Añade un producto al ticket actual.
     *
     * @param p producto que se desea añadir al ticket
     */
    public void ANADIRPRODUCTO(Producto p) {
        ps.add(p);
    }

    /**
     * Elimina un producto del ticket actual.
     *
     * Si el producto existe en la lista, se elimina. Si no existe, la lista
     * permanece sin cambios.
     *
     * @param p producto que se desea eliminar del ticket
     */
    public void eliminarProDUCTO(Producto p) {
        ps.remove(p);
    }

    /**
     * Cobra el ticket actual.
     *
     * Calcula el subtotal de todos los productos, aplica el IVA, muestra el
     * ticket por pantalla, incrementa el contador de tickets emitidos, acumula
     * el total facturado y finalmente vacía la lista de productos del ticket.
     */
    public void cobrar() {
        double subt = 0;
        for (Producto p : ps) {
            subt = subt + p.calcularImporte();
        }
        double iva = subt * 0.21;
        double tot = subt + iva;

        System.out.println("===== TICKET =====");
        System.out.println("Cajero: " + n);
        for (Producto p : ps) {
            System.out.println(p.getNombre() + " x" + p.getCantidad()
                    + " = " + String.format("%.2f", p.calcularImporte()) + " EUR");
        }
        System.out.println("------------------");
        System.out.println("Subtotal: " + String.format("%.2f", subt) + " EUR");
        System.out.println("IVA (21%): " + String.format("%.2f", iva) + " EUR");
        System.out.println("TOTAL: " + String.format("%.2f", tot) + " EUR");
        System.out.println("==================");

        c = c + 1;
        t = t + tot;
        ps.clear();
    }

    /**
     * Muestra el cierre de caja de la jornada.
     *
     * Calcula el IVA recaudado a partir del total facturado y muestra por
     * pantalla el nombre del cajero, los tickets emitidos, el total facturado
     * y el IVA recaudado.
     */
    public void cierreCaja() {
        double ivaRec = t - (t / (1 + 0.21));

        System.out.println("===== CIERRE DE CAJA =====");
        System.out.println("Cajero: " + n);
        System.out.println("--------------------------");
        System.out.println("Tickets emitidos: " + c);
        System.out.println("Total facturado:  " + String.format("%.2f", t) + " EUR");
        System.out.println("IVA recaudado:    " + String.format("%.2f", ivaRec) + " EUR");
        System.out.println("==========================");
    }

    /**
     * Indica si el ticket actual no contiene productos.
     *
     * @return {@code true} si no hay productos en el ticket; {@code false} en
     * caso contrario
     */
    public boolean ticketVacio() {
        return ps.isEmpty();
    }

    /**
     * Devuelve el número de tickets emitidos por el cajero.
     *
     * @return número de tickets emitidos
     */
    public int getTicketsEmitidos() {
        return c;
    }

    /**
     * Devuelve el total facturado durante la jornada.
     *
     * @return total facturado, incluyendo IVA
     */
    public double getTotalDia() {
        return t;
    }
}