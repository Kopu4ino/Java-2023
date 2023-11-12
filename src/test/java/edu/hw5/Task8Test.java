package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task8Test {
    @Test
    void testOddLength() {
        String input1 = "1";
        String input2 = "101";
        String input3 = "1010000";

        String badInput1 = "";
        String badInput2 = "11";
        String badInput3 = "1000";

        assertThat(input1.matches(Task8.oddLength)).isTrue();
        assertThat(input2.matches(Task8.oddLength)).isTrue();
        assertThat(input3.matches(Task8.oddLength)).isTrue();

        assertThat(badInput1.matches(Task8.oddLength)).isFalse();
        assertThat(badInput2.matches(Task8.oddLength)).isFalse();
        assertThat(badInput3.matches(Task8.oddLength)).isFalse();
    }

    @Test
    @DisplayName("Четное количество нулей в строке")
    void testEvenCntZeros() {
        String input1 = "1"; // 0 - крастно 3
        String input2 = "1001010010"; // 6 нулей
        String input3 = "000";

        String badInput1 = "1010100";
        String badInput2 = "10";

        assertThat(input1.matches(Task8.evenCntZeros)).isTrue();
        assertThat(input2.matches(Task8.evenCntZeros)).isTrue();
        assertThat(input3.matches(Task8.evenCntZeros)).isTrue();

        assertThat(badInput1.matches(Task8.evenCntZeros)).isFalse();
        assertThat(badInput2.matches(Task8.evenCntZeros)).isFalse();
    }

    @Test
    void everyOddCharIsOne() {
        String input1 = "1010111";
        String input2 = "101";
        String input3 = "11";

        String badInput1 = "001";
        String badInput2 = "0";

        assertThat(input1.matches(Task8.everyOddCharIsOne)).isTrue();
        assertThat(input2.matches(Task8.everyOddCharIsOne)).isTrue();
        assertThat(input3.matches(Task8.everyOddCharIsOne)).isTrue();

        assertThat(badInput1.matches(Task8.everyOddCharIsOne)).isFalse();
        assertThat(badInput2.matches(Task8.everyOddCharIsOne)).isFalse();
    }

    @Test
    @DisplayName("начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину")
    void testZeroOrOne() {
        String input1 = "010";
        String input2 = "0";
        String input3 = "00000";

        String input4 = "11";
        String input5 = "1001";
        String input6 = "1100";

        String badInput1 = "110";
        String badInput2 = "01";

        assertThat(input1.matches(Task8.zeroOrOne)).isTrue();
        assertThat(input2.matches(Task8.zeroOrOne)).isTrue();
        assertThat(input3.matches(Task8.zeroOrOne)).isTrue();
        assertThat(input4.matches(Task8.zeroOrOne)).isTrue();
        assertThat(input5.matches(Task8.zeroOrOne)).isTrue();
        assertThat(input6.matches(Task8.zeroOrOne)).isTrue();

        assertThat(badInput1.matches(Task8.zeroOrOne)).isFalse();
        assertThat(badInput2.matches(Task8.zeroOrOne)).isFalse();
    }
}
