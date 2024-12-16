package com.auto_driving.menu;

import com.auto_driving.model.RectangularField;
import com.auto_driving.validator.RectangularFieldValidator;

import static com.auto_driving.utils.Utils.getInput;

public class RectangularFieldSetupState implements MenuState {
    private RectangularField field;

    @Override
    public void executeRequest() {
        // set the field dimensions based on user input
        String dimensionStr = getInput(
                "Please enter the width and height of the simulation field in x y format:",
                new RectangularFieldValidator()
        );
        String[] dimensions = dimensionStr.split(" ");
        field = RectangularField.getInstance(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
    }

    @Override
    public MenuState getNextState() {
        return new FieldOptionState();
    }

    public RectangularField getField() {
        return field;
    }

    public void setField(RectangularField field) {
        this.field = field;
    }
}
