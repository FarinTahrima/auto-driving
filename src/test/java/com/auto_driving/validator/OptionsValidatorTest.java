package com.auto_driving.validator;

import com.auto_driving.exception.InvalidOptionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsValidatorTest {
    private final static String INVALID_OPTION = "Invalid option. Please input a correct option number.";
    private final static String INVALID_NUMBER_FORMAT = "Invalid value. Please enter a numerical value.";
    List<String> options = new ArrayList<>(Arrays.asList("Add a car to field", "Run Simulation"));

    // when valid option is input
    @Test
    public void testOptionInputIsOne() {
        OptionsValidator validator = new OptionsValidator(options);
        assertDoesNotThrow(() -> validator.validate("1"));
    }

    @Test
    public void testOptionInputIsTwo() {
        OptionsValidator validator =  new OptionsValidator(options);
        assertDoesNotThrow(() -> validator.validate("2"));
    }

    // when non-numerical value is input for options
    @Test
    public void testOptionInputIsNonNumericalValues() {
        OptionsValidator validator =  new OptionsValidator(options);
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> validator.validate("x"));
        assertEquals(INVALID_NUMBER_FORMAT, exception.getMessage());
    }

    // when non-positive, zero or number more than 2 is input for option
    @Test
    public void testOptionInputIsZero() {
        OptionsValidator validator =  new OptionsValidator(options);
        InvalidOptionException exception = assertThrows(InvalidOptionException.class, () -> validator.validate("0"));
        assertEquals(INVALID_OPTION, exception.getMessage());
    }

    @Test
    public void testOptionInputIsNegative() {
        OptionsValidator validator =  new OptionsValidator(options);
        InvalidOptionException exception = assertThrows(InvalidOptionException.class, () -> validator.validate("-2"));
        assertEquals(INVALID_OPTION, exception.getMessage());
    }

    @Test
    public void testOptionInputIsMoreThanOptionSize() {
        OptionsValidator validator =  new OptionsValidator(options);
        InvalidOptionException exception = assertThrows(InvalidOptionException.class, () -> validator.validate("4"));
        assertEquals(INVALID_OPTION, exception.getMessage());
    }
}
