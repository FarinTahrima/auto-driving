package com.auto_driving.validator;

import com.auto_driving.exception.*;
import com.auto_driving.model.FieldManager;
import com.auto_driving.model.RectangularField;

public class CarNameValidator implements  Validator {
    @Override
    public void validate(String input) throws InvalidNameException, NameAlreadyExistsException {
        String name = input.trim();
        FieldManager fieldManager = RectangularField.getFieldManager();

        // Invalid case 1: empty spaces only
        if (name.isEmpty()) {
            throw new InvalidNameException();
        }

        // Invalid case 2: name is already taken by another car
        boolean doesNameAlreadyExist = fieldManager.getCars()
                .stream()
                .anyMatch(car -> name.equals(car.getName()));
        if (doesNameAlreadyExist) {
            throw new NameAlreadyExistsException();
        }
    }
}
