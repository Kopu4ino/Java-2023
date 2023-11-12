package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task7Test {
    @Test
    void testRegex1() {
        String goodSt1 = "110";
        String goodSt2 = "000";
        String goodSt3 = "10011010010";

        String badSt1 = "1";
        String badSt2 = "";
        String badSt3 = "1111111";

        assertThat(goodSt1.matches(Task7.regex1)).isTrue();
        assertThat(goodSt2.matches(Task7.regex1)).isTrue();
        assertThat(goodSt3.matches(Task7.regex1)).isTrue();

        assertThat(badSt1.matches(Task7.regex1)).isFalse();
        assertThat(badSt2.matches(Task7.regex1)).isFalse();
        assertThat(badSt3.matches(Task7.regex1)).isFalse();
    }

    @Test
    void testRegex2() {
        String goodSt1 = "1";
        String goodSt2 = "101001";
        String goodSt3 = "0110";

        String badSt1 = "110";
        String badSt2 = "00001";

        assertThat(goodSt1.matches(Task7.regex2)).isTrue();
        assertThat(goodSt2.matches(Task7.regex2)).isTrue();
        assertThat(goodSt3.matches(Task7.regex2)).isTrue();

        assertThat(badSt1.matches(Task7.regex2)).isFalse();
        assertThat(badSt2.matches(Task7.regex2)).isFalse();
    }

    @Test
    void testRegex3(){
        String goodSt1 = "110";
        String goodSt2 = "10";
        String goodSt3 = "0";

        String badSt1 = "";
        String badSt2 = "00101010";

        assertThat(goodSt1.matches(Task7.regex3)).isTrue();
        assertThat(goodSt2.matches(Task7.regex3)).isTrue();
        assertThat(goodSt3.matches(Task7.regex3)).isTrue();

        assertThat(badSt1.matches(Task7.regex3)).isFalse();
        assertThat(badSt2.matches(Task7.regex3)).isFalse();
    }
}
