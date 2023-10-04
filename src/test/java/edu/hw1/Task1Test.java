package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Длина в секундах(хороший ввод)")
    void inputValidDuration() {
        String dur1 = "01:00";
        String dur2 = "13:56";
        String dur3 = "200:10";
        String dur4 = "200:15";

        assertThat(Task1.getSecDuration(dur1)).isEqualTo(60);
        assertThat(Task1.getSecDuration(dur2)).isEqualTo(836);
        assertThat(Task1.getSecDuration(dur3)).isEqualTo(12010);
        assertThat(Task1.getSecDuration(dur4)).isEqualTo(12015);
    }

    @Test
    @DisplayName("Длина в секундах(плохой ввод)")
    void inputNotValidDuration() {
        String dur1 = "10:60";
        String dur2 = "100:100";
        String dur3 = "10:60:10";
        String dur4 = "abc:abc";
        String dur5 = "-10:10";

        assertThat(Task1.getSecDuration(dur1)).isEqualTo(-1);
        assertThat(Task1.getSecDuration(dur2)).isEqualTo(-1);
        assertThat(Task1.getSecDuration(dur3)).isEqualTo(-1);
        assertThat(Task1.getSecDuration(dur4)).isEqualTo(-1);
        assertThat(Task1.getSecDuration(dur5)).isEqualTo(-1);
    }

}
