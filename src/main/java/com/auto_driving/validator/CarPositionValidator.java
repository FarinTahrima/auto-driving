package com.auto_driving.validator;

import com.auto_driving.exception.InvalidArgumentsLengthException;
import com.auto_driving.exception.InvalidDirectionException;
import com.auto_driving.exception.InvalidValueException;
import com.auto_driving.exception.PositionOutsideBoundaryException;
import com.auto_driving.model.RectangularField;

public class CarPositionValidator implements Validator {

    @Override
    public void validate(String input) throws InvalidArgumentsLengthException, InvalidValueException, InvalidDirectionException {
        int x, y;
        char direction;
        String[] position = input.split(" ");

        // Invalid case 1: invalid no. of arguments
        if (position.length != 3) {
            throw new InvalidArgumentsLengthException(3);
        }

        // Invalid case 2: x or y is not a positive numerical value
        if (!position[0].matches("^[1-9]\\d*$") || !position[1].matches("^[1-9]\\d*$")) {
            throw new InvalidValueException();
        } else {
            x = Integer.parseInt(position[0]);
            y = Integer.parseInt(position[1]);
        }

        // Invalid case 4: x is more than the field width or y is more than the field height
        if (x >= RectangularField.getWidth() || y >= RectangularField.getHeight()) {
            throw new PositionOutsideBoundaryException(RectangularField.getWidth(), RectangularField.getHeight());
        }

        // Invalid case 4: direction is not N, S, E or W
        // if more than one char for direction, then only check with first char
        if (!position[2].toUpperCase().matches("^[NESW]\\w*$")) {
            throw new InvalidDirectionException();
        }
    }
}