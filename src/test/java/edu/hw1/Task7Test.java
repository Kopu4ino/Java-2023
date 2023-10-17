package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task7Test {
    @Test
    void testRotateLeft() {
        Integer num1 = 16; Integer shift1 = 1;
        Integer num2 = 17; Integer shift2 = 2;
        Integer num3 = 2; Integer shift3 = 1;
        Integer num4 = 2; Integer shift4 = 0;

        assertThat(Task7.rotateLeft(num1, shift1)).isEqualTo(1);
        assertThat(Task7.rotateLeft(num2, shift2)).isEqualTo(6);
        assertThat(Task7.rotateLeft(num3, shift3)).isEqualTo(1);
        assertThat(Task7.rotateLeft(num4, shift4)).isEqualTo(2);
    }

    @Test
    void testRotateRight() {
        Integer num1 = 8; Integer shift1 = 0;
        Integer num2 = 8; Integer shift2 = 2;
        Integer num3 = 32; Integer shift3 = 3;
        Integer num4 = 32; Integer shift4 = -2;
        Integer num5 = 14; Integer shift5 = 2;

        assertThat(Task7.rotateRight(num1, shift1)).isEqualTo(8);
        assertThat(Task7.rotateRight(num2, shift2)).isEqualTo(2);
        assertThat(Task7.rotateRight(num3, shift3)).isEqualTo(4);
        assertThat(Task7.rotateRight(num4, shift4)).isEqualTo(2);
        assertThat(Task7.rotateRight(num5, shift5)).isEqualTo(11);
    }
}
