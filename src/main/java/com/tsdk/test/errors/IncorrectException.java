package com.tsdk.test.errors;

public class IncorrectException extends RuntimeException {
    public IncorrectException(final String message) {
        super(message);
    }
}

