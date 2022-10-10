package com.svalero.bikesapi.exception;

public class RouteNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Route not found";

    public RouteNotFoundException(String message) {
        super(message);
    }

    public RouteNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
