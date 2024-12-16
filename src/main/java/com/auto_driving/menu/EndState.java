package com.auto_driving.menu;

public class EndState implements MenuState{
    @Override
    public void executeRequest() {
        // ending message
        System.out.println("Thank you for running the simulation. Goodbye!");
    }

    @Override
    public MenuState getNextState() {
        return null;
    }
}
