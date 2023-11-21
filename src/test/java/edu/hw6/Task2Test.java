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
        testFile = testDirectory.resolve("Tinkoff Bank Biggest Secret.txt");
        Files.createFile(testFile);
    }

    @Test
    void testTryOneCopy() throws IOException {
        Task2.cloneFile(testFile);

        Path expectPath = testDirectory.resolve("Tinkoff Bank Biggest Secret — копия.txt");
        assertThat(Files.exists(expectPath)).isTrue();
    }

    @Test
    void testTryMultipleCopies() throws IOException {
        Task2.cloneFile(testFile);
        Task2.cloneFile(testFile);
        Task2.cloneFile(testFile);

        Path expected1 = testDirectory.resolve("Tinkoff Bank Biggest Secret — копия (2).txt");
        Path expected2 = testDirectory.resolve("Tinkoff Bank Biggest Secret — копия (3).txt");

        assertThat(Files.exists(expected1)).isTrue();
        assertThat(Files.exists(expected2)).isTrue();
    }

    @Test
    void shouldThrowIOExceptionForNonExistentFile() {
        Path nonexistentFile = testDirectory.resolve("nonexistent.txt");
        assertThatThrownBy(() -> Task2.cloneFile(nonexistentFile)).isInstanceOf(IOException.class);
    }
}
