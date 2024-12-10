package com.auto_driving.menu;

public class Start implements MenuState{

    @Override
    public void executeRequest() {
        System.out.println("Welcome to Auto Driving Car Simulation!\n");
    }
}
