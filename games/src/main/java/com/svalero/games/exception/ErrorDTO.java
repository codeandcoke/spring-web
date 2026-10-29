package com.svalero.games.exception;

import lombok.Data;

@Data
public class ErrorDTO {

    private int errorCode;
    private String errorMessage;
}
