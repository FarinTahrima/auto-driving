package com.auto_driving.validator;

import com.auto_driving.exception.InvalidArgumentsLengthException;
import com.auto_driving.exception.NonPosititveValueException;

public class RectangularFieldValidator implements Validator {
    @Override
    public void validate(String input) throws InvalidArgumentsLengthException, NonPosititveValueException {
        int width, height;
        String[] dimensions = input.split(" ");

        // Invalid Case 1: Incorrect no. of arguments
        if (dimensions.length != 2) {
            throw new InvalidArgumentsLengthException(2);
        }

        try {
            width =  Integer.parseInt(dimensions[0]);
            height = Integer.parseInt(dimensions[1]);
        }

        // Invalid case 2: Non-numerical values
        catch (NumberFormatException e) {
            // Handle non-numeric input
            throw new NumberFormatException("Invalid values. Please enter numerical values.");
        }

        // invalid case 3: Non-positive values
        if (width <= 0 || height <= 0) {
            throw new NonPosititveValueException();
        }
    }
}
