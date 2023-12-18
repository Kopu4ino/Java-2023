package edu.hw8.Task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedThreadPool implements ThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] threads;
    private final int milSecToWait = 100;
    private final static Logger LOGGER = LogManager.getLogger();

    public FixedThreadPool(int threadCount) {
        taskQueue = new ArrayBlockingQueue<>(threadCount);
        threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Worker();
            threads[i].start();
        }
    }

    public static FixedThreadPool create(int threadCount) {
        return new FixedThreadPool(threadCount);
    }

    @Override
    public void start() {
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            LOGGER.error("Возникло прерывание во время назначения задачи", e);
        }
    }

    @Override
    public void close() throws Exception {
        for (Thread thread : threads) {
            thread.join(milSecToWait);
        }
    }

    private class Worker extends Thread {
        public void run() {
            while (true) {
                Runnable task = null;
                try {
                    task = taskQueue.take();
                } catch (InterruptedException e) {
                    LOGGER.error("Возникло прерывание во время ожидания задачи", e);
                    throw new RuntimeException(e);
                }

                task.run();
            }
        }
    }
}
