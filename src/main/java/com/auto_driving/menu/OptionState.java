package com.auto_driving.menu;

import com.auto_driving.validator.OptionsValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.auto_driving.AutoDrivingConsole.*;

public abstract class OptionState implements MenuState {

    private int optionSelected;
    private List<String> options = new ArrayList<>();

    public OptionState(List<String> options) {
        this.options = options;
    }

    @Override
    public void executeRequest() {
        // display the options to choose from
        String optionsStr =  IntStream.range(0, options.size())
                .mapToObj(i -> String.format("[%d] %s\n", i + 1, options.get(i)))
                .collect(Collectors.joining());
        String message = "\nPlease choose from the following options:\n" + optionsStr;

        // determine the selected option
        setOptionSelected(Integer.parseInt(getInput(message, new OptionsValidator(options))));
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
