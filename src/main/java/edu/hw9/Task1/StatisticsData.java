package edu.hw9.Task1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class StatisticsData {
    private final ReentrantLock lock = new ReentrantLock();
    private double sum = 0.0;
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private AtomicLong count = new AtomicLong(0);

    public void addData(double[] values) {
        lock.lock();
        try {
            for (double value : values) {
                sum += value;
                min = Math.min(min, value);
                max = Math.max(max, value);
            }
            count.addAndGet(values.length);
        } finally {
            lock.unlock();
        }
    }

    public double getSum() {
        lock.lock();
        try {
            return sum;
        } finally {
            lock.unlock();
        }
    }

    public double getMin() {
        lock.lock();
        try {
            return min;
        } finally {
            lock.unlock();
        }
    }

    public double getMax() {
        lock.lock();
        try {
            return max;
        } finally {
            lock.unlock();
        }
    }

    public double getAverage() {
        lock.lock();
        try {
            return count.get() > 0 ? sum / count.get() : 0.0;
        } finally {
            lock.unlock();
        }
    }

    public long getCount() {
        return count.get();
    }
}
