package edu.hw7.Task4;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

class CalcPiMultiThreadTest {

    @Test
    void testCalculatePiMultiThread() throws InterruptedException {
        int totalPoints = 1000000;

        double pi = CalcPiMultiThread.calculatePiMultiThread(totalPoints);

        assertThat(pi).isCloseTo(Math.PI, within(0.01));
    }
}
