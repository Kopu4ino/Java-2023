package edu.hw7.Task4;

import java.util.Random;

public class CalcPiSingleThread {
    private CalcPiSingleThread() {
    }

    public static double calculatePi(int totalPoints) {
        final double MONTE_CONST = 4;
        int circlePoints = 0;
        Random random = new Random();

        for (int i = 0; i < totalPoints; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            if (x * x + y * y <= 1) {
                circlePoints++;
            }
        }

        return MONTE_CONST * circlePoints / totalPoints;
    }
}
