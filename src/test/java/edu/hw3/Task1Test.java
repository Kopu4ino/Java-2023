package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {
    @Test
    void atbashDefaultInput() {
        String input1 = "Hello world!";

        String input2 = "Any fool can write code that a computer can understand. Good programmers write " +
            "code that humans can understand. ― Martin Fowler";

        String input3 = "123_123";

        assertThat(Task1.atbash(input1)).isEqualTo("Svool dliow!");

        assertThat(Task1.atbash(input2)).isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw." +
            " Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");

        assertThat(Task1.atbash(input3)).isEqualTo("123_123");
    }

    @Test
    void atbashEmptyInput() {
        String emptyInput = "";
        assertThat(Task1.atbash(emptyInput)).isEqualTo("");
    }
}
