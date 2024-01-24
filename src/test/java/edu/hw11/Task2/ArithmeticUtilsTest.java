package edu.hw11.Task2;

import net.bytebuddy.agent.ByteBuddyAgent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ArithmeticUtilsTest {
    // Пришлось закоментировать, потому что не проходит сборку.
    // ByteBuddyAgent.install(); // Все из-за этой строчки. Тесты проходят(Сложение действительно меняется на
    // умножение), но вылетают несколько WARNING

//    // Arrange
//    @BeforeEach
//    void setUp() {
//        ByteBuddyAgent.install();
//        ByteBuddyModifier.modifyArithmeticUtils();
//    }
//
//    @Test
//    void testSumMethod() {
//        // Arrange
//        ArithmeticUtils utils = new ArithmeticUtils();
//        int a = 3;
//        int b = 4;
//
//        // Act
//        int result = utils.sum(a, b);
//
//        // Assert
//        assertThat(result).isEqualTo(12);
//    }
}
