package edu.hw7;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Task1 {

    private final AtomicInteger value = new AtomicInteger(0);

    private void increment() {
        value.incrementAndGet();
    }

    public void incrementsByThreads(int threadCnt) {
        List<Thread> threads = Stream.generate(() -> new Thread(this::increment))
            .limit(threadCnt)
            .peek(Thread::start)
            .toList();

        for (Thread th : threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public AtomicInteger getValue() {
        return value;
    }
}
