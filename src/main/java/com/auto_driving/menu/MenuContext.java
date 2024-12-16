package com.auto_driving.menu;

public class MenuContext {
    private MenuState currentState;

    public MenuContext() {
        // Initial state
        this.currentState = new StartState();
    }

    public void request() {
        currentState.executeRequest();
        this.currentState = currentState.getNextState();
        // continue executing requests until there is no more
        if (currentState != null) {
            request();
        }
    }
}
