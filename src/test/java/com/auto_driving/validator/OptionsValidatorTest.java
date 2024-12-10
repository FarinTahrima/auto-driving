package com.auto_driving.validator;

import com.auto_driving.exception.InvalidOptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsValidatorTest {
    private final static String INVALID_OPTION = "Invalid option. Please either input 1 or 2.";
    private final static String INVALID_NUMBER_FORMAT = "Invalid value. Please enter a numerical value.";

    @Test
    public void testOptionInputIsOne() {
        OptionsValidator validator = new OptionsValidator();
        assertDoesNotThrow(() -> validator.validate("1"));
    }

    @Test
    public void testOptionInputIsTwo() {
        OptionsValidator validator = new OptionsValidator();
        assertDoesNotThrow(() -> validator.validate("2"));
    }

    @Test
    public void testOptionInputIsNonNumericalValues() {
        OptionsValidator validator = new OptionsValidator();
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> validator.validate("x"));
        assertEquals(INVALID_NUMBER_FORMAT, exception.getMessage());
    }

    @Test
    public void testOptionInputIsZero() {
        OptionsValidator validator = new OptionsValidator();
        InvalidOptionException exception = assertThrows(InvalidOptionException.class, () -> validator.validate("0"));
        assertEquals(INVALID_OPTION, exception.getMessage());
    }

    @Test
    public void testOptionInputIsNegative() {
        OptionsValidator validator = new OptionsValidator();
        InvalidOptionException exception = assertThrows(InvalidOptionException.class, () -> validator.validate("-2"));
        assertEquals(INVALID_OPTION, exception.getMessage());
    }

    @Test
    public void testOptionInputIsMoreThanTwo() {
        OptionsValidator validator = new OptionsValidator();
        InvalidOptionException exception = assertThrows(InvalidOptionException.class, () -> validator.validate("4"));
        assertEquals(INVALID_OPTION, exception.getMessage());
    }
}
