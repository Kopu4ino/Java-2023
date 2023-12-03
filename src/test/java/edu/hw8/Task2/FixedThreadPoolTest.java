package edu.hw8.Task2;

import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;

class FixedThreadPoolTest {
    @Test
    void testFibTask() throws Exception {
        ThreadPool pool = new FixedThreadPool(5);

        for (int i = 1; i <= 50; i++) {
            pool.execute(new FibonacciTask(i));
        }

        pool.close();
    }

}
