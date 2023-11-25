package edu.hw7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task2Test {

    @ParameterizedTest
    @CsvSource({
        "0, 1",
        "1, 1",
        "3, 6",
        "5, 120",
        "8, 40_320",
    })
    void testCalcFactor(int n, int expect) {
        assertThat(Task2.calcFactor(n)).isEqualTo(expect);
    }
}
