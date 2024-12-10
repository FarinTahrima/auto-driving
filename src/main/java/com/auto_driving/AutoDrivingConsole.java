package com.auto_driving;

import com.auto_driving.menu.*;
import com.auto_driving.validator.Validator;

import java.util.Scanner;

public class AutoDrivingConsole {

    public static void main(String[] args) {
        // Create context
        MenuContext menuContext = new MenuContext();

        // Initial State: Start
        menuContext.setCurrentState(new StartState());
        menuContext.request();

        // Start Page >> Rectangular Field Setup
        menuContext.setCurrentState(new RectangularFieldSetupState());
        menuContext.request();

        // Rectangular Field >> Field Options
        FieldOptionState fieldOptionState = new FieldOptionState();
        menuContext.setCurrentState(fieldOptionState);
        menuContext.request();

        if (fieldOptionState.getFieldOptionSelected() == 1) {
            menuContext.setCurrentState(new AddCarState());
            menuContext.request();
        }


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

}
