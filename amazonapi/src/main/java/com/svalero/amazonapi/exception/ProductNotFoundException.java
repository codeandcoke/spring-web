package com.svalero.amazonapi.exception;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(long productId) {
        super("Product " + productId + " not found");
    }

    public ProductNotFoundException() {
        super("Product not found");
    }
}
