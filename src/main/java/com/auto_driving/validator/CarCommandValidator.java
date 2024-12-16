package com.auto_driving.validator;

import com.auto_driving.exception.*;

public class CarCommandValidator implements InputValidator {

    @Override
    public void validate(String input) throws InvalidCommandException {

        // Invalid case 1: if no valid char found at all, must have at least one F,L or R
        if (!input.toUpperCase().matches("[FLR]\\w*")) {
            throw new InvalidCommandException();
        }
    }

}
