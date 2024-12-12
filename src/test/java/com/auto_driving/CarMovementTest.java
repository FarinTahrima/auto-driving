package com.auto_driving;

import com.auto_driving.menu.RunSimulationState;
import com.auto_driving.model.CarPosition;
import com.auto_driving.model.RectangularField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarMovementTest {

    @BeforeEach
    public void setup() {
        RectangularField.getInstance(10,10);

    }
    // rotate left
    @Test
    public void testRotateLeftFromNtoW() {
        CarPosition position = new CarPosition(1,1, 'N');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(1,1,'W');
        CarPosition actualPosition =  state.rotateLeft(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testRotateLeftFromWtoS() {
        CarPosition position = new CarPosition(3,4, 'W');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(3,4,'S');
        CarPosition actualPosition =  state.rotateLeft(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testRotateLeftFromStoE() {
        CarPosition position = new CarPosition(2,4, 'S');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(2,4,'E');
        CarPosition actualPosition =  state.rotateLeft(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testRotateLeftFromEtoN() {
        CarPosition position = new CarPosition(7,8, 'E');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(7,8,'N');
        CarPosition actualPosition =  state.rotateLeft(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    // rotate right
    @Test
    public void testRotateRightFromNtoE() {
        CarPosition position = new CarPosition(1,1, 'N');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(1,1,'E');
        CarPosition actualPosition =  state.rotateRight(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testRotateRightFromEtoS() {
        CarPosition position = new CarPosition(3,4, 'E');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(3,4,'S');
        CarPosition actualPosition =  state.rotateRight(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testRotateRightFromStoW() {
        CarPosition position = new CarPosition(2,4, 'S');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(2,4,'W');
        CarPosition actualPosition =  state.rotateRight(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testRotateRightFromEtoN() {
        CarPosition position = new CarPosition(7,8, 'W');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(7,8,'N');
        CarPosition actualPosition =  state.rotateRight(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    // forward
    @Test
    public void testValidForwardForN() {
        CarPosition position = new CarPosition(1,1, 'N');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(1,2,'N');
        CarPosition actualPosition =  state.moveForward(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testNoForwardForNWhenYAddOneWillEqualHeight() {
        CarPosition position = new CarPosition(1,9, 'N');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(1,9,'N');
        CarPosition actualPosition =  state.moveForward(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testValidForwardForS() {
        CarPosition position = new CarPosition(1,4, 'S');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(1,3,'S');
        CarPosition actualPosition =  state.moveForward(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testNoForwardForSWhenYIsAlreadyZero() {
        CarPosition position = new CarPosition(1,0, 'S');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(1,0,'S');
        CarPosition actualPosition =  state.moveForward(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testValidForwardForE() {
        CarPosition position = new CarPosition(1,1, 'E');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(2,1,'E');
        CarPosition actualPosition =  state.moveForward(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testNoForwardForEWhenXAddOneWillEqualWidth() {
        CarPosition position = new CarPosition(9,1, 'E');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(9,1,'E');
        CarPosition actualPosition =  state.moveForward(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testValidForwardForW() {
        CarPosition position = new CarPosition(4,4, 'W');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(3,4,'W');
        CarPosition actualPosition =  state.moveForward(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }

    @Test
    public void testNoForwardForWWhenXIsAlreadyZero() {
        CarPosition position = new CarPosition(0,3, 'W');
        RunSimulationState state = new RunSimulationState();

        CarPosition expectedPosition = new CarPosition(0,3,'W');
        CarPosition actualPosition =  state.moveForward(position);
        boolean areEqual = expectedPosition.equals(actualPosition);

        assertTrue(areEqual);
    }
}
