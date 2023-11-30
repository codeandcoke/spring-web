package com.svalero.helloapi.exception;

import com.svalero.helloapi.repository.ProductRepository;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(long id) {
        super("The product " + id + " doesn't exist");
    }
}
