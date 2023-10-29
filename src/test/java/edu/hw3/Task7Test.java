package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task7Test {
    @Test
    void TryAddNull() {
        Task7.tree.put(null, "null");
        assertThat(Task7.tree.containsKey(null)).isTrue();
    }
}
