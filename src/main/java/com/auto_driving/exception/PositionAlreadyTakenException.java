package com.auto_driving.exception;

public class PositionAlreadyTakenException extends RuntimeException {
    public PositionAlreadyTakenException(int x, int y) {
        super("The position (" + x + "," + y + ") is already taken by another car.");
    }
}