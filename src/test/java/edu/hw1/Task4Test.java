package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {
    @Test
    void evenStringLength() {
        String s1 = "214365";
        String s2 = "badcfehg";

        assertThat(Task4.fixString(s1)).isEqualTo("123456");
        assertThat(Task4.fixString(s2)).isEqualTo("abcdefgh");
    }

    @Test
    void oddStringLength() {
        String s1 = "21435";
        String s2 = "21436";
        String s3 = "badce";

        assertThat(Task4.fixString(s1)).isEqualTo("12345");
        assertThat(Task4.fixString(s2)).isEqualTo("12346");
        assertThat(Task4.fixString(s3)).isEqualTo("abcde");
    }

    @Test
    void oneCharInString() {
        String s1 = "1";
        String s2 = "a";

        assertThat(Task4.fixString(s1)).isEqualTo("1");
        assertThat(Task4.fixString(s2)).isEqualTo("a");
    }

    @Test
    void emptyString() {
        String s1 = "";

        assertThat(Task4.fixString(s1)).isEqualTo("");
    }

}
