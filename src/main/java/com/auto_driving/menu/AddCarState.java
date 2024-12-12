package com.auto_driving.menu;

import com.auto_driving.model.Car;
import com.auto_driving.model.CarPosition;
import com.auto_driving.model.RectangularField;
import com.auto_driving.validator.CarCommandValidator;
import com.auto_driving.validator.CarNameValidator;
import com.auto_driving.validator.CarPositionValidator;

import java.util.ArrayList;
import java.util.List;

import static com.auto_driving.AutoDrivingConsole.askForInput;
import static com.auto_driving.AutoDrivingConsole.validateInput;

public class AddCarState implements MenuState {
    boolean isNameValid = false;
    boolean isPositionValid = false;
    boolean isCommandValid = false;

    @Override
    public void executeRequest() {
        String carName = "";
        CarPosition carPosition = null;
        List<Character> carCommands = new ArrayList<Character>();

        // ask for car name
        while (!isNameValid) {
            String nameInput = askForInput("Please enter the name of the car");
            isNameValid = validateInput(new CarNameValidator(), nameInput);
            carName = nameInput;
        }

        // ask for car position
        while (!isPositionValid) {
            String positionInput = askForInput("Please enter initial position of car " + carName + " in x y Direction format:");
            isPositionValid = validateInput(new CarPositionValidator(), positionInput);

            if (isPositionValid) {
                String[] position = positionInput.split(" ");
                int x = Integer.parseInt(position[0]);
                int y = Integer.parseInt(position[1]);
                char direction = position[2].toUpperCase().toCharArray()[0];

                carPosition = new CarPosition(x, y, direction);
            }
        }

        // ask for strategy
        while (!isCommandValid) {
            String strategyInput = askForInput("Please enter the commands for car " + carName + ":");
            isCommandValid = validateInput(new CarCommandValidator(), strategyInput);

            if (isCommandValid) {
                char[] strategyInputArray = strategyInput.toUpperCase().toCharArray();
                for (char c : strategyInputArray) {
                    if (c == 'F' || c == 'R' || c == 'L') {
                        carCommands.add(c);
                    }
                }
            }
        }

        Car car = new Car(carName, carPosition, carCommands);
        RectangularField.addCarToField(car);
        System.out.println("Your current list of cars are:");
        RectangularField.printListOfCars(false);
    }
}
