package com.auto_driving.model;

import java.util.*;
import java.util.stream.Collectors;

public class OccupiedPosition {
    private Map<String, List<Car>> occupiedPositions = new HashMap<>();
    private int collidedCarsCount = 0;

    public void updateOccupiedPosition(Car car, String previousPositionPlot, String newPositionPlot) {

        // if the previous and new position are the same -> no need to update
        if (Objects.equals(previousPositionPlot, newPositionPlot)) return;

        // when new cars are added to the field, it will add the initial position to the occupied position map
        if (previousPositionPlot == null) {
            List<Car> cars = new ArrayList<Car>();
            cars.add(car);
            occupiedPositions.put(newPositionPlot, cars);
        } else {
            boolean positionIsOccupied = occupiedPositions.containsKey(newPositionPlot);

            // if position occupied it will add the car to the list with parked cars already added
            // else it will be a new entry as that position wasn't occupied earlier
            List<Car> occupiedCarsAtNewPosition = positionIsOccupied ? occupiedPositions.get(newPositionPlot) : new ArrayList<Car>();
            occupiedCarsAtNewPosition.add(car);
            occupiedPositions.put(newPositionPlot, occupiedCarsAtNewPosition);

            // if the position is already occupied -> meaning the car will crash as multiple cars at same position
            if (positionIsOccupied) {
                updateCarCollision(occupiedCarsAtNewPosition, newPositionPlot);
            }

            // remove the previous position
            occupiedPositions.remove(previousPositionPlot);
        }
    }

    // update to the affected cars of the position that their car crashed
    public void updateCarCollision(List<Car> affectedCars, String positionPlot) {
        for(Car car: affectedCars) {
            CollisionIndicator collisionIndicator = car.getCollisionIndicator();
            // if the other car has not collided yet means it will now collide due to both car being at the same position
            // if the other car already collided before thew new car comes in, means it had already collided with other cars in previous step
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
                .collect(Collectors.joining());
        // reason consists of the other cars the car crashed with, at which plot position and which step it collided
        return String.format("collides with %s at %s at step %d", otherCars, positionPlot, RectangularField.getCommandManager().getStepNumber());
    }

    public int getCollidedCarsCount() {
        return collidedCarsCount;
    }

    public void setCollidedCarsCount(int collidedCarsCount) {
        this.collidedCarsCount = collidedCarsCount;
    }
}
