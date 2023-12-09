package edu.project3.LogReaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LocalLogFileReaderTest {

    @TempDir
    Path tempDir;

    @Test
    public void testReadLinesFromRegularFile() throws IOException {
        LocalLogFileReader reader = new LocalLogFileReader();

        Path testFile = tempDir.resolve("test.log");
        String testContent = "Test line 1\nTest line 2\n";
        Files.writeString(testFile, testContent);

        List<String> lines = reader.readLines(testFile.toString()).toList();

        assertThat(lines).containsExactly("Test line 1", "Test line 2");
    }

    @Test
    public void testReadLinesFromDirectory() throws IOException {
        LocalLogFileReader reader = new LocalLogFileReader();

        Files.writeString(tempDir.resolve("file1.log"), "Line 1\nLine 2\n");
        Files.writeString(tempDir.resolve("file2.log"), "Line 3\nLine 4\n");

        List<String> lines = reader.readLines(tempDir.toString()).toList();

        assertThat(lines).containsExactlyInAnyOrder("Line 1", "Line 2", "Line 3", "Line 4");
    }

    @Test
    public void testReadLinesInvalidPath() {
        LocalLogFileReader reader = new LocalLogFileReader();
        String invalidPath = "nonexistent/path";

        assertThatThrownBy(() -> reader.readLines(invalidPath))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid path");
    }
}
