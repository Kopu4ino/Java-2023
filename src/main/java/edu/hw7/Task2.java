package edu.hw7;

import java.util.stream.IntStream;

public class Task2 {
    private Task2() {
    }

    public static int calcFactor(int n) {
        final int START = 1;
        final int END = n + 1;

        return IntStream.range(START, END)
            .parallel()
            .reduce(START, (left, right) -> left * right);
    }
}
