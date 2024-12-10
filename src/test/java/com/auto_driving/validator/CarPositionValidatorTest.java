package com.auto_driving.validator;

import com.auto_driving.exception.InvalidArgumentsLengthException;
import com.auto_driving.exception.InvalidDirectionException;
import com.auto_driving.exception.InvalidValueException;
import com.auto_driving.exception.PositionOutsideBoundaryException;
import com.auto_driving.model.RectangularField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarPositionValidatorTest {
    private final static String INVALID_NO_OF_ARGUMENTS = "Invalid Number of Arguments. Please enter 3 values separated by a space.";
    private final static String INVALID_VALUE = "Please input numerical values that are more than 0.";
    private final static String INVALID_DIRECTION = "Invalid direction. Please input N, E, S or W.";
    private final static String POSITION_OUTSIDE_BOUNDARY = """
            Your position is outside field boundary.
            The max value for x can be 9.
            The max value for y can be 9.
            """;

    @BeforeEach
    public void setupRectangularField() {
        RectangularField.getInstance(10,10);
    }

    @Test
    public void testPositionInputAllValuesAreValid() {
        CarPositionValidator validator = new CarPositionValidator();
        assertDoesNotThrow(() -> validator.validate("1 2 N"));
    }

    @Test
    public void testArgumentsLessThanThree() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidArgumentsLengthException exception = assertThrows(InvalidArgumentsLengthException.class, () -> validator.validate("5 2"));
        assertEquals(INVALID_NO_OF_ARGUMENTS, exception.getMessage());
    }

    @Test
    public void testArgumentsMoreThanThree() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidArgumentsLengthException exception = assertThrows(InvalidArgumentsLengthException.class, () -> validator.validate("1 2 3 A"));
        assertEquals(INVALID_NO_OF_ARGUMENTS, exception.getMessage());
    }

    @Test
    public void testNonNumericalValuesForX() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("a 1 N"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testNonNumericalValuesForY() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("3 yay S"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testNonNumericalValuesForXAndY() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("abc def N"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testZeroX() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("0 5 E"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testZeroY() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("3 0 E"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testZeroXAndY() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("0 0 S"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testNegativeX() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("-2 5 W"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testNegativeY() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("3 -8 E"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testNegativeXAndY() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidValueException exception = assertThrows(InvalidValueException.class, () -> validator.validate("-2 -8 S"));
        assertEquals(INVALID_VALUE, exception.getMessage());
    }

    @Test
    public void testXExceedFieldWidth() {
        CarPositionValidator validator = new CarPositionValidator();
        PositionOutsideBoundaryException exception = assertThrows(PositionOutsideBoundaryException.class, () -> validator.validate("22 8 S"));
        assertEquals(POSITION_OUTSIDE_BOUNDARY, exception.getMessage());
    }

    @Test
    public void testYExceedFieldHeight() {
        CarPositionValidator validator = new CarPositionValidator();
        PositionOutsideBoundaryException exception = assertThrows(PositionOutsideBoundaryException.class, () -> validator.validate("2 18 E"));
        assertEquals(POSITION_OUTSIDE_BOUNDARY, exception.getMessage());
    }


    @Test
    public void testXAndYExceedFieldWidthAndHeight() {
        CarPositionValidator validator = new CarPositionValidator();
        PositionOutsideBoundaryException exception = assertThrows(PositionOutsideBoundaryException.class, () -> validator.validate("12 18 E"));
        assertEquals(POSITION_OUTSIDE_BOUNDARY, exception.getMessage());
    }

    @Test
    public void testXEqualFieldWidth() {
        CarPositionValidator validator = new CarPositionValidator();
        PositionOutsideBoundaryException exception = assertThrows(PositionOutsideBoundaryException.class, () -> validator.validate("10 8 S"));
        assertEquals(POSITION_OUTSIDE_BOUNDARY, exception.getMessage());
    }

    @Test
    public void testYEqualFieldHeight() {
        CarPositionValidator validator = new CarPositionValidator();
        PositionOutsideBoundaryException exception = assertThrows(PositionOutsideBoundaryException.class, () -> validator.validate("2 10 E"));
        assertEquals(POSITION_OUTSIDE_BOUNDARY, exception.getMessage());
    }


    @Test
    public void testXAndYEqualFieldWidthAndHeight() {
        CarPositionValidator validator = new CarPositionValidator();
        PositionOutsideBoundaryException exception = assertThrows(PositionOutsideBoundaryException.class, () -> validator.validate("10 10 E"));
        assertEquals(POSITION_OUTSIDE_BOUNDARY, exception.getMessage());
    }

    @Test
    public void testDirectionIsNorth() {
        CarPositionValidator validator = new CarPositionValidator();
        assertDoesNotThrow(() -> validator.validate("1 2 N"));
    }

    @Test
    public void testDirectionIsSouth() {
        CarPositionValidator validator = new CarPositionValidator();
        assertDoesNotThrow(() -> validator.validate("5 2 S"));
    }

    @Test
    public void testDirectionIsEast() {
        CarPositionValidator validator = new CarPositionValidator();
        assertDoesNotThrow(() -> validator.validate("1 8 E"));
    }

    @Test
    public void testDirectionIsWest() {
        CarPositionValidator validator = new CarPositionValidator();
        assertDoesNotThrow(() -> validator.validate("2 4 W"));
    }

    @Test
    public void testDirectionStartsWithValidChar() {
        CarPositionValidator validator = new CarPositionValidator();
        assertDoesNotThrow(() -> validator.validate("1 8 East"));
    }

    @Test
    public void testDirectionStartsWithInvalidChar() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidDirectionException exception = assertThrows(InvalidDirectionException.class, () -> validator.validate("1 8 Left"));
        assertEquals(INVALID_DIRECTION, exception.getMessage());
    }

    @Test
    public void testDirectionIsInvalid() {
        CarPositionValidator validator = new CarPositionValidator();
        InvalidDirectionException exception = assertThrows(InvalidDirectionException.class, () -> validator.validate("1 8 F"));
        assertEquals(INVALID_DIRECTION, exception.getMessage());
    }

}
