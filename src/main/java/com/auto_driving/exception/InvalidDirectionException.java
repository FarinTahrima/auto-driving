package com.auto_driving.exception;

public class InvalidDirectionException extends RuntimeException {
    public InvalidDirectionException() {
        super("Invalid direction. Please input N, E, S or W.");
    }
}
