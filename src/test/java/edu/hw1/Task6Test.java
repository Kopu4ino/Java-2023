package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task6Test {
    @Test
    void kaprekarStepCntCorrectInput() {
        Integer num1 = 6621;
        Integer num2 = 6554;
        Integer num3 = 1234;

        assertThat(Task6.countK(num1)).isEqualTo(5);
        assertThat(Task6.countK(num2)).isEqualTo(4);
        assertThat(Task6.countK(num3)).isEqualTo(3);
    }

    @Test
    void kaprekarStepCntIncorrectInput() {
        Integer num1 = 2;
        Integer num2 = 10101001;
        Integer num3 = 7777;

        assertThat(Task6.countK(num1)).isEqualTo(-1);
        assertThat(Task6.countK(num2)).isEqualTo(-1);
        assertThat(Task6.countK(num3)).isEqualTo(-1);
    }

    @Test
    void testAscOrder() {
        Integer num1 = 1001;
        Integer num2 = 2222;
        Integer num3 = 9899;

        assertThat(Task6.getNumInAscOrder(num1)).isEqualTo(1100);
        assertThat(Task6.getNumInAscOrder(num2)).isEqualTo(2222);
        assertThat(Task6.getNumInAscOrder(num3)).isEqualTo(9998);
    }

    @Test
    void testDescOrder() {
        Integer num1 = 1001;
        Integer num2 = 2222;
        Integer num3 = 9899;

        assertThat(Task6.getNumInDescOrder(num1)).isEqualTo(11);
        assertThat(Task6.getNumInDescOrder(num2)).isEqualTo(2222);
        assertThat(Task6.getNumInDescOrder(num3)).isEqualTo(8999);
    }

}
