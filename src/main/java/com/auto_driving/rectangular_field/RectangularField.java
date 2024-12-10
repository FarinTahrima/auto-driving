package com.auto_driving.rectangular_field;

import com.auto_driving.menu.MenuState;
import com.auto_driving.validator.RectangularFieldValidator;

import static com.auto_driving.AutoDrivingConsole.askForInput;
import static com.auto_driving.AutoDrivingConsole.validateInput;

public class RectangularField implements MenuState {
    boolean isInputValid = false;

    private long width;
    private long height;

    @Override
    public void executeRequest() {
        while (!isInputValid) {
            String input = askForInput("Please enter the width and height of the simulation field in x y format:");
            isInputValid = validateInput(new RectangularFieldValidator(), input);
            if (isInputValid) {
                String[] dimensions = input.split(" ");
                generateRectangleField(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
            }
        }
    }

    private void generateRectangleField (long width, long height)  {
        this.width = width;
        this.height = height;
        System.out.printf("You have created a field of %d x %d.\n", width, height);
    }

}
