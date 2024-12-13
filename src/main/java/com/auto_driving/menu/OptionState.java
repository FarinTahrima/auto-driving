package com.auto_driving.menu;

import com.auto_driving.validator.OptionsValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.auto_driving.AutoDrivingConsole.askForInput;
import static com.auto_driving.AutoDrivingConsole.validateInput;

public class OptionState implements MenuState {

    boolean isOptionValid = false;
    private int optionSelected;
    private List<String> options = new ArrayList<>();

    public OptionState(List<String> options) {
        this.options = options;
    }

    @Override
    public void executeRequest() {
        while (!isOptionValid) {
            String optionsStr =  IntStream.range(0, options.size())
                    .mapToObj(i -> String.format("[%d] %s\n", i + 1, options.get(i))) // Map index and element
                    .collect(Collectors.joining()); // Collect as a list
            String input = askForInput("\nPlease choose from the following options:\n" + optionsStr);
            isOptionValid = validateInput(new OptionsValidator(options), input);
            if (isOptionValid) {
                setOptionSelected(Integer.parseInt(input));
            }
        }
    }

    public int getOptionSelected() {
        return optionSelected;
    }

    public void setOptionSelected(int optionSelected) {
        this.optionSelected = optionSelected;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
