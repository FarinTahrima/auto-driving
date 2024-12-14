package com.auto_driving.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.auto_driving.AutoDrivingConsole.getPositionXandYPlots;

public class RectangularField {
    private static RectangularField instance;
    private static int width;
    private static int height;

    private static List<Car> cars = new ArrayList<Car>();
    private static int maxCommandCount = 0;
    private static OccupiedPosition occupiedPosition = new OccupiedPosition();

    public RectangularField(int width, int height) {
        RectangularField.width = width;
        RectangularField.height = height;
        System.out.printf("You have created a field of %d x %d.\n", width, height);
    }

    public static RectangularField getInstance(int width, int height) {
        if (instance == null)
            instance = new RectangularField(width, height);
        return instance;
    }

    public static RectangularField getInstance() {
        return instance;
    }

    public static void resetInstance() {
        instance = null;
        cars = new ArrayList<Car>();
        maxCommandCount = 0;
        occupiedPosition = new OccupiedPosition();
    }

    public static void addCarToField(Car car) {
        cars.add(car);

        // update the max command count to determine the no. of max iterations during simulation
        int commandCount = car.getCommands().size();
        if (commandCount > maxCommandCount) {
            setMaxCommandCount(commandCount);
        }

        occupiedPosition.updateOccupiedPosition(car, null, getPositionXandYPlots(car.getPosition()), 0);
    }

    public static int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        RectangularField.height = height;
    }

    public static int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        RectangularField.width = width;
    }

    public static List<Car> getCars() {
        return cars;
    }

    public static void setCars(List<Car> cars) {
        RectangularField.cars = cars;
    }

    public static void printListOfCars(boolean isPostSimulation) {
        String listTitle = !isPostSimulation ? "\nYour current list of cars are:" : "\nAfter simulation, the result is:";
        System.out.println(listTitle);
        for (Car car : cars) {
            String nameStr = car.getName();
            String positionStr = getPositionXandYPlots(car.getPosition());
            String directionStr = String.valueOf(car.getPosition().getDirection());
            String commandsStr =  car.getCommands().stream()
                    .map(String::valueOf) // Convert Character to String
                    .collect(Collectors.joining());

            if (!isPostSimulation) {
                String carInfo = String.format("- %s, %s %s, %s", nameStr, positionStr, directionStr, commandsStr);
                System.out.println(carInfo);
            }

            if (isPostSimulation) {
                String simulationResult = car.getCollisionIndicator().isCollided()
                        ? String.format("-%s, %s", nameStr, car.getCollisionIndicator().getReason()) // print the reason behind the collision
                        : String.format("- %s, %s %s", nameStr, positionStr, directionStr); // print the usual car info when not collided
                System.out.println(simulationResult);
            }
        }
    }

    public static int getMaxCommandCount() {
        return maxCommandCount;
    }

    public static void setMaxCommandCount(int maxCommandCount) {
        RectangularField.maxCommandCount = maxCommandCount;
    }

    public static OccupiedPosition getOccupiedPosition() {
        return occupiedPosition;
    }

    public static void updateOccupiedPosition(Car car, String previousPositionPlot, String newPositionPlot, int step_number) {
        occupiedPosition.updateOccupiedPosition(car, previousPositionPlot, newPositionPlot, step_number);
    }
}
