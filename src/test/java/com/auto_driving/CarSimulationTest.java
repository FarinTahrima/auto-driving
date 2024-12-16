package com.auto_driving;

import com.auto_driving.menu.RunSimulationState;
import com.auto_driving.model.Car;
import com.auto_driving.model.CarPosition;
import com.auto_driving.model.RectangularField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.auto_driving.AutoDrivingConsole.convertCommandStrToListOfChars;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarSimulationTest {
    @BeforeEach
    public void setup() {
        RectangularField.getInstance(10,10);
    }

    @AfterEach
    public void reset() {
        RectangularField.resetInstance();
    }

    // NOTE: these are the cases when a car attempts to cross boundary, position will be unchanged
    // when x = 0, direction = W, and next command to execute is F
    // when x = field width - 1, direction = E, and next command to execute is F
    // when y = 0, direction = S, and next command to execute is F
    // when y = field height - 1, direction = N, and next command to execute is F


    // single car
    @Test
    public void singleCarWithMultipleCommand() {
        CarPosition carPosition = new CarPosition("1 2 N");
        List<Character> carCommands = convertCommandStrToListOfChars("FFRFFFFRRL");
        Car car = new Car("A", carPosition, carCommands);
        RectangularField.getFieldManager().addCarToField(car);

        RunSimulationState simulationState = new RunSimulationState();
        simulationState.startSimulation();

        List<String> expectedResult = List.of("A, (5,4) S");
        List<String> actualResult = RectangularField.getFieldManager().getListOfCarsInfo(true);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void singleCarWithMultipleCommandAndAttemptsToCrossBoundary() {
        CarPosition carPosition = new CarPosition("1 2 N");
        List<Character> carCommands = convertCommandStrToListOfChars("LFF");
        Car car = new Car("A", carPosition, carCommands);
        RectangularField.getFieldManager().addCarToField(car);

        RunSimulationState simulationState = new RunSimulationState();
        simulationState.startSimulation();

        List<String> expectedResult = List.of("A, (0,2) W");
        List<String> actualResult = RectangularField.getFieldManager().getListOfCarsInfo(true);
        assertEquals(expectedResult, actualResult);
    }

    // two cars
    @Test
    public void twoCarsWithoutCollisionAndSameNoOfCommands() {
        CarPosition carAPosition = new CarPosition("4 5 N");
        List<Character> carACommands = convertCommandStrToListOfChars("RLRFF");
        Car carA = new Car("A", carAPosition, carACommands);

        CarPosition carBPosition = new CarPosition("9 9 W");
        List<Character> carBCommands = convertCommandStrToListOfChars("FFLFF");
        Car carB = new Car("B", carBPosition, carBCommands);

        RectangularField.getFieldManager().addCarToField(carA);
        RectangularField.getFieldManager().addCarToField(carB);
        RunSimulationState simulationState = new RunSimulationState();
        simulationState.startSimulation();

        List<String> expectedResult = List.of("A, (6,5) E", "B, (7,7) S");
        List<String> actualResult = RectangularField.getFieldManager().getListOfCarsInfo(true);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void twoCarsWithoutCollisionAndDifferentNoOfCommands() {
        CarPosition carAPosition = new CarPosition("2 3 E");
        List<Character> carACommands = convertCommandStrToListOfChars("FFR");
        Car carA = new Car("A", carAPosition, carACommands);

        CarPosition carBPosition = new CarPosition("1 2 W");
        List<Character> carBCommands = convertCommandStrToListOfChars("FR");
        Car carB = new Car("B", carBPosition, carBCommands);

        RectangularField.getFieldManager().addCarToField(carA);
        RectangularField.getFieldManager().addCarToField(carB);
        RunSimulationState simulationState = new RunSimulationState();
        simulationState.startSimulation();

        List<String> expectedResult = List.of("A, (4,3) S", "B, (0,2) N");
        List<String> actualResult = RectangularField.getFieldManager().getListOfCarsInfo(true);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void twoCarsWithCollisionAndSameNoOfCommands() {
        CarPosition carAPosition = new CarPosition("1 2 N");
        List<Character> carACommands = convertCommandStrToListOfChars("FFRFFFFRRL");
        Car carA = new Car("A", carAPosition, carACommands);

        CarPosition carBPosition = new CarPosition("7 8 W");
        List<Character> carBCommands = convertCommandStrToListOfChars("FFLFFFFFFF");
        Car carB = new Car("B", carBPosition, carBCommands);

        RectangularField.getFieldManager().addCarToField(carA);
        RectangularField.getFieldManager().addCarToField(carB);
        RunSimulationState simulationState = new RunSimulationState();
        simulationState.startSimulation();

        List<String> expectedResult = List.of("A, collides with B at (5,4) at step 7", "B, collides with A at (5,4) at step 7");
        List<String> actualResult = RectangularField.getFieldManager().getListOfCarsInfo(true);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void twoCarsWithCollisionAndDifferentNoOfCommands() {
        CarPosition carAPosition = new CarPosition("8 7 S");
        List<Character> carACommands = convertCommandStrToListOfChars("RRF");
        Car carA = new Car("A", carAPosition, carACommands);

        CarPosition carBPosition = new CarPosition("7 7 E");
        List<Character> carBCommands = convertCommandStrToListOfChars("FF");
        Car carB = new Car("B", carBPosition, carBCommands);

        RectangularField.getFieldManager().addCarToField(carA);
        RectangularField.getFieldManager().addCarToField(carB);
        RunSimulationState simulationState = new RunSimulationState();
        simulationState.startSimulation();

        List<String> expectedResult = List.of("A, collides with B at (8,7) at step 1", "B, collides with A at (8,7) at step 1");
        List<String> actualResult = RectangularField.getFieldManager().getListOfCarsInfo(true);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void multipleCarsWithNoCollision() {
        CarPosition carAPosition = new CarPosition("1 2 E");
        List<Character> carACommands = convertCommandStrToListOfChars("RLF");
        Car carA = new Car("A", carAPosition, carACommands);

        CarPosition carBPosition = new CarPosition("4 5 S");
        List<Character> carBCommands = convertCommandStrToListOfChars("FF");
        Car carB = new Car("B", carBPosition, carBCommands);

        CarPosition carCPosition = new CarPosition("7 8 N");
        List<Character> carCCommands = convertCommandStrToListOfChars("RRF");
        Car carC = new Car("C", carCPosition, carCCommands);

        CarPosition carDPosition = new CarPosition("9 9 W");
        List<Character> carDCommands = convertCommandStrToListOfChars("RF");
        Car carD = new Car("D", carDPosition, carDCommands);

        RectangularField.getFieldManager().addCarToField(carA);
        RectangularField.getFieldManager().addCarToField(carB);
        RectangularField.getFieldManager().addCarToField(carC);
        RectangularField.getFieldManager().addCarToField(carD);

        RunSimulationState simulationState = new RunSimulationState();
        simulationState.startSimulation();

        List<String> expectedResult = List.of("A, (2,2) E", "B, (4,3) S", "C, (7,7) S", "D, (9,9) N");
        List<String> actualResult = RectangularField.getFieldManager().getListOfCarsInfo(true);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void multipleCarsWithTwoCarsColliding() {
        // 4 cars, 2 collided at a certain step
        CarPosition carAPosition = new CarPosition("1 2 E");
        List<Character> carACommands = convertCommandStrToListOfChars("RLF");
        Car carA = new Car("A", carAPosition, carACommands);

        CarPosition carBPosition = new CarPosition("4 5 S");
        List<Character> carBCommands = convertCommandStrToListOfChars("FF");
        Car carB = new Car("B", carBPosition, carBCommands);

        CarPosition carCPosition = new CarPosition("8 9 E");
        List<Character> carCCommands = convertCommandStrToListOfChars("RLFFF");
        Car carC = new Car("C", carCPosition, carCCommands);

        CarPosition carDPosition = new CarPosition("9 9 W");
        List<Character> carDCommands = convertCommandStrToListOfChars("FFR");
        Car carD = new Car("D", carDPosition, carDCommands);

        RectangularField.getFieldManager().addCarToField(carA);
        RectangularField.getFieldManager().addCarToField(carB);
        RectangularField.getFieldManager().addCarToField(carC);
        RectangularField.getFieldManager().addCarToField(carD);

        RunSimulationState simulationState = new RunSimulationState();
        simulationState.startSimulation();

        List<String> expectedResult = List.of("A, (2,2) E", "B, (4,3) S", "C, collides with D at (8,9) at step 1", "D, collides with C at (8,9) at step 1");
        List<String> actualResult = RectangularField.getFieldManager().getListOfCarsInfo(true);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void multipleCarsWithThreeCarsCollidingAtSameIteration() {
        // 4 cars, with 3 colliding at a certain step
        CarPosition carAPosition = new CarPosition("1 2 E");
        List<Character> carACommands = convertCommandStrToListOfChars("RLF");
        Car carA = new Car("A", carAPosition, carACommands);

        CarPosition carBPosition = new CarPosition("4 5 S");
        List<Character> carBCommands = convertCommandStrToListOfChars("LFFFFLFFFF");
        Car carB = new Car("B", carBPosition, carBCommands);

        CarPosition carCPosition = new CarPosition("8 9 E");
        List<Character> carCCommands = convertCommandStrToListOfChars("RLFFF");
        Car carC = new Car("C", carCPosition, carCCommands);

        CarPosition carDPosition = new CarPosition("9 9 W");
        List<Character> carDCommands = convertCommandStrToListOfChars("FFR");
        Car carD = new Car("D", carDPosition, carDCommands);

        RectangularField.getFieldManager().addCarToField(carA);
        RectangularField.getFieldManager().addCarToField(carB);
        RectangularField.getFieldManager().addCarToField(carC);
        RectangularField.getFieldManager().addCarToField(carD);

        RunSimulationState simulationState = new RunSimulationState();
        simulationState.startSimulation();

        List<String> expectedResult = List.of("A, (2,2) E", "B, collides with C,D at (8,9) at step 10", "C, collides with D at (8,9) at step 1", "D, collides with C at (8,9) at step 1");
        List<String> actualResult = RectangularField.getFieldManager().getListOfCarsInfo(true);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void multipleCarsWithTwoDifferentCarsCollidingAtDifferentTimes() {
        // 4 cars, with 2 colliding each other at one step, other 2 colliding each other at another step
        // 4 cars, 2 collided at a certain step
        CarPosition carAPosition = new CarPosition("1 2 E");
        List<Character> carACommands = convertCommandStrToListOfChars("RLFFF");
        Car carA = new Car("A", carAPosition, carACommands);

        CarPosition carBPosition = new CarPosition("4 5 S");
        List<Character> carBCommands = convertCommandStrToListOfChars("FFF");
        Car carB = new Car("B", carBPosition, carBCommands);

        CarPosition carCPosition = new CarPosition("8 9 E");
        List<Character> carCCommands = convertCommandStrToListOfChars("RLFFF");
        Car carC = new Car("C", carCPosition, carCCommands);

        CarPosition carDPosition = new CarPosition("9 9 W");
        List<Character> carDCommands = convertCommandStrToListOfChars("FFR");
        Car carD = new Car("D", carDPosition, carDCommands);

        RectangularField.getFieldManager().addCarToField(carA);
        RectangularField.getFieldManager().addCarToField(carB);
        RectangularField.getFieldManager().addCarToField(carC);
        RectangularField.getFieldManager().addCarToField(carD);

        RunSimulationState simulationState = new RunSimulationState();
        simulationState.startSimulation();

        List<String> expectedResult = List.of("A, collides with B at (4,2) at step 5", "B, collides with A at (4,2) at step 5", "C, collides with D at (8,9) at step 1", "D, collides with C at (8,9) at step 1");
        List<String> actualResult = RectangularField.getFieldManager().getListOfCarsInfo(true);
        assertEquals(expectedResult, actualResult);
    }
}
