package com.auto_driving.menu;

import com.auto_driving.model.Car;
import com.auto_driving.model.CommandManager;
import com.auto_driving.model.FieldManager;
import com.auto_driving.model.RectangularField;

public class RunSimulationState implements MenuState {

    // retrieve the required managers from the rectangular field instance
    FieldManager fieldManager = RectangularField.getFieldManager();
    CommandManager commandManager = RectangularField.getCommandManager();

    @Override
    public void executeRequest() {
        // print the car info of all cars with their initial position first
        System.out.println("Your current list of cars are:");
        for(String carInfo: fieldManager.getListOfCarsInfo(false)) {
            System.out.println("- " + carInfo);
        }
        startSimulation();
    }

    @Override
    public MenuState getNextState() {
        return new PostSimulationState();
    }

    public void startSimulation() {

        // continue to run the iteration
        // 1. when the car with most commands is yet to be executed AND
        // 2. when all the car has not yet collided
        while(commandManager.getStepNumber() <= commandManager.getMaxCommandCount()
                && fieldManager.getCollidedCarsCount() < fieldManager.getCars().size()) {
            for(Car car: fieldManager.getCars()) {

                // if the command size is less or same as the ongoing iteration number
                // and the car hasnt collided yet -> move the car
                if (commandManager.getStepNumber() <= car.getCommands().size()  && !car.getCollisionIndicator().isCollided()) {
                    commandManager.moveCar(car, car.getCommands().get(commandManager.getStepNumber() - 1));
                }
            }
            commandManager.setStepNumber(commandManager.getStepNumber() + 1);
        }

        // print the latest car info of all cars after simulation
        System.out.println("After simulation, the result is:");
        for(String carInfo: fieldManager.getListOfCarsInfo(true)) {
            System.out.println("- " + carInfo);
        }
    }
}
