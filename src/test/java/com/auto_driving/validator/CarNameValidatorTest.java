package com.auto_driving.validator;

import com.auto_driving.exception.InvalidNameException;
import com.auto_driving.exception.NameAlreadyExistsException;
import com.auto_driving.model.Car;
import com.auto_driving.model.CarPosition;
import com.auto_driving.model.RectangularField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.auto_driving.AutoDrivingConsole.convertCommandStrToListOfChars;
import static org.junit.jupiter.api.Assertions.*;

public class CarNameValidatorTest {
    private final static String INVALID_NAME = "We do not accept empty values.";
    private final static String NAME_ALREADY_TAKEN = "This name is already taken. Please try another name.";

    @BeforeEach
    public void rectangularFieldSetupAndAddCars() {
        RectangularField.getInstance(10,10);

        CarPosition ferrariPosition = new CarPosition("1 2 N");
        CarPosition toyotaPosition = new CarPosition("7 8 W");
        List<Character> ferrariCommands = convertCommandStrToListOfChars("FFRFFFFRRL");
        List<Character> toyotaCommands = convertCommandStrToListOfChars("FFLFFFFFFF");
        Car ferrariCar = new Car("Ferrari", ferrariPosition, ferrariCommands);
        Car toyotaCar = new Car("Toyota", toyotaPosition, toyotaCommands);

        RectangularField.getFieldManager().addCarToField(ferrariCar);
        RectangularField.getFieldManager().addCarToField(toyotaCar);
    }

    // when valid name is set
    @Test
    public void testValidName() {
        CarNameValidator validator = new CarNameValidator();
        assertDoesNotThrow(() -> validator.validate("Tesla"));
    }

    @Test
    public void testValidNameWithTrailingSpaces() {
        CarNameValidator validator = new CarNameValidator();
        assertDoesNotThrow(() -> validator.validate("   BMW  "));
    }

    @Test
    public void testValidNameWithMiddleSpaces() {
        CarNameValidator validator = new CarNameValidator();
        assertDoesNotThrow(() -> validator.validate("Hot Wheels"));
    }

    // when invalid names are set
    @Test
    public void testInvalidEmptyName() {
        CarNameValidator validator = new CarNameValidator();
        InvalidNameException exception = assertThrows(InvalidNameException.class, () -> validator.validate(""));
        assertEquals(INVALID_NAME, exception.getMessage());
    }

    @Test
    public void testInvalidSpacesOnlyName() {
        CarNameValidator validator = new CarNameValidator();
        InvalidNameException exception = assertThrows(InvalidNameException.class, () -> validator.validate("   "));
        assertEquals(INVALID_NAME, exception.getMessage());
    }

    // when name is already taken by another car
    @Test
    public void testInvalidAsNameAlreadyTaken() {
        CarNameValidator validator = new CarNameValidator();
        NameAlreadyExistsException exception = assertThrows(NameAlreadyExistsException.class, () -> validator.validate("Ferrari"));
        assertEquals(NAME_ALREADY_TAKEN, exception.getMessage());
    }

}
