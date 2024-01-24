package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task2Test {

    @TempDir
    Path testDirectory;

    Path testFile;

    @BeforeEach
    void init() throws IOException {
        // Arrange
        testFile = testDirectory.resolve("Tinkoff Bank Biggest Secret.txt");
        Files.createFile(testFile);
    }

    @Test
    void testTryOneCopy() throws IOException {
        // Act
        Task2.cloneFile(testFile);

        // Assert
        Path expectPath = testDirectory.resolve("Tinkoff Bank Biggest Secret — копия.txt");
        assertThat(Files.exists(expectPath)).isTrue();
    }

    @Test
    void testTryMultipleCopies() throws IOException {
        // Act
        Task2.cloneFile(testFile);
        Task2.cloneFile(testFile);
        Task2.cloneFile(testFile);

        // Assert
        Path expected1 = testDirectory.resolve("Tinkoff Bank Biggest Secret — копия (2).txt");
        Path expected2 = testDirectory.resolve("Tinkoff Bank Biggest Secret — копия (3).txt");

        assertThat(Files.exists(expected1)).isTrue();
        assertThat(Files.exists(expected2)).isTrue();
    }

    @Test
    void shouldThrowIOExceptionForNonExistentFile() {
        // Arrange
        Path nonexistentFile = testDirectory.resolve("nonexistent.txt");

        // Act & Assert
        assertThatThrownBy(() -> Task2.cloneFile(nonexistentFile)).isInstanceOf(IOException.class);
    }
}
