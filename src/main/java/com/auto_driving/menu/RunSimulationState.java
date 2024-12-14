package com.auto_driving.menu;

import com.auto_driving.model.Car;

import static com.auto_driving.model.RectangularField.commandManager;
import static com.auto_driving.model.RectangularField.fieldManager;

public class RunSimulationState implements MenuState {

    @Override
    public void executeRequest() {
        fieldManager.printListOfCars(false);
        startSimulation();
    }

    public void startSimulation() {

        // continue to run the commands till the car with most commands has been executed or all the cars has collided
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
        fieldManager.printListOfCars(true);
    }

}
