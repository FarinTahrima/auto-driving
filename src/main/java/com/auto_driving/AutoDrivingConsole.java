package com.auto_driving;

import com.auto_driving.menu.*;
import com.auto_driving.model.CarPosition;
import com.auto_driving.validator.Validator;

import java.util.Scanner;

public class AutoDrivingConsole {

    public static void main(String[] args) {
        // Create context
        MenuContext menuContext = new MenuContext();
        menuContext.request();
    }

    public static String askForInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    public static boolean validateInput(Validator validator, String input) {
        try {
            validator.validate(input);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static String getPositionXandYPlots(CarPosition position) {
        return String.format("(%d,%d)", position.getX(), position.getY());
    }
}
