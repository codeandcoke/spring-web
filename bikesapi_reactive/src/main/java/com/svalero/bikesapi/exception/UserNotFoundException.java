package com.svalero.bikesapi.exception;

public class UserNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "User not found";

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
