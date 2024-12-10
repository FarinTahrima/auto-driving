package com.auto_driving.menu;

import com.auto_driving.model.RectangularField;
import com.auto_driving.validator.RectangularFieldValidator;

import static com.auto_driving.AutoDrivingConsole.askForInput;
import static com.auto_driving.AutoDrivingConsole.validateInput;

public class RectangularFieldSetupState implements MenuState {
    boolean isInputValid = false;
    private RectangularField field;

    @Override
    public void executeRequest() {
        while (!isInputValid) {
            String input = askForInput("Please enter the width and height of the simulation field in x y format:");
            isInputValid = validateInput(new RectangularFieldValidator(), input);
            if (isInputValid) {
                String[] dimensions = input.split(" ");
                field = RectangularField.getInstance(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
            }
        }
    }

    public RectangularField getField() {
        return field;
    }

    public void setField(RectangularField field) {
        this.field = field;
    }
}
