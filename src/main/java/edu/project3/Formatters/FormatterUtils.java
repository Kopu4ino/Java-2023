package edu.project3.Formatters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FormatterUtils {
    private FormatterUtils() {
    }

    public static void writeToFile(String data, String filePath) throws IOException {
        Path path = Path.of(filePath);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Files.writeString(path, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
