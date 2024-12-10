package com.auto_driving;

import com.auto_driving.menu.FieldOptions;
import com.auto_driving.menu.MenuContext;
import com.auto_driving.menu.Start;
import com.auto_driving.rectangular_field.RectangularField;
import com.auto_driving.validator.Validator;

import java.util.Scanner;

public class AutoDrivingConsole {

    public static void main(String[] args) {
        // Create context
        MenuContext menuContext = new MenuContext();

        // Initial State: Start Page
        menuContext.setCurrentState(new Start());
        menuContext.request();

        // Start Page >> Rectangular Field
        menuContext.setCurrentState(new RectangularField());
        menuContext.request();

        // Rectangular Field >> Field Options
        menuContext.setCurrentState(new FieldOptions());
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

}
