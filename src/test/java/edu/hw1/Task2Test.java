package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Количество цифр(положительные числа)")
    void positiveNumbers() {
        long num1 = 0;
        long num2 = 1;
        long num3 = 123;
        long num4 = 400;
        long num5 = 123000000;

        assertThat(Task2.digitsCnt(num1)).isEqualTo(1);
        assertThat(Task2.digitsCnt(num2)).isEqualTo(1);
        assertThat(Task2.digitsCnt(num3)).isEqualTo(3);
        assertThat(Task2.digitsCnt(num4)).isEqualTo(3);
        assertThat(Task2.digitsCnt(num5)).isEqualTo(9);
    }

    @Test
    @DisplayName("Количество цифр(отрицательные числа)")
    void negativeNumbers() {
        long num1 = -1;
        long num2 = -123;
        long num3 = -200;
        long num4 = -123000000;

        assertThat(Task2.digitsCnt(num1)).isEqualTo(1);
        assertThat(Task2.digitsCnt(num2)).isEqualTo(3);
        assertThat(Task2.digitsCnt(num3)).isEqualTo(3);
        assertThat(Task2.digitsCnt(num4)).isEqualTo(9);
    }
}
