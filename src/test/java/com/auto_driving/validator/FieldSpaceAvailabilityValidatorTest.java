package com.auto_driving.validator;

import com.auto_driving.exception.OutOfSpaceException;
import com.auto_driving.model.Car;
import com.auto_driving.model.CarPosition;
import com.auto_driving.model.RectangularField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.auto_driving.utils.Utils.convertCommandStrToListOfChars;
import static org.junit.jupiter.api.Assertions.*;

public class FieldSpaceAvailabilityValidatorTest {
    private final static String OUT_OF_SPACE = "The field is fully parked. The max capacity is 2.";

    @BeforeEach
    public void setup() {
        RectangularField.getInstance(2,1);
    }

    @AfterEach
    public void reset() {
        RectangularField.resetInstance();
    }

    @Test
    public void spaceAvailableAsNoCarAdded() {
        FieldAvailabilityValidator validator = new FieldAvailabilityValidator();
        assertDoesNotThrow(validator::validate);
    }

    @Test
    public void spaceAvailableWhenOneCarAlreadyAdded() {
        CarPosition carAPosition = new CarPosition("0 0 S");
        List<Character> carACommands = convertCommandStrToListOfChars("LRF");
        Car carA = new Car("A", carAPosition, carACommands);

        RectangularField.getFieldManager().addCarToField(carA);

        FieldAvailabilityValidator validator = new FieldAvailabilityValidator();
        assertDoesNotThrow(validator::validate);
    }

    @Test
    public void spaceIsNotAvailableAsMaxCarsAreAdded() {
        CarPosition carAPosition = new CarPosition("0 0 S");
        List<Character> carACommands = convertCommandStrToListOfChars("LRF");
        Car carA = new Car("A", carAPosition, carACommands);

        CarPosition carBPosition = new CarPosition("1 0 E");
        List<Character> carBCommands = convertCommandStrToListOfChars("FF");
        Car carB = new Car("B", carBPosition, carBCommands);

        RectangularField.getFieldManager().addCarToField(carA);
        RectangularField.getFieldManager().addCarToField(carB);

        FieldAvailabilityValidator validator = new FieldAvailabilityValidator();
        OutOfSpaceException exception = assertThrows(OutOfSpaceException.class, validator::validate);
        assertEquals(OUT_OF_SPACE, exception.getMessage());
    }
}
