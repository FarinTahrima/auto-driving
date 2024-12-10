package com.auto_driving;

import com.auto_driving.rectangular_field.RectangularField;

public class AutoDrivingConsole {
    public static void main(String[] args) {
        greetings();
        RectangularField rectangularField = new RectangularField();
        rectangularField.initRectangularField();
    }

    private static void greetings() {
        System.out.println("Welcome to Auto Driving Car Simulation!\n");
    }
}
