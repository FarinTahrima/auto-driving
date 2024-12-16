package com.auto_driving.menu;

import com.auto_driving.model.RectangularField;

public class StartState implements MenuState{

    @Override
    public void executeRequest() {
        if (RectangularField.getInstance() != null) {
            RectangularField.resetInstance();
        }
        System.out.println("Welcome to Auto Driving Car Simulation!\n");
    }

    @Override
    public MenuState getNextState() {
        return new RectangularFieldSetupState();
    }
}
