package edu.hw10.Task1.ForTests;

import edu.hw10.Task1.Annotations.Min;

public class TestFactoryClass {
    private int number;
    private String text;

    private TestFactoryClass(int number, String text) {
        this.number = number;
        this.text = text;
    }

    public static TestFactoryClass create(@Min(10) int number, String text) {
        return new TestFactoryClass(number, text);
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }
}
