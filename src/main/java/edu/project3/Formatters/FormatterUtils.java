package edu.project3.Formatters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FormatterUtils {
    private FormatterUtils() {
    }

    @SuppressWarnings("MagicNumber")
    public static String getStatusName(int statusCode) {
        return switch (statusCode) {
            case 200 -> "OK (Успешно)";
            case 206 -> "Partial Content (Частичное содержимое)";
            case 304 -> "Not Modified (Не изменено)";
            case 403 -> "Forbidden (Запрещено)";
            case 404 -> "Not Found (Не найдено)";
            case 416 -> "Requested Range Not Satisfiable (Запрошенный диапазон не достижим)";
            default -> "Unknown (Неизвестно)";
        };
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
