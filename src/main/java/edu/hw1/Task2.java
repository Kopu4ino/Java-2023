package edu.hw1;

public class Task2 {
    private Task2() {
    }

    public static long digitsCnt(long num) {
        long posNum = Math.abs(num);
        long cnt = 0;
        final int DIVIDER = 10;

        if (posNum == 0) {
            cnt++;
        }

        while (posNum > 0) {
            cnt++;
            posNum /= DIVIDER;
        }

        return cnt;
    }
}
