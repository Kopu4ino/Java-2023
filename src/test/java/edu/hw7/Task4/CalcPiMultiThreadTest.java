package edu.hw7.Task4;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

class CalcPiMultiThreadTest {

    @Test
    void testCalculatePiMultiThread() throws InterruptedException {
        //Arrange
        int totalPoints = 1000000;

        //Act
        double pi = CalcPiMultiThread.calculatePiMultiThread(totalPoints);

        //Assert
        assertThat(pi).isCloseTo(Math.PI, within(0.01));
    }
}
