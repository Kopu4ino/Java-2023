package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task8Test {
    @Test
    void testOddLength() {
        //Arrange
        String input1 = "1";
        String input2 = "101";
        String input3 = "1010000";

        String badInput1 = "";
        String badInput2 = "11";
        String badInput3 = "1000";

        //Act & Assert
        assertThat(input1.matches(Task8.ODD_LENGTH)).isTrue();
        assertThat(input2.matches(Task8.ODD_LENGTH)).isTrue();
        assertThat(input3.matches(Task8.ODD_LENGTH)).isTrue();

        assertThat(badInput1.matches(Task8.ODD_LENGTH)).isFalse();
        assertThat(badInput2.matches(Task8.ODD_LENGTH)).isFalse();
        assertThat(badInput3.matches(Task8.ODD_LENGTH)).isFalse();
    }

    @Test
    @DisplayName("Четное количество нулей в строке")
    void testEvenCntZeros() {
        //Arrange
        String input1 = "1"; // 0 - крастно 3
        String input2 = "1001010010"; // 6 нулей
        String input3 = "000";

        String badInput1 = "1010100";
        String badInput2 = "10";

        //Act & Assert
        assertThat(input1.matches(Task8.EVEN_CNT_ZEROS)).isTrue();
        assertThat(input2.matches(Task8.EVEN_CNT_ZEROS)).isTrue();
        assertThat(input3.matches(Task8.EVEN_CNT_ZEROS)).isTrue();

        assertThat(badInput1.matches(Task8.EVEN_CNT_ZEROS)).isFalse();
        assertThat(badInput2.matches(Task8.EVEN_CNT_ZEROS)).isFalse();
    }

    @Test
    void everyOddCharIsOne() {
        //Arrange
        String input1 = "1010111";
        String input2 = "101";
        String input3 = "11";

        String badInput1 = "001";
        String badInput2 = "0";

        //Act & Assert
        assertThat(input1.matches(Task8.EVERY_ODD_CHAR_IS_ONE)).isTrue();
        assertThat(input2.matches(Task8.EVERY_ODD_CHAR_IS_ONE)).isTrue();
        assertThat(input3.matches(Task8.EVERY_ODD_CHAR_IS_ONE)).isTrue();

        assertThat(badInput1.matches(Task8.EVERY_ODD_CHAR_IS_ONE)).isFalse();
        assertThat(badInput2.matches(Task8.EVERY_ODD_CHAR_IS_ONE)).isFalse();
    }

    @Test
    @DisplayName("начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину")
    void testZeroOrOne() {
        //Arrange
        String input1 = "010";
        String input2 = "0";
        String input3 = "00000";

        String input4 = "11";
        String input5 = "1001";
        String input6 = "1100";

        String badInput1 = "110";
        String badInput2 = "01";

        //Act & Assert
        assertThat(input1.matches(Task8.ZERO_OR_ONE)).isTrue();
        assertThat(input2.matches(Task8.ZERO_OR_ONE)).isTrue();
        assertThat(input3.matches(Task8.ZERO_OR_ONE)).isTrue();
        assertThat(input4.matches(Task8.ZERO_OR_ONE)).isTrue();
        assertThat(input5.matches(Task8.ZERO_OR_ONE)).isTrue();
        assertThat(input6.matches(Task8.ZERO_OR_ONE)).isTrue();

        assertThat(badInput1.matches(Task8.ZERO_OR_ONE)).isFalse();
        assertThat(badInput2.matches(Task8.ZERO_OR_ONE)).isFalse();
    }
}
