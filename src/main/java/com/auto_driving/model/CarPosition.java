package com.auto_driving.model;

public class CarPosition {
    private int x;
    private int y;
    private char direction;

    public CarPosition(String positionStr) {
        // extract position x, y and direction from the position string
        String[] position = positionStr.split(" ");
        this.x = Integer.parseInt(position[0]);
        this.y = Integer.parseInt(position[1]);
        this.direction = position[2].toUpperCase().toCharArray()[0];
    }

    // getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CarPosition position = (CarPosition) obj;
        return x == position.x && y == position.y && direction == position.direction;
    }

}
