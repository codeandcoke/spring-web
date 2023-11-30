package com.svalero.helloapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorResponse {

    private int code;
    private String message;
    private Map<String, String> errors;

    private ErrorResponse(int errorCode, String errorMessage) {
        code = errorCode;
        message = errorMessage;
        errors = new HashMap<>();
    }

    private ErrorResponse(int code, String message, Map<String, String> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public static ErrorResponse generalError(int code, String message) {
        return new ErrorResponse(code, message);
    }

    public static ErrorResponse validationError(Map<String, String> errors) {
        return new ErrorResponse(400, "Validation error", errors);
    }
}
