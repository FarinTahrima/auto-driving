package com.auto_driving.exception;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException() {
        super("Please input numerical values that are more than 0.");
    }
}
