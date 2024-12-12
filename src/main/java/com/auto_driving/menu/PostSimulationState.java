package com.auto_driving.menu;

import com.auto_driving.model.RectangularField;

public class PostSimulationState implements MenuState{
    @Override
    public void executeRequest() {
        System.out.println("After simulation, the result is:");
        RectangularField.printListOfCars(true);
    }
}
