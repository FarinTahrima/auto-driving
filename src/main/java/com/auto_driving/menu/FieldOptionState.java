package com.auto_driving.menu;

import com.auto_driving.validator.OptionsValidator;

import static com.auto_driving.AutoDrivingConsole.askForInput;
import static com.auto_driving.AutoDrivingConsole.validateInput;

public class FieldOptionState implements MenuState {

    boolean isOptionValid = false;
    private int fieldOptionSelected;

    @Override
    public void executeRequest() {
        while (!isOptionValid) {
            String input = askForInput("""
                    \nPlease choose from the following options:
                    [1] Add a car to field
                    [2] Run simulation""");
            isOptionValid = validateInput(new OptionsValidator(), input);
            if (isOptionValid) {
                setFieldOptionSelected(Integer.parseInt(input));
            }
        }
    }

    public int getFieldOptionSelected() {
        return fieldOptionSelected;
    }

    public void setFieldOptionSelected(int fieldOptionSelected) {
        this.fieldOptionSelected = fieldOptionSelected;
    }
}
