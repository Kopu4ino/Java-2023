package edu.hw10.Task2.ForTests;

import edu.hw10.Task2.Cache;

public interface TestInterface {
    @Cache(persist = true)
    String methodWithCache(String input);

    @Cache
    int methodWithMemoryCache(int input);
}
