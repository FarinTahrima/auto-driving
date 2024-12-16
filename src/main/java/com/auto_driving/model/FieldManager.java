package com.auto_driving.model;

import java.util.*;
import java.util.stream.Collectors;

import static com.auto_driving.utils.Utils.getPositionXandYPlots;

// to manage the cars on the field
public class FieldManager {
    private List<Car> cars = new ArrayList<>();
    private Map<String, List<Car>> occupiedPositions = new HashMap<>();
    private int maxCapacity = 0;
    private int collidedCarsCount = 0;

    // getters and setters
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Map<String, List<Car>> getOccupiedPositions() {
        return occupiedPositions;
    }

    public void setOccupiedPositions(Map<String, List<Car>> occupiedPositions) {
        this.occupiedPositions = occupiedPositions;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCollidedCarsCount() {
        return collidedCarsCount;
    }

    public void setCollidedCarsCount(int collidedCarsCount) {
        this.collidedCarsCount = collidedCarsCount;
    }

    // other methods
    public void addCarToField(Car car) {
        cars.add(car);

        // update the max command count to determine the no. of max iterations during simulation
        RectangularField.getCommandManager().checkCommandCount(car.getCommands());

        // update the position that has been occupied
        updateOccupiedPosition(car, null, getPositionXandYPlots(car.getPosition()));
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

    // to check if the field have spaces for a new car
    public boolean checkAvailability() {
        // if the number of cars parked is less than capacity -> means available
        return cars.size() < maxCapacity;
    }

    public void updateOccupiedPosition(Car car, String previousPositionPlot, String newPositionPlot) {

        // if the previous and new position are the same -> no need to update
        if (Objects.equals(previousPositionPlot, newPositionPlot)) return;

        // when new cars are added to the field, it will add the initial position to the occupied position map
        if (previousPositionPlot == null) {
            List<Car> cars = new ArrayList<>();
            cars.add(car);
            occupiedPositions.put(newPositionPlot, cars);
        } else {
            boolean positionIsOccupied = occupiedPositions.containsKey(newPositionPlot);

            // if position occupied it will add the car to the list with other cars on the same position x and y
            // else it will be a new entry as that position wasn't occupied earlier
            List<Car> occupiedCarsAtNewPosition = positionIsOccupied ? occupiedPositions.get(newPositionPlot) : new ArrayList<>();
            occupiedCarsAtNewPosition.add(car);
            occupiedPositions.put(newPositionPlot, occupiedCarsAtNewPosition);

            // if the position is already occupied -> meaning the car will collide as multiple cars at same position x and y
            if (positionIsOccupied) {
                updateCarCollision(occupiedCarsAtNewPosition, newPositionPlot);
            }

            // remove the previous position
            occupiedPositions.remove(previousPositionPlot);
        }
    }

    // update to the affected cars of the position that their car collided
    public void updateCarCollision(List<Car> affectedCars, String positionPlot) {
        for(Car car: affectedCars) {
            CollisionIndicator collisionIndicator = car.getCollisionIndicator();
            // if the other car has not collided yet means it will now collide due to both car being at the same position
            // if the other car already collided before then new car comes in, means it had already collided with other cars in a past step
            if (!collisionIndicator.isCollided()) {
                collisionIndicator.setCollided(true);
                collisionIndicator.setReason(getCollisionReason(car, affectedCars, positionPlot));
                collidedCarsCount++;
            }
        }
    }

    private String getCollisionReason(Car car, List<Car> affectedCars, String positionPlot) {
        String carName = car.getName();
        String otherCars =  affectedCars.stream()
                .map(Car::getName)
                .filter(name -> !name.equals(carName))
                .collect(Collectors.joining(","));
        // reason consists of the other cars the car crashed with, at which plot position and which step it collided
        return String.format("collides with %s at %s at step %d", otherCars, positionPlot, RectangularField.getCommandManager().getStepNumber());
    }
}

