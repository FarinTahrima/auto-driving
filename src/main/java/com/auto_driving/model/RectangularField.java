package com.auto_driving.model;

public class RectangularField {
    private static RectangularField instance;
    private static int width;
    private static int height;

    private static FieldManager fieldManager = new FieldManager(); // to manage the cars on the field
    private static CommandManager commandManager = new CommandManager(); // to manage the commands of a car

    public RectangularField(int width, int height) {
        RectangularField.width = width;
        RectangularField.height = height;
        System.out.printf("You have created a field of %d x %d.\n", width, height);
    }

    public static RectangularField getInstance(int width, int height) {
        if (instance == null)
            instance = new RectangularField(width, height);
        return instance;
    }

    public static RectangularField getInstance() {
        return instance;
    }

    public static void resetInstance() {
        instance = null;
        fieldManager = new FieldManager();
        commandManager = new CommandManager();
    }

    // getters
    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public static FieldManager getFieldManager() {
        return fieldManager;
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }
}
