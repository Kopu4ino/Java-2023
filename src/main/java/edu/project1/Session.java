package edu.project1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    public Session(String answer, int maxAttempts) {
        this.answer = answer.toLowerCase();
        this.userAnswer = new char[answer.length()];
        Arrays.fill(this.userAnswer, '*');
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }

    @NotNull
    public GuessResult guess(char guess) {
        boolean isCorrect = false;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess) {
                userAnswer[i] = guess;
                isCorrect = true;
            }
        }

        if (!isCorrect) {
            attempts++;
        }

        if (new String(userAnswer).equals(answer)) {
            return new GuessResult.Win(userAnswer, attempts, maxAttempts);
        } else if (attempts >= maxAttempts) {
            return new GuessResult.Defeat(answer.toCharArray(), attempts, maxAttempts);
        } else {
            return isCorrect
                ? new GuessResult.SuccessfulGuess(userAnswer, attempts, maxAttempts)
                : new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts);
        }
    }

    @NotNull
    public GuessResult giveUp() {
        return new GuessResult.Defeat(answer.toCharArray(), attempts, maxAttempts);
    }
}
