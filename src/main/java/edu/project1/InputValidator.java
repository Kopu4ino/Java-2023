package edu.project1;

import org.jetbrains.annotations.NotNull;

public class InputValidator {
    private InputValidator() {
    }

    @NotNull
    public static ValidationResponse validate(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new ValidationResponse(false, "Input cannot be empty");
        }

        String trimmedInput = input.trim();
        if (trimmedInput.length() > 1) {
            return new ValidationResponse(false, "Please enter only one character");
        }

        char character = trimmedInput.charAt(0);
        if (!Character.isLetter(character)) {
            return new ValidationResponse(false, "Please enter a valid letter");
        }

        return new ValidationResponse(true, String.valueOf(character));
    }

    public record ValidationResponse(boolean isValid, String message) {
    }
}
