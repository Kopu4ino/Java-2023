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
        var atomicTest = new Task1();

        atomicTest.incrementsByThreads(threadCnt);

        assertThat(atomicTest.getValue().get()).isEqualTo((threadCnt));
    }

    @Test
    void test() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        int sum = listOfNumbers.parallelStream().reduce(0, Integer::sum) + 5;
        assertThat(sum).isEqualTo(15);
    }

}
