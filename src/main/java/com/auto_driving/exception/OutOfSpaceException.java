package com.auto_driving.exception;

public class OutOfSpaceException extends RuntimeException {
    public OutOfSpaceException(int maxCapacity) {
      super("The field is fully parked. The max capacity is " + maxCapacity + ".");
    }
}
