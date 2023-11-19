package edu.project3.Formatters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

public class FormatterUtilsTest {

    @TempDir
    Path tempDir;

    @Test
    public void testWriteToFile() throws IOException {
        String testDataToWrite = "Test data";
        Path testFile = tempDir.resolve("test.txt");

        FormatterUtils.writeToFile(testDataToWrite, testFile.toString());

        String fileContent = Files.readString(testFile);
        assertThat(fileContent).isEqualTo(testDataToWrite);
    }
}
