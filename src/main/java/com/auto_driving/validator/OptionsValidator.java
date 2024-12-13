package com.auto_driving.validator;

import com.auto_driving.exception.InvalidOptionException;

import java.util.ArrayList;
import java.util.List;

public class OptionsValidator implements Validator{

    List<String> options = new ArrayList<>();

    public OptionsValidator(List<String> options) {
        this.options = options;
    }

    @Override
    public void validate(String input) throws InvalidOptionException {
        int optionSelected;
        try {
            optionSelected = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Invalid case 1: non-numerical values
            throw new NumberFormatException("Invalid value. Please enter a numerical value.");
        }

        // Invalid case 2: numerical but unavailable options
        if (optionSelected <= 0 || optionSelected > options.size()) {
            throw new InvalidOptionException();
        }
    }
}
