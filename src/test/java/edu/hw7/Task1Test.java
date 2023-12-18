package edu.hw7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 20})
    void testIncrementsByThreads(int threadCnt) {
        //Arrange
        var atomicTest = new Task1();

        //Act
        atomicTest.incrementsByThreads(threadCnt);

        //Assert
        assertThat(atomicTest.getValue().get()).isEqualTo((threadCnt));
    }

    @Test
    void test() {
        //Arrange
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);

        //Act
        int sum = listOfNumbers.parallelStream().reduce(0, Integer::sum) + 5;

        //Assert
        assertThat(sum).isEqualTo(15);
    }

}
