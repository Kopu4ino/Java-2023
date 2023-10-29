package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class ConsoleHangman {
    private static final String EXIT_COMMAND = "exit";
    private static final int MAX_ATTEMPTS = 5;
    private static final Logger LOGGER = LogManager.getLogger(ConsoleHangman.class);
    private final WordDictionary dictionary;
    private final Scanner scanner;

    public ConsoleHangman(WordDictionary dictionary) {
        this.dictionary = dictionary;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        LOGGER.info("Welcome to Hangman!");
        while (true) {
            String wordToGuess = dictionary.getWord();
            Session session = new Session(wordToGuess, MAX_ATTEMPTS);

            while (true) {
                LOGGER.info("Guess a letter: ");
                String input = scanner.nextLine().trim().toLowerCase();
                InputValidator.ValidationResponse validationResponse = InputValidator.validate(input);

                if (input.equals(EXIT_COMMAND)) {
                    GuessResult result = session.giveUp();
                    printState(result);
                    break;
                } else if (validationResponse.isValid()) {
                    char guess = input.charAt(0);
                    GuessResult result = session.guess(guess);
                    printState(result);

                    if (result instanceof GuessResult.Defeat || result instanceof GuessResult.Win) {
                        break;
                    }
                } else {
                    LOGGER.warn(validationResponse.message());
                }
            }

            LOGGER.info("Do you want to play again? (yes/no)");
            if (!scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                break;
            }
        }
        LOGGER.info("Thanks for playing!");
    }

    private void printState(@NotNull GuessResult guess) {
        LOGGER.info("{}", guess.message());
        LOGGER.info("The word: {}", new String(guess.state()));
        LOGGER.info("Attempts: {} out of {}", guess.attempt(), guess.maxAttempts());
    }

}
