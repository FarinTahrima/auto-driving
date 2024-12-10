package com.auto_driving.menu;

import com.auto_driving.validator.OptionsValidator;

import static com.auto_driving.AutoDrivingConsole.askForInput;
import static com.auto_driving.AutoDrivingConsole.validateInput;

public class FieldOptions implements MenuState {

    boolean isOptionValid = false;
    private int fieldOptionSelected;

    @Override
    public void executeRequest() {
        while (!isOptionValid) {
            String input = askForInput("Please choose from the following options:\n" +
                    "[1] Add a car to field\n" +
                    "[2] Run simulation");
            isOptionValid = validateInput(new OptionsValidator(), input);
            if (isOptionValid) {
                System.out.println("Selected Option is " + input);
            }
        }
    }
}
