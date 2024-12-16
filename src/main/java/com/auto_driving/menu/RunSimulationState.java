package com.auto_driving.menu;

import com.auto_driving.model.Car;
import com.auto_driving.model.CommandManager;
import com.auto_driving.model.FieldManager;
import com.auto_driving.model.RectangularField;

public class RunSimulationState implements MenuState {

    FieldManager fieldManager = RectangularField.getFieldManager();
    CommandManager commandManager = RectangularField.getCommandManager();

    @Override
    public void executeRequest() {
        // print the list of cars with their current initial position first
        System.out.println("Your current list of cars are:");
        for(String carInfo: fieldManager.getListOfCarsInfo(false)) {
            System.out.println("- " + carInfo);
        }
        startSimulation();
    }

    public void startSimulation() {

        // continue to run the commands till
        // 1. the car with most commands has been executed OR
        // 2. all the cars has collided
        while(commandManager.getStepNumber() <= commandManager.getMaxCommandCount()
                && fieldManager.getOccupiedPosition().getCollidedCarsCount() < fieldManager.getCars().size()) {
            for(Car car: fieldManager.getCars()) {

                // if the command size is less or same as the ongoing iteration number and the car hasnt collided yet -> move the car
                if (commandManager.getStepNumber() <= car.getCommands().size()  && !car.getCollisionIndicator().isCollided()) {
                    commandManager.moveCar(car, car.getCommands().get(commandManager.getStepNumber() - 1));
                }
            }
            commandManager.setStepNumber(commandManager.getStepNumber() + 1);
        }
        System.out.println("After simulation, the result is:");
        for(String carInfo: fieldManager.getListOfCarsInfo(true)) {
            System.out.println("- " + carInfo);
        }
    }

}
