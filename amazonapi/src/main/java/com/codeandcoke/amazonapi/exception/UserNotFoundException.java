package com.codeandcoke.amazonapi.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(long userId) {
        super("User " + userId + " not found");
    }

    public UserNotFoundException() {
        super("User not found");
    }
}
