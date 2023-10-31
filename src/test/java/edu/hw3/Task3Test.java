package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task3Test {
    @Test
    void freqDictDefaultInput() {
        List<Object> input1 = List.of("a", "bb", "a", "bb");
        List<Object> input2 = List.of(1, 1, 2, 2);
        List<Object> input3 = List.of("код", "код", "код", "bug");

        assertThat(Task3.freqDict(input1)).isEqualTo(Map.of("bb", 2, "a", 2));
        assertThat(Task3.freqDict(input2)).isEqualTo(Map.of(1, 2, 2, 2));
        assertThat(Task3.freqDict(input3)).isEqualTo(Map.of("код", 3, "bug", 1));
    }

    @Test
    void freqDictEmptyInput() {
        List<Object> emptyList = List.of();

        assertThat(Task3.freqDict(emptyList)).isEqualTo(Map.of());
    }
}
