package com.auto_driving.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.auto_driving.AutoDrivingConsole.getPositionXandYPlots;

public class FieldManager {
    private List<Car> cars = new ArrayList<>();
    private OccupiedPosition occupiedPosition = new OccupiedPosition();

    // getters
    public List<Car> getCars() {
        return cars;
    }

    public OccupiedPosition getOccupiedPosition() {
        return occupiedPosition;
    }

    public void updateOccupiedPosition(Car car, String previousPositionPlot, String newPositionPlot) {
        occupiedPosition.updateOccupiedPosition(car, previousPositionPlot, newPositionPlot);
    }

    // other methods
    public void addCarToField(Car car) {
        cars.add(car);

        // update the max command count to determine the no. of max iterations during simulation
        RectangularField.getCommandManager().checkCommandCount(car.getCommands());

        // update the position that has been occupied
        occupiedPosition.updateOccupiedPosition(car, null, getPositionXandYPlots(car.getPosition()));
    }

    public void printListOfCars(boolean isPostSimulation) {
        String listTitle = !isPostSimulation ? "\nYour current list of cars are:" : "\nAfter simulation, the result is:";
        System.out.println(listTitle);
        for (Car car : cars) {
            String nameStr = car.getName();
            String positionStr = getPositionXandYPlots(car.getPosition());
            String directionStr = String.valueOf(car.getPosition().getDirection());
            String commandsStr =  car.getCommands().stream()
                    .map(String::valueOf) // Convert Character to String
                    .collect(Collectors.joining());

            // for pre simulation the name, position x and y, direction and commands will be displayed for car
            if (!isPostSimulation) {
                String carInfo = String.format("- %s, %s %s, %s", nameStr, positionStr, directionStr, commandsStr);
                System.out.println(carInfo);
            }

            // for post simulation
            // if the car collides, it will print the name and reason
            // else, it will print name, the updated position x and y and direction
            if (isPostSimulation) {
                String simulationResult = car.getCollisionIndicator().isCollided()
                        ? String.format("- %s, %s", nameStr, car.getCollisionIndicator().getReason()) // print the reason behind the collision
                        : String.format("- %s, %s %s", nameStr, positionStr, directionStr); // print the usual car info when not collided
                System.out.println(simulationResult);
            }
        }
    }
}

