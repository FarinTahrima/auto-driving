package com.auto_driving.model;

import java.util.List;

public class Car {
    private String name;
    private CarPosition position;
    private List<Character> commands;
    private CollisionIndicator collisionIndicator = new CollisionIndicator();

    public Car(String name, CarPosition position, List<Character> commands) {
        this.name = name;
        this.position = position;
        this.commands = commands;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarPosition getPosition() {
        return position;
    }

    public void setPosition(CarPosition position) {
        this.position = position;
    }

    public List<Character> getCommands() {
        return commands;
    }

    public void setCommands(List<Character> commands) {
        this.commands = commands;
    }

    public CollisionIndicator getCollisionIndicator() {
        return collisionIndicator;
    }

    public void setCollisionIndicator(CollisionIndicator collisionIndicator) {
        this.collisionIndicator = collisionIndicator;
    }
}
