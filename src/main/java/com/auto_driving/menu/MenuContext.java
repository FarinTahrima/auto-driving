package com.auto_driving.menu;

import com.auto_driving.model.RectangularField;

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
                // when start over option is selected, need to reset the field instance
                if (RectangularField.getInstance() != null) {
                    RectangularField.resetInstance();
                }
                // Start -> Setup the field
                setCurrentState(new RectangularFieldSetupState());
                break;
            case "RectangularFieldSetupState", "AddCarState":
                // Setup field -> Options to choose for field
                // When car is already added -> Options to choose for field (whether to run more or run simulation)
                setCurrentState(new FieldOptionState());
                break;
            case "FieldOptionState":
                // Option selected -> change state based on the selected option
                FieldOptionState fieldOptionState = (FieldOptionState) currentState;
                setCurrentState(fieldOptionState.getNextState());
                break;
            case "RunSimulationState":
                // when run simulation -> post simulation
                setCurrentState(new PostSimulationState());
                break;
            case "PostSimulationState":
                // Post simulation -> option to choose whether to start over simulation or end
                PostSimulationState postSimulationState = (PostSimulationState) currentState;
                setCurrentState(postSimulationState.getNextState());
                break;
            default:
                // last step, no more new state
                stateName = "";
                setCurrentState(null);
                break;
        }

        // execute request whenever the current state is not null
        if (!stateName.isEmpty()) {
            request();
        }
    }
}
