package com.auto_driving.model;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(cars.size());
        cars.add(car);
        System.out.println(cars.size());
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
}
