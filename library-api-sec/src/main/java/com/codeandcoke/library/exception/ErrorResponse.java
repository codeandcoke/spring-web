package com.codeandcoke.library.exception;

public record ErrorResponse (int internalErrorCode, String message) {}
