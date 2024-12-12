package com.auto_driving.validator;

import com.auto_driving.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarCommandValidatorTest {
    private final static String INVALID_COMMANDS = "No valid command found. Please input F, L or R for your commands.";

    // valid commands (single)
    @Test
    public void testValidSingleCommandForward() {
        CarCommandValidator validator = new CarCommandValidator();
        assertDoesNotThrow(() -> validator.validate("F"));
    }

    @Test
    public void testValidSingleCommandLeft() {
        CarCommandValidator validator = new CarCommandValidator();
        assertDoesNotThrow(() -> validator.validate("L"));
    }

    @Test
    public void testValidSingleCommandRight() {
        CarCommandValidator validator = new CarCommandValidator();
        assertDoesNotThrow(() -> validator.validate("R"));
    }

    // valid commands (single) - lower cases are accepted too
    @Test
    public void testValidSingleCommandForwardLowerCase() {
        CarCommandValidator validator = new CarCommandValidator();
        assertDoesNotThrow(() -> validator.validate("f"));
    }

    @Test
    public void testValidSingleCommandLeftLowerCase() {
        CarCommandValidator validator = new CarCommandValidator();
        assertDoesNotThrow(() -> validator.validate("l"));
    }

    @Test
    public void testValidSingleCommandRightLowerCase() {
        CarCommandValidator validator = new CarCommandValidator();
        assertDoesNotThrow(() -> validator.validate("r"));
    }

    // when invalid command is set and is just one char
    @Test
    public void testInvalidSingleCommand() {
        CarCommandValidator validator = new CarCommandValidator();
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> validator.validate("A"));
        assertEquals(INVALID_COMMANDS, exception.getMessage());
    }

    @Test
    public void testInvalidSingleCommandLowerCase() {
        CarCommandValidator validator = new CarCommandValidator();
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> validator.validate("m"));
        assertEquals(INVALID_COMMANDS, exception.getMessage());
    }

    // when string with multiple char with at least one valid char is input, invalid ones will be ignored
    @Test
    public void testValidMultipleCommandsWithAllCorrectCommandsInUpperCase() {
        CarCommandValidator validator = new CarCommandValidator();
        assertDoesNotThrow(() -> validator.validate("FFLRF"));
    }

    @Test
    public void testValidMultipleCommandsWithAllCorrectCommandsInLowerCase() {
        CarCommandValidator validator = new CarCommandValidator();
        assertDoesNotThrow(() -> validator.validate("flrfrrf"));
    }

    @Test
    public void testValidMultipleCommandsWithSomeCorrectCommandsInUpperCase() {
        CarCommandValidator validator = new CarCommandValidator();
        assertDoesNotThrow(() -> validator.validate("FABRF"));
    }

    @Test
    public void testValidMultipleCommandsWithSomeCorrectCommandsInLowerCase() {
        CarCommandValidator validator = new CarCommandValidator();
        assertDoesNotThrow(() -> validator.validate("fiarf"));
    }

    @Test
    public void testValidMultipleCommandsWithSomeCorrectCommandsMixedCases() {
        CarCommandValidator validator = new CarCommandValidator();
        assertDoesNotThrow(() -> validator.validate("FiARl"));
    }

    // when no valid char is input among the multiple chars
    @Test
    public void testInvalidMultipleCommandWithNoCorrectLowerCase() {
        CarCommandValidator validator = new CarCommandValidator();
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> validator.validate("aid"));
        assertEquals(INVALID_COMMANDS, exception.getMessage());
    }

    @Test
    public void testInvalidMultipleCommandWithNoCorrectUpperCase() {
        CarCommandValidator validator = new CarCommandValidator();
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> validator.validate("XYZ"));
        assertEquals(INVALID_COMMANDS, exception.getMessage());
    }

    @Test
    public void testInvalidMultipleCommandWithNoCorrectMixed() {
        CarCommandValidator validator = new CarCommandValidator();
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> validator.validate("aBc"));
        assertEquals(INVALID_COMMANDS, exception.getMessage());
    }

}
