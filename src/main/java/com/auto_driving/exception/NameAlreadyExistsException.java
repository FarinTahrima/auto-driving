package com.auto_driving.exception;

public class NameAlreadyExistsException extends RuntimeException {
    public NameAlreadyExistsException() {
        super("This name is already taken. Please try another name.");
    }
}