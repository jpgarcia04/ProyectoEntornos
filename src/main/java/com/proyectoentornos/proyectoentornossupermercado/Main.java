/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.lorenzo.pr4entornos;

/**
 *
 * @author loren
 */
public class Main {

    public static void main(String[] args) {
        Cajero cajero = new Cajero("Lorenzo");

        // Primer cliente
        cajero.anadirProducto(new Producto("Leche", 1.20, 2));
        cajero.anadirProducto(new Producto("Pan", 0.80, 3));
        cajero.anadirProducto(new Producto("Manzanas", 2.50, 1));
        cajero.cobrar();

        // Segundo cliente: añade y se arrepiente de algo
        Producto cereales = new Producto("Cereales", 3.20, 1);
        cajero.anadirProducto(new Producto("Yogur", 0.50, 6));
        cajero.anadirProducto(cereales);
        cajero.eliminarProducto(cereales);
        cajero.cobrar();

        cajero.cierreCaja();
    }
}
