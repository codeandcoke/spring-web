package com.sanvalero.myshop.exception;

/**
 * Excepción de producto no encontrado
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(long id) {
        super("Product not found: " + id);
    }
}
