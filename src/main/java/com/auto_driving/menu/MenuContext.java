package com.auto_driving.menu;

import com.auto_driving.model.RectangularField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MenuContext {
    private MenuState currentState;

    public MenuContext() {
        // Initial state
        this.currentState = new StartState();
    }

    public void setCurrentState(MenuState currentState) {
        this.currentState = currentState;
    }

    public void request() {
        currentState.executeRequest();
        changeState();
    }

    // state changed once request is executed usually
    public void changeState() {
        String stateName = currentState.getClass().getSimpleName();
        switch(stateName) {
            case "StartState":
                setCurrentState(new RectangularFieldSetupState());
                break;
            case "RectangularFieldSetupState", "AddCarState":
                List<String> fieldOptions = new ArrayList<>(Arrays.asList("Add a car to field", "Run Simulation"));
                setCurrentState(new OptionState(fieldOptions));
                break;
            case "OptionState":
                OptionState optionState = (OptionState) currentState;
                if (optionState.getOptionSelected() == 1 && Objects.equals(optionState.getOptions().get(0), "Add a car to field")) {
                    setCurrentState(new AddCarState());
                }
                if (optionState.getOptionSelected() == 2 && Objects.equals(optionState.getOptions().get(1), "Run Simulation")) {
                    setCurrentState(new RunSimulationState());
                }

                if (optionState.getOptionSelected() == 1 && Objects.equals(optionState.getOptions().get(0), "Start Over")) {
                    RectangularField.resetInstance();
                    setCurrentState(new StartState());
                }
                if (optionState.getOptionSelected() == 2 && Objects.equals(optionState.getOptions().get(1), "End")) {
                    stateName = "";
                    setCurrentState(null);
                }
                break;
            case "RunSimulationState":
                List<String> postSimulationOptions = new ArrayList<>(Arrays.asList("Start Over", "End"));
                setCurrentState(new OptionState(postSimulationOptions));
                break;
            case "EndState":
                stateName = "";
                setCurrentState(null);
                break;
            default:
                stateName = "";
                setCurrentState(null);
                break;

        }
        if (!stateName.isEmpty()) {
            request();
        }
    }
}
