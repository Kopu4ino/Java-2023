package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Пустой массив")
    void emptyArr() {
        int[] emptyArr = {};
        int[] arr = {1, 2, 3};
        assertThat(Task3.isNestable(emptyArr, arr)).isEqualTo(true);
        assertThat(Task3.isNestable(arr, emptyArr)).isEqualTo(false);
    }

    @Test
    @DisplayName("True: Можно вложить")
    void nestable() {
        assertThat(Task3.isNestable(new int[] {1, 2, 3}, new int[] {0, 10})).isEqualTo(true);
        assertThat(Task3.isNestable(new int[] {1}, new int[] {0, 10})).isEqualTo(true);
        assertThat(Task3.isNestable(new int[] {3, 1, 2}, new int[] {0, 10})).isEqualTo(true);
        assertThat(Task3.isNestable(new int[] {3, 1, 2}, new int[] {10, 0})).isEqualTo(true);
    }

    @Test
    @DisplayName("False: Нельзя вложить")
    void notNestable() {
        assertThat(Task3.isNestable(new int[] {10, 2, 3}, new int[] {0, 10})).isEqualTo(false);
        assertThat(Task3.isNestable(new int[] {20}, new int[] {0, 10})).isEqualTo(false);
        assertThat(Task3.isNestable(new int[] {3, 15, 2}, new int[] {0, 10})).isEqualTo(false);
        assertThat(Task3.isNestable(new int[] {3, 1, -2}, new int[] {10, 0})).isEqualTo(false);
    }

    @Test
    @DisplayName("Из Тз")
    void defaultTest() {
        assertThat(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6})).isEqualTo(true);
        assertThat(Task3.isNestable(new int[] {3, 1}, new int[] {4, 0})).isEqualTo(true);
        assertThat(Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9})).isEqualTo(false);
        assertThat(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3})).isEqualTo(false);
    }
}
