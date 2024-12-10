package com.auto_driving.exception;

public class PositionOutsideBoundaryException extends RuntimeException {
    public PositionOutsideBoundaryException(int width, int height) {
        super("Your position is outside field boundary.\n" +
                "The max value for x can be " + (width - 1) + ".\n" +
                "The max value for y can be " + (height - 1) + ".\n");
    }
}
