package com.auto_driving.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RectangularField {
    private static RectangularField instance;
    private static int width;
    private static int height;
    private static List<Car> cars = new ArrayList<Car>();

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

    public static void addCarToField(Car car) {
        cars.add(car);
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
        String carInfo = "";
        for (Car car : cars) {
            String nameStr = car.getName();
            String positionStr = String.format("(%d,%d)", car.getPosition().getX(), car.getPosition().getY());
            String directionStr = String.valueOf(car.getPosition().getDirection());
            carInfo = String.format("- %s, %s %s", nameStr, positionStr, directionStr);
            if (!isPostSimulation) {
                String commandsStr =  car.getCommands().stream()
                        .map(String::valueOf) // Convert Character to String
                        .collect(Collectors.joining());
                carInfo = String.format("- %s, %s %s, %s", nameStr, positionStr, directionStr, commandsStr);
            }

            System.out.println(carInfo);
        }
    }
}
