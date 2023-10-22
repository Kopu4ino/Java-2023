package edu.project1;

import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult
    permits GuessResult.Defeat, GuessResult.Win, GuessResult.SuccessfulGuess, GuessResult.FailedGuess {
    char[] state();

    int attempt();

    int maxAttempts();

    @NotNull String message();

    record Defeat(char[] state, int attempt, int maxAttempts) implements GuessResult {
        public @NotNull String message() {
            return "You lost!";
        }
    }

    record Win(char[] state, int attempt, int maxAttempts) implements GuessResult {
        public @NotNull String message() {
            return "You won!";
        }
    }

    record SuccessfulGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        public @NotNull String message() {
            return "Correct! Keep going!";
        }
    }

    record FailedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
        public @NotNull String message() {
            return "Incorrect! You have " + (maxAttempts - attempt) + " attempts left.";
        }
    }
}
