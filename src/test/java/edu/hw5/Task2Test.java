package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;

class Task2Test {
    @Test
    void testGetAllBlackFridayByYear() {
        //Arrange
        int inputYear1 = 2024;
        int inputYear2 = 1925;

        //Act & Assert
        assertThat(Task2.getAllBlackFridayByYear(inputYear1)).isEqualTo(
            List.of(
                LocalDate.of(inputYear1, 9, 13),
                LocalDate.of(inputYear1, 12, 13)
            )
        );

        assertThat(Task2.getAllBlackFridayByYear(inputYear2)).isEqualTo(
            List.of(
                LocalDate.of(inputYear2, 2, 13),
                LocalDate.of(inputYear2, 3, 13),
                LocalDate.of(inputYear2, 11, 13)
            )
        );
    }

    @Test
    @DisplayName("Должен найти в текущем году")
    void testGetNextBlackFridayInCurYear() {
        //Arrange
        LocalDate date1 = LocalDate.of(2024, 1, 13);
        LocalDate date2 = LocalDate.of(2024, 10, 13);

        //Act & Assert
        assertThat(Task2.getNextBlackFriday(date1)).isEqualTo(LocalDate.of(2024, 9, 13));
        assertThat(Task2.getNextBlackFriday(date2)).isEqualTo(LocalDate.of(2024, 12, 13));
    }

    @Test
    @DisplayName("Должен найти в следующем году")
    void testGetNextBlackFridayInNextYear() {
        //Arrange
        LocalDate date1 = LocalDate.of(2023, 12, 14);

        //Act & Assert
        assertThat(Task2.getNextBlackFriday(date1)).isEqualTo(LocalDate.of(2024, 9, 13));

    }
}
