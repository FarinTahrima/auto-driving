package com.auto_driving.exception;

public class NonPosititveValueException extends RuntimeException {
    public NonPosititveValueException() {
        super("Please input values that are more than 0.");
    }
}
