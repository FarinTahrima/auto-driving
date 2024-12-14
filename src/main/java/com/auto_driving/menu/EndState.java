package com.auto_driving.menu;

public class EndState implements MenuState{
    @Override
    public void executeRequest() {
        // ending message
        System.out.println("Thank you for running the simulation. Goodbye!");
    }
}
