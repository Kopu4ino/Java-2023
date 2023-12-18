package edu.hw8.Task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FibonacciTask implements Runnable {
    private final int n;
    private final static Logger LOGGER = LogManager.getLogger();

    public FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        try {
            long fibonacciValue = fibonacci(n);
            LOGGER.info("Число Фибоначчи на позиции " + n + ": " + fibonacciValue
                + ". Вычислено потоком: " + Thread.currentThread().getName());
        } catch (Exception e) {
            LOGGER.error("Ошибка при вычислении числа Фибоначчи для " + n
                + " в потоке " + Thread.currentThread().getName(), e);
        }
    }

    private long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        long first = 0;
        long second = 1;

        for (int i = 2; i <= n; i++) {
            long temp = second;
            second += first;
            first = temp;
        }
        return second;
    }
}
