package com.auto_driving.validator;

import com.auto_driving.exception.InvalidArgumentsLengthException;
import com.auto_driving.exception.InvalidValueException;

public class RectangularFieldValidator implements InputValidator {
    @Override
    public void validate(String input) throws InvalidArgumentsLengthException, InvalidValueException {
        String[] dimensions = input.split(" ");

        // Invalid Case 1: Incorrect no. of arguments
        if (dimensions.length != 2) {
            throw new InvalidArgumentsLengthException(2);
        }

        if (!dimensions[0].matches("^[1-9]\\d*$") || !dimensions[1].matches("^[1-9]\\d*$")) {
            throw new InvalidValueException(1);
        }
    }
}
