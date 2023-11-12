package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.zip.DataFormatException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class Task1Test {
    @Test
    void testGameClubAnalyticsCorrectInput() {
        List<String> input1 = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20");

        List<String> input2 = List.of(
            "2022-04-01, 21:30 - 2022-04-01, 21:30",
            "2022-04-01, 21:30 - 2022-04-01, 21:30",
            "2022-04-01, 21:30 - 2022-04-01, 21:30");

        assertThat(Task1.gameClubAnalytics(input1)).isEqualTo("3ч 40м");
        assertThat(Task1.gameClubAnalytics(input2)).isEqualTo("0ч 0м");

    }

    @Test
    void testGameClubAnalyticsIncorrectInput() {
        List<String> incorrectInput = List.of(
            "2022-03-12, 20:20 - *************",
            "************* - 2022-04-02, 01:20");
        assertThatThrownBy(() -> Task1.gameClubAnalytics(incorrectInput)).isInstanceOf(DateTimeParseException.class);
    }


}
