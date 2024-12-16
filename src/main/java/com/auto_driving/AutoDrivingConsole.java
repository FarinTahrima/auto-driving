package com.auto_driving;

import com.auto_driving.menu.*;
import com.auto_driving.model.CarPosition;
import com.auto_driving.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutoDrivingConsole {

    public static void main(String[] args) {
        // Create context
        MenuContext menuContext = new MenuContext();
        menuContext.request();
    }

    public static String getInput(String message, Validator validator) {
        boolean isValidInput = false;
        String input = "";
        while (!isValidInput) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(message);
             input = scanner.nextLine();

            try {
                validator.validate(input);
                isValidInput = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

    public static String getPositionXandYPlots(CarPosition position) {
        return String.format("(%d,%d)", position.getX(), position.getY());
    }

    public static List<Character> convertCommandStrToListOfChars(String commandStr) {
        List<Character> carCommands = new ArrayList<>();
        char[] commandArray = commandStr.toUpperCase().toCharArray();
        for (char c : commandArray) {
            if (c == 'F' || c == 'R' || c == 'L') {
                carCommands.add(c);
            }
        }
        return carCommands;
    }
}
