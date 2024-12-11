package com.auto_driving.model;

import java.util.List;

public class Car {
    private String name;
    private CarPosition position;
    private List<Character> commands;

    public Car(String name, CarPosition position, List<Character> commands) {
        this.name = name;
        this.position = position;
        this.commands = commands;
    }

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
}
