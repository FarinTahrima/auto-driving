package com.auto_driving.exception;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
        super("We do not accept empty values.");
    }
}