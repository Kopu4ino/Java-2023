package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task5Test {
    @Test
    void testValidateCarNumber() {
        //Arrange
        String correctNum1 = "А123ВЕ777";
        String correctNum2 = "О777ОО177";
        String correctNum3 = "Т464ОУ56";

        String incorrectNum1 = "123АВЕ777";
        String incorrectNum2 = "А123ВГ77";
        String incorrectNum3 = "А123ВЕ7777";

        //Act & Assert
        assertThat(Task5.validateCarNumber(correctNum1)).isTrue();
        assertThat(Task5.validateCarNumber(correctNum2)).isTrue();
        assertThat(Task5.validateCarNumber(correctNum3)).isTrue();

        assertThat(Task5.validateCarNumber(incorrectNum1)).isFalse();
        assertThat(Task5.validateCarNumber(incorrectNum2)).isFalse();
        assertThat(Task5.validateCarNumber(incorrectNum3)).isFalse();
    }

}
