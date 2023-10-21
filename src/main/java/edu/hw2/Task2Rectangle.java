package edu.hw2;

public class Task2Rectangle {
    private final int width;
    private final int height;

    public Task2Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Task2Rectangle setWidth(int width) {
        return new Task2Rectangle(width, height);
    }

    public Task2Rectangle setHeight(int height) {
        return new Task2Rectangle(width, height);
    }

    public double area() {
        return width * height;
    }
}
