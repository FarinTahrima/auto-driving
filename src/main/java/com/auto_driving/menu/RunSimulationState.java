package com.auto_driving.menu;

import com.auto_driving.model.Car;
import com.auto_driving.model.CarPosition;
import com.auto_driving.model.RectangularField;

public class RunSimulationState implements MenuState {
    @Override
    public void executeRequest() {
        simulateCar(RectangularField.getCars().get(0));
    }

    public void simulateCar(Car car) {
        CarPosition position = car.getPosition();
        for(char c: car.getCommands()) {
            switch(c) {
                case 'L':
                    car.setPosition(rotateLeft(position));
                    break;
                case 'R':
                    car.setPosition(rotateRight(position));
                    break;
                case 'F':
                    car.setPosition(moveForward(position));
                    break;
                default:
                    break;
            }
        }
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

}
