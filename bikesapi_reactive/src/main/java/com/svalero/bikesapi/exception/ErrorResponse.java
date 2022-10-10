package com.svalero.bikesapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.svalero.bikesapi.exception.Constants.MANDATORY_FIELD_ERROR_CODE;

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
        return new ErrorResponse(104, "Validation error", errors);
    }
}
