package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public class FilePredicates {
    private FilePredicates() {
    }

    public static Predicate<Path> sizePredicate(long minSize) {
        return path -> {
            try {
                return Files.size(path) >= minSize;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static Predicate<Path> extensionPredicate(String extension) {
        return path -> path.getFileName() != null && path.getFileName().toString().endsWith(extension);
    }
}
