package com.auto_driving.validator;

import com.auto_driving.exception.InvalidOptionException;

public class OptionsValidator implements Validator{

    @Override
    public void validate(String input) throws InvalidOptionException {
        int optionSelected;
        try {
            optionSelected = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid value. Please enter a numerical value.");
        }

        if (optionSelected <= 0 || optionSelected > 2) {
            throw new InvalidOptionException();
        }
    }
}
