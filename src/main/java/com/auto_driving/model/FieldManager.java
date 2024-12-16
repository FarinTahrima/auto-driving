package com.auto_driving.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.auto_driving.AutoDrivingConsole.getPositionXandYPlots;

public class FieldManager {
    private List<Car> cars = new ArrayList<>();
    private OccupiedPosition occupiedPosition = new OccupiedPosition();
    private int maxCapacity = 0;

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

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // other methods
    public void addCarToField(Car car) {
        cars.add(car);

        // update the max command count to determine the no. of max iterations during simulation
        RectangularField.getCommandManager().checkCommandCount(car.getCommands());

        // update the position that has been occupied
        occupiedPosition.updateOccupiedPosition(car, null, getPositionXandYPlots(car.getPosition()));
    }

    public List<String> getListOfCarsInfo(boolean isPostSimulation) {
        List<String> result = new ArrayList<>();
        for (Car car : cars) {
            String nameStr = car.getName();
            String positionStr = getPositionXandYPlots(car.getPosition());
            String directionStr = String.valueOf(car.getPosition().getDirection());
            String commandsStr =  car.getCommands().stream()
                    .map(String::valueOf) // Convert Character to String
                    .collect(Collectors.joining());

            // for pre simulation the name, position x and y, direction and commands will be displayed for car
            if (!isPostSimulation) {
                result.add(String.format("%s, %s %s, %s", nameStr, positionStr, directionStr, commandsStr));
            } else {
                // for post simulation
                // if the car collides, it will print the name and reason
                // else, it will print name, the updated position x and y and direction
                result.add(car.getCollisionIndicator().isCollided()
                        ? String.format("%s, %s", nameStr, car.getCollisionIndicator().getReason()) // print the reason behind the collision
                        : String.format("%s, %s %s", nameStr, positionStr, directionStr));// print the usual car info when not collided
            }
        }
        return result;
    }

    public boolean checkAvailability() {
        // if the number of cars parked is less than capacity -> means available
        return cars.size() < maxCapacity;
    }
}

