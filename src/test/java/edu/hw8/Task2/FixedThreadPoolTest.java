package edu.hw8.Task2;

import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;

class FixedThreadPoolTest {
    @Test
    void testFibTask() throws Exception {
        //Arrange
        ThreadPool pool = new FixedThreadPool(5);

        //Act
        for (int i = 1; i <= 50; i++) {
            pool.execute(new FibonacciTask(i));
        }
        pool.close();

        //Assert - видно в логере
    }

}
