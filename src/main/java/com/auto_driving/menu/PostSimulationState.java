package com.auto_driving.menu;

import java.util.ArrayList;
import java.util.Arrays;

public class PostSimulationState extends OptionState {
    public PostSimulationState() {
        super(new ArrayList<>(Arrays.asList("Start Over", "End")));
    }

    public MenuState getNextState() {
        // change menu state based the option selected
        return switch (getOptionSelected()) {
            case 1 -> new StartState();
            case 2 -> new EndState();
            default -> null;
        };
    }
}
