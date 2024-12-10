package com.auto_driving.exception;

public class InvalidArgumentsLengthException extends RuntimeException {
  public InvalidArgumentsLengthException(int correctNumberOfArguments) {
    super("Invalid Number of Arguments. Please enter " + correctNumberOfArguments + " values separated by a space.");
  }
}
