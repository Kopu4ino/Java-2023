package edu.hw10.Task2.ForTests;

public class TestInterfaceImpl implements TestInterface {
    private int callCount = 0;

    @Override
    public String methodWithCache(String input) {
        callCount++;
        return "Result: " + input;
    }

    @Override
    public int methodWithMemoryCache(int input) {
        callCount++;
        return input * input;
    }

    public int getCallCount() {
        return callCount;
    }
}
