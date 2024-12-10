package com.auto_driving.rectangular_field;

import com.auto_driving.exception.InvalidArgumentsLengthException;
import com.auto_driving.exception.NonPosititveValueException;

import java.util.Scanner;

public class RectangularField {
    boolean isInputValid = false;

    private long width;
    private long height;

    // print rectangle field input till a valid one is inputted
    public void initRectangularField() {
        while (!isInputValid) {
            String input = inputForRectangularField();
            validateInput(input);
            if (isInputValid) {
                String[] dimensions = input.split(" ");
                generateRectangleField(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
            }
        }
    }

    private String inputForRectangularField() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the width and height of the simulation field in x y format:");
        return scanner.nextLine();
    }

    private void validateInput(String input) {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        try {
            validator.validateInput(input);
            isInputValid = true;
        } catch (InvalidArgumentsLengthException | NumberFormatException | NonPosititveValueException e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateRectangleField (long width, long height)  {
        this.width = width;
        this.height = height;
        System.out.printf("You have created a field of %d x %d.", width, height);
    }
}
