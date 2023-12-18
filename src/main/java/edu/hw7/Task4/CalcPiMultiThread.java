package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class CalcPiMultiThread {
    private CalcPiMultiThread() {
    }

    public static double calculatePiMultiThread(int totalPoints) throws InterruptedException {
        final double MONTE_CONST = 4;
        int threadCount = Runtime.getRuntime().availableProcessors();
        AtomicLong circlePoints = new AtomicLong(0);

        Thread[] threads = new Thread[threadCount];
        int pointsPerThread = totalPoints / threadCount;

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < pointsPerThread; j++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();
                    if (x * x + y * y <= 1) {
                        circlePoints.incrementAndGet();
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return MONTE_CONST * circlePoints.get() / totalPoints;
    }
}

