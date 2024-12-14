package com.auto_driving.model;

import java.util.List;

import static com.auto_driving.AutoDrivingConsole.getPositionXandYPlots;
import static com.auto_driving.model.RectangularField.fieldManager;

public class CommandManager {
    private int maxCommandCount = 0;
    private int stepNumber = 1;

    public int getMaxCommandCount() {
        return maxCommandCount;
    }

    public void setMaxCommandCount(int maxCommandCount) {
        this.maxCommandCount = maxCommandCount;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public void moveCar(Car car, char command) {
        CarPosition previousPosition = car.getPosition();
        CarPosition newPosition = previousPosition;
        String previousPositionPlot = getPositionXandYPlots(previousPosition);
        switch(command) {
            case 'L':
                newPosition = rotateLeft(previousPosition);
                break;
            case 'R':
                newPosition = rotateRight(previousPosition);
                break;
            case 'F':
                newPosition = moveForward(previousPosition);
                fieldManager.updateOccupiedPosition(car, previousPositionPlot, getPositionXandYPlots(newPosition));
                break;
            default:
                break;
        }
        car.setPosition(newPosition);
    }

    public CarPosition rotateRight(CarPosition position) {
        char direction = position.getDirection();
        switch (direction) {
            case 'N':
                position.setDirection('E');
                break;
            case 'E':
                position.setDirection('S');
                break;
            case 'S':
                position.setDirection('W');
                break;
            case 'W':
                position.setDirection('N');
                break;
            default:
                break;
        }
        return position;
    }

    public CarPosition rotateLeft(CarPosition position) {
        char direction = position.getDirection();
        switch (direction) {
            case 'N':
                position.setDirection('W');
                break;
            case 'W':
                position.setDirection('S');
                break;
            case 'S':
                position.setDirection('E');
                break;
            case 'E':
                position.setDirection('N');
                break;
            default:
                break;
        }
        return position;
    }

    public CarPosition moveForward(CarPosition position) {
        char direction = position.getDirection();
        switch (direction) {
            case 'N':
                // the max pos y can be field height - 1, pos y cant be equal(as pos y starts from 0, min height is 1) or more than field height
                // move forward to north means upwards, hence increment y by 1
                if (position.getY() < RectangularField.getHeight() - 1) {
                    position.setY(position.getY() + 1);
                }
                break;
            case 'S':
                // the min pos y can be 0
                // move forward to south means downwards, hence decrement y by 1
                if (position.getY() > 0) {
                    position.setY(position.getY() - 1);
                }
                break;
            case 'E':
                // the max pos x can be field width - 1, pos x cant be equal(as pos x starts from 0, min width is 1) or more than field width
                // move forward to east means right, hence increment x by 1
                if (position.getX() < RectangularField.getWidth() - 1) {
                    position.setX(position.getX() + 1);
                }
                break;
            case 'W':
                // the min pos x can be 0
                // move forward to west means left, hence decrement x by 1
                if (position.getX() > 0) {
                    position.setX(position.getX() - 1);
                }
                break;
            default:
                break;
        }
        return position;
    }

    public void checkCommandCount(List<Character> commands) {
        // update the max command count to determine the no. of max iterations during simulation
        int commandCount = commands.size();
        if (commandCount > maxCommandCount) {
            setMaxCommandCount(commandCount);
        }
    }
}
