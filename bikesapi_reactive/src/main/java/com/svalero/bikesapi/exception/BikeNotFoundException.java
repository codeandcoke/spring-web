package com.svalero.bikesapi.exception;

public class BikeNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Bike not found";

    public BikeNotFoundException(String message) {
        super(message);
    }

    public BikeNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
