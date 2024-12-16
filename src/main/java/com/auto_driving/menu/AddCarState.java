package com.auto_driving.menu;

import com.auto_driving.model.Car;
import com.auto_driving.model.CarPosition;
import com.auto_driving.model.FieldManager;
import com.auto_driving.model.RectangularField;
import com.auto_driving.validator.CarCommandValidator;
import com.auto_driving.validator.CarNameValidator;
import com.auto_driving.validator.CarPositionValidator;

import java.util.ArrayList;
import java.util.List;

import static com.auto_driving.AutoDrivingConsole.*;

public class AddCarState implements MenuState {

    FieldManager fieldManager = RectangularField.getFieldManager();

    @Override
    public void executeRequest() {

        // car name
        String carName = getInput("Please enter the name of the car", new CarNameValidator());

        // car position
        String positionStr = getInput(
                String.format("Please enter initial position of car %s in x y Direction format:", carName),
                new CarPositionValidator()
        );
        CarPosition carPosition = new CarPosition(positionStr);

        // car commands
        String commandStr = getInput(
                String.format("Please enter the commands for car %s:", carName),
                new CarCommandValidator()
        );

        List<Character> carCommands = convertCommandStrToListOfChars(commandStr);

        Car car = new Car(carName, carPosition, carCommands);

        // add car to the field
        fieldManager.addCarToField(car);

        // print the list of cars added
        System.out.println("Your current list of cars are:");
        for(String carInfo: fieldManager.getListOfCarsInfo(false)) {
            System.out.println("- " + carInfo);
        }

    }
}
