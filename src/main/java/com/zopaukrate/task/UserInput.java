package com.zopaukrate.task;

import com.zopaukrate.task.exception.InvalidUserInputException;

import java.util.Optional;

public class UserInput {

    private final String[] input;

    public UserInput(String[] input) {
        checkMinimumInput(input);
        this.input = input;
    }

    private void checkMinimumInput(String[] input) {
        if (input == null || input.length == 0) {
            throw new InvalidUserInputException("You need to provide at-least one argument (requested loan amount)");
        }
    }

    public int getAmountInput() {
        try {
            return Integer.parseInt(input[0]);
        } catch (NumberFormatException exception) {
            throw new InvalidUserInputException(String.format("Invalid value provided for loan amount: ", input[0]));
        }
    }

    public Optional<String> getLendersFilePath() {
        if (input.length >= 2) return Optional.of(input[1]);
        return Optional.empty();
    }

    public Optional<Integer> getLoanDurationInMonths() {
        if (input.length < 3) return Optional.empty();
        try {
            return Optional.of(Integer.parseInt(input[2]));
        } catch (NumberFormatException exception) {
            throw new InvalidUserInputException(String.format("Invalid value provided for loan duration: ", input[2]));
        }
    }
}
