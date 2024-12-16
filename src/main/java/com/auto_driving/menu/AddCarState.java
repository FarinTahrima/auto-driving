package com.auto_driving.menu;

import com.auto_driving.exception.OutOfSpaceException;
import com.auto_driving.model.Car;
import com.auto_driving.model.CarPosition;
import com.auto_driving.model.FieldManager;
import com.auto_driving.model.RectangularField;
import com.auto_driving.validator.CarCommandValidator;
import com.auto_driving.validator.CarNameValidator;
import com.auto_driving.validator.CarPositionValidator;
import com.auto_driving.validator.FieldAvailabilityValidator;

import java.util.List;

import static com.auto_driving.utils.Utils.convertCommandStrToListOfChars;
import static com.auto_driving.utils.Utils.getInput;

public class AddCarState implements MenuState  {

    // retrieve the required managers from the rectangular field instance
    FieldManager fieldManager = RectangularField.getFieldManager();

    @Override
    public void executeRequest() {

        try {
            FieldAvailabilityValidator validator = new FieldAvailabilityValidator();
            validator.validate(); // first check if the field is available for parking cars

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
        } catch (OutOfSpaceException e) {
            System.out.println(e.getMessage());
        } finally {
            // print the list of cars added
            System.out.println("Your current list of cars are:");
            for(String carInfo: fieldManager.getListOfCarsInfo(false)) {
                System.out.println("- " + carInfo);
            }
        }
    }

    @Override
    public MenuState getNextState() {
        return new FieldOptionState();
    }
}