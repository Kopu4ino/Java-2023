package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task6Test {
    @Test
    void testContainsSubString() {
    String text1 = "achfdbaabgabcaabg"; String st1 = "abc";
    String text2 = "4"; String st2 = "4";

    String text3 = "********"; String st3 = "1231";

    assertThat(Task6.containsSubString(text1, st1)).isTrue();
    assertThat(Task6.containsSubString(text2, st2)).isTrue();

    assertThat(Task6.containsSubString(text3, st3)).isFalse();
}
}
