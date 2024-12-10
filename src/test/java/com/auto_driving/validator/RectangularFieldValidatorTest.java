package com.auto_driving.validator;

import com.auto_driving.exception.InvalidArgumentsLengthException;
import com.auto_driving.exception.InvalidValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularFieldValidatorTest {
    private final static String INVALID_NO_OF_ARGUMENTS = "Invalid Number of Arguments. Please enter 2 values separated by a space.";
    private final static String INVALID_VALUE = "Please input numerical values that are more than 0.";

    @Test
    public void testValidDimensions() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        assertDoesNotThrow(() -> validator.validate("5 4"));
    }

    @Test
    public void testArgumentsLessThanTwo() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidArgumentsLengthException exception = assertThrows(InvalidArgumentsLengthException.class, () -> validator.validate("5"));
        assertEquals(INVALID_NO_OF_ARGUMENTS, exception.getMessage());
    }

    @Test
    public void testArgumentsMoreThanTwo() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidArgumentsLengthException exception = assertThrows(InvalidArgumentsLengthException.class, () -> validator.validate("1 2 3"));
        assertEquals(INVALID_NO_OF_ARGUMENTS, exception.getMessage());
    }

    @Test
    public void testNonNumericalValuesForWidth() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("a 1"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testNonNumericalValuesForHeight() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("3 yay"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testNonNumericalValuesForWidthAndHeight() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("abc def"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testZeroWidth() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("0 5"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testZeroHeight() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("3 0"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testZeroWidthAndHeight() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("0 0"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testNegativeWidth() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("-2 5"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testNegativeHeight() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("3 -8"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testNegativeWidthAndHeight() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("-2 -8"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }
}
