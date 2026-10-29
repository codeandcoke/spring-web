package com.svalero.games.exception;

public class GameExistsAlreadyException extends Exception {

    public GameExistsAlreadyException() {
        super("The game already exists");
    }

    public GameExistsAlreadyException(String message) {
        super(message);
    }
}
