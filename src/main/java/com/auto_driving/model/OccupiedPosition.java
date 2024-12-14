package com.auto_driving.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.auto_driving.model.RectangularField.commandManager;

public class OccupiedPosition {
    private Map<String, List<Car>> occupiedPositions = new HashMap<>();
    private int collidedCarsCount = 0;

    public void updateOccupiedPosition(Car car, String previousPositionPlot, String newPositionPlot) {
        // when new cars are added to the field, assumption is when new car is added it cannot take an occupied position
        if (previousPositionPlot == null) {
            List<Car> cars = new ArrayList<Car>();
            cars.add(car);
            occupiedPositions.put(newPositionPlot, cars);
        } else {
            boolean willCarCollide = occupiedPositions.containsKey(newPositionPlot);

            List<Car> occupiedCarsAtNewPosition = willCarCollide ? occupiedPositions.get(newPositionPlot) : new ArrayList<Car>();
            occupiedCarsAtNewPosition.add(car);
            occupiedPositions.put(newPositionPlot, occupiedCarsAtNewPosition);

            // if the plot is found in occupied positions, meaning the car will crash
            if (willCarCollide) {
                updateCarCollision(occupiedCarsAtNewPosition, newPositionPlot);
            }

            // remove the previous position
            occupiedPositions.remove(previousPositionPlot);
        }
    }

    // crash update
    public void updateCarCollision(List<Car> affectedCars, String positionPlot) {
        for(Car car: affectedCars) {
            CollisionIndicator collisionIndicator = car.getCollisionIndicator();
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
        return String.format("collides with %s at %s at step %d", otherCars, positionPlot, commandManager.getStepNumber());
    }

    public int getCollidedCarsCount() {
        return collidedCarsCount;
    }

    public void setCollidedCarsCount(int collidedCarsCount) {
        this.collidedCarsCount = collidedCarsCount;
    }
}
