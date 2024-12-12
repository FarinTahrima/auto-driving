package com.auto_driving.menu;

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
                setCurrentState(new FieldOptionState());
                break;
            case "FieldOptionState":
                FieldOptionState fieldOptionState = (FieldOptionState) currentState;
                if (fieldOptionState.getFieldOptionSelected() == 1) {
                    setCurrentState(new AddCarState());
                }
                if (fieldOptionState.getFieldOptionSelected() == 2) {
                    setCurrentState(new RunSimulationState());
                }
                break;
            case "RunSimulationState":
                setCurrentState(new PostSimulationState());
                break;
            case "PostSimulationState":
                setCurrentState(new EndState());
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
