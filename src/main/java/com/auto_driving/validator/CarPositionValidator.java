package com.auto_driving.validator;

import com.auto_driving.exception.*;
import com.auto_driving.model.FieldManager;
import com.auto_driving.model.RectangularField;

public class CarPositionValidator implements Validator {

    @Override
    public void validate(String input) throws InvalidArgumentsLengthException, InvalidValueException, InvalidDirectionException {
        int x, y;
        String[] position = input.split(" ");
        FieldManager fieldManager = RectangularField.getFieldManager();

        // Invalid case 1: invalid no. of arguments
        if (position.length != 3) {
            throw new InvalidArgumentsLengthException(3);
        }

        // Invalid case 2: x or y is not a positive numerical value
        if (!position[0].matches("^[0-9]\\d*$") || !position[1].matches("^[0-9]\\d*$")) {
            throw new InvalidValueException();
        } else {
            x = Integer.parseInt(position[0]);
            y = Integer.parseInt(position[1]);
        }

        // Invalid case 4: x is more than the field width or y is more than the field height
        if (x >= RectangularField.getWidth() || y >= RectangularField.getHeight()) {
            throw new PositionOutsideBoundaryException(RectangularField.getWidth(), RectangularField.getHeight());
        }

        // Invalid case 5: x and y position is already taken by another car
        boolean isPositionAlreadyTaken = fieldManager.getCars()
                .stream()
                .anyMatch(car -> x == car.getPosition().getX()
                    && y == car.getPosition().getY());
        if (isPositionAlreadyTaken) {
            throw new PositionAlreadyTakenException(x, y);
        }

        // Invalid case 6: direction is not N, S, E or W
        // if more than one char for direction, then only check with first char
        if (!position[2].toUpperCase().matches("^[NESW]\\w*$")) {
            throw new InvalidDirectionException();
        }
    }
}
