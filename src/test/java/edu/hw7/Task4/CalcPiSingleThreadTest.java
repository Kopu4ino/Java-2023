package edu.hw7.Task4;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import org.junit.jupiter.api.Test;

class CalcPiSingleThreadTest {

    @Test
    void testCalculatePi() {
        //Arrange
        int totalPoints = 1000000;

        //Act
        double pi = CalcPiSingleThread.calculatePi(totalPoints);

        //Assert
        assertThat(pi).isCloseTo(Math.PI, within(0.01));
    }
}
