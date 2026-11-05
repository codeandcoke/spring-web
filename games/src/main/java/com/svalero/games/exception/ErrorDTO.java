package com.svalero.games.exception;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorDTO {

    private int errorCode;
    private String errorMessage;
    private Map<String, String> errors;

    public ErrorDTO() {
        errors = new HashMap<>();
    }
}
