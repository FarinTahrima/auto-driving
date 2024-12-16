package com.auto_driving.utils;

import com.auto_driving.model.CarPosition;
import com.auto_driving.validator.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static String getInput(String message, InputValidator validator) {
        boolean isValidInput = false;
        String input = "";
        // continue to ask for input till a valid response is received
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
            // redundant chars in the command will be filtered out
            if (c == 'F' || c == 'R' || c == 'L') {
                carCommands.add(c);
            }
        }
        return carCommands;
    }
}
