package com.auto_driving.menu;

import com.auto_driving.model.Car;
import com.auto_driving.model.RectangularField;
import com.auto_driving.validator.CarPositionValidator;

import static com.auto_driving.AutoDrivingConsole.askForInput;
import static com.auto_driving.AutoDrivingConsole.validateInput;

public class AddCarState implements MenuState {
    boolean isPositionValid = false;

    @Override
    public void executeRequest() {

        String nameInput = askForInput("Please enter the name of the car");
        String positionInput = askForInput("Please enter initial position of car " + nameInput + " in x y Direction format:");
        isPositionValid = validateInput(new CarPositionValidator(), positionInput);

        if (isPositionValid) {
            String[] position = positionInput.split(" ");
            int x = Integer.parseInt(position[0]);
            int y = Integer.parseInt(position[1]);
            char direction = position[2].toUpperCase().toCharArray()[0];

            Car car = new Car(nameInput, x, y, direction);
            RectangularField.addCarToField(car);
        }
    }
}
