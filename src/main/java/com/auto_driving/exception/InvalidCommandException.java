package com.auto_driving.exception;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException() {
        super("No valid command found. Please input F, L or R for your commands.");
    }
}