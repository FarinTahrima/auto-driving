package com.auto_driving.menu;

import java.util.ArrayList;
import java.util.Arrays;

public class FieldOptionState extends OptionState {

    public FieldOptionState() {
        super(new ArrayList<>(Arrays.asList("Add a car to field", "Run Simulation")));
    }

    public MenuState getNextState() {
        // change menu state based the option selected
        return switch (getOptionSelected()) {
            case 1 -> new AddCarState();
            case 2 -> new RunSimulationState();
            default -> null;
        };
    }
}
