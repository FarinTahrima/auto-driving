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
    }
}
