package edu.hw10.Task1.ForTests;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;

public class TestAnnotatedPOJO {
    private int number;

    public TestAnnotatedPOJO(@Min(10) @Max(100) int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
