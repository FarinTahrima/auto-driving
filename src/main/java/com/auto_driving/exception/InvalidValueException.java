package com.auto_driving.exception;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(int minValue) {
        super("Please input numerical values that are equal or more than " + minValue + ".");
    }
}
