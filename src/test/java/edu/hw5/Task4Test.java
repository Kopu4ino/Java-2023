package edu.hw5;

import org.junit.jupiter.api.Test;
import java.io.StringBufferInputStream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {
    @Test
    void testValidatePassword() {
        String input1 = "QwErTY!@#";
        String input2 = "@";
        String input3 = "";
        String input4 = "12131";

        assertThat(Task4.validatePassword(input1)).isTrue();
        assertThat(Task4.validatePassword(input2)).isTrue();

        assertThat(Task4.validatePassword(input3)).isFalse();
        assertThat(Task4.validatePassword(input4)).isFalse();
    }
}
