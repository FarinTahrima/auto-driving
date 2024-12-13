package com.auto_driving.exception;

public class InvalidOptionException extends RuntimeException {
    public InvalidOptionException() {
        super("Invalid option. Please input a correct option number.");
    }
}
