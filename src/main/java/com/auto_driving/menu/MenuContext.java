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
                if (RectangularField.getInstance() != null) {
                    RectangularField.resetInstance();
                }
                setCurrentState(new RectangularFieldSetupState());
                break;
            case "RectangularFieldSetupState", "AddCarState":
                setCurrentState(new FieldOptionState());
                break;
            case "FieldOptionState":
                FieldOptionState fieldOptionState = (FieldOptionState) currentState;
                setCurrentState(fieldOptionState.getNextState());
                break;
            case "RunSimulationState":
                setCurrentState(new PostSimulationState());
                break;
            case "PostSimulationState":
                PostSimulationState postSimulationState = (PostSimulationState) currentState;
                setCurrentState(postSimulationState.getNextState());
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
