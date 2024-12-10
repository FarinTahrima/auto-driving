package com.auto_driving.exception;

public class InvalidOptionException extends RuntimeException {
    public InvalidOptionException() {
        super("Invalid option. Please either input 1 or 2.");
    }
}
