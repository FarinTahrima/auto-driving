package com.auto_driving.rectangular_field;

import com.auto_driving.exception.InvalidArgumentsLengthException;
import com.auto_driving.exception.NonPosititveValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularFieldValidatorTest {
    @Test
    public void testValidDimensions() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        assertDoesNotThrow(() -> validator.validateInput("5 4"));
    }

    @Test
    public void testArgumentsLessThanTwo() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidArgumentsLengthException exception = assertThrows(InvalidArgumentsLengthException.class, () -> validator.validateInput("5"));
        assertEquals("Invalid Number of Arguments. Please enter 2 values separated by a space.", exception.getMessage());
    }

    @Test
    public void testArgumentsMoreThanTwo() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        InvalidArgumentsLengthException exception = assertThrows(InvalidArgumentsLengthException.class, () -> validator.validateInput("1 2 3"));
        assertEquals("Invalid Number of Arguments. Please enter 2 values separated by a space.", exception.getMessage());
    }

    @Test
    public void testNonNumericalValuesForWidth() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> validator.validateInput("a 1"));
        assertEquals("Invalid values. Please enter numerical values.", exception.getMessage());
    }

    @Test
    public void testNonNumericalValuesForHeight() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> validator.validateInput("3 yay"));
        assertEquals("Invalid values. Please enter numerical values.", exception.getMessage());
    }

    @Test
    public void testNonNumericalValuesForWidthAndHeight() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> validator.validateInput("abc def"));
        assertEquals("Invalid values. Please enter numerical values.", exception.getMessage());
    }

    @Test
    public void testZeroWidth() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        NonPosititveValueException exception = assertThrows(NonPosititveValueException.class, () -> validator.validateInput("0 5"));
        assertEquals("Please input values that are more than 0.", exception.getMessage());
    }

    @Test
    public void testZeroHeight() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        NonPosititveValueException exception = assertThrows(NonPosititveValueException.class, () -> validator.validateInput("3 0"));
        assertEquals("Please input values that are more than 0.", exception.getMessage());
    }

    @Test
    public void testZeroWidthAndHeight() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        NonPosititveValueException exception = assertThrows(NonPosititveValueException.class, () -> validator.validateInput("0 0"));
        assertEquals("Please input values that are more than 0.", exception.getMessage());
    }

    @Test
    public void testNegativeWidth() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        NonPosititveValueException exception = assertThrows(NonPosititveValueException.class, () -> validator.validateInput("-2 5"));
        assertEquals("Please input values that are more than 0.", exception.getMessage());
    }

    @Test
    public void testNegativeHeight() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        NonPosititveValueException exception = assertThrows(NonPosititveValueException.class, () -> validator.validateInput("3 -8"));
        assertEquals("Please input values that are more than 0.", exception.getMessage());
    }

    @Test
    public void testNegativeWidthAndHeight() {
        RectangularFieldValidator validator = new RectangularFieldValidator();
        NonPosititveValueException exception = assertThrows(NonPosititveValueException.class, () -> validator.validateInput("-2 -8"));
        assertEquals("Please input values that are more than 0.", exception.getMessage());
    }
}
