package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class Task4Test {
    @Test
    void convertToRomanCorrectInput() {
        Integer input1 = 2; String expect1 = "II";
        Integer input2 = 16; String expect2 = "XVI";
        Integer input3 = 399; String expect3 = "CCCXCIX";

        assertThat(Task4.convertToRoman(input1)).isEqualTo(expect1);
        assertThat(Task4.convertToRoman(input2)).isEqualTo(expect2);
        assertThat(Task4.convertToRoman(input3)).isEqualTo(expect3);
    }

    @Test
    void convertToRomanIncorrectInput() {
        Integer input1 = -10;
        Integer input2 = 10000;

        assertThatThrownBy(() ->Task4.convertToRoman(input1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() ->Task4.convertToRoman(input2)).isInstanceOf(IllegalArgumentException.class);
    }

}
