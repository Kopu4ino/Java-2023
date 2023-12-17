package edu.project3.LogReaders;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class LocalLogFileReader {
    public Stream<String> readLines(String path) throws IOException {
        String[] pathParts = splitPathAndGlob(path);
        Path basePath = Paths.get(pathParts[0]);
        String globPattern = pathParts[1];


        if (!globPattern.isEmpty()) {
            return readFilesByPattern(basePath, "glob:" + globPattern);
        } else if (Files.isRegularFile(basePath)) {
            return Files.lines(basePath);
        } else if (Files.isDirectory(basePath)) {
            return readAllFilesInDirectory(basePath);
        }

        throw new IllegalArgumentException("Invalid path: " + path);
    }

    public String[] splitPathAndGlob(String path) {
        String changedPath = path.replaceAll("/", Matcher.quoteReplacement(File.separator));
        int globIndex = changedPath.indexOf('*');
        if (globIndex != -1) {
            String basePath = changedPath.substring(0, globIndex);
            int lastDirSeparator = basePath.lastIndexOf(File.separator);
            basePath = basePath.substring(0, lastDirSeparator);
            String globPattern = changedPath.substring(lastDirSeparator + 1);
            return new String[] {basePath, globPattern};
        } else {
            return new String[] {path, ""};
        }
    }

    private Stream<String> readFilesByPattern(Path dir, String pattern) throws IOException {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(pattern);
        return Files.walk(dir)
            .filter(Files::isRegularFile)
            .filter(path -> matcher.matches(path.getFileName()))
            .flatMap(path -> {
                try {
                    return Files.lines(path);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
    }

    private Stream<String> readAllFilesInDirectory(Path dir) throws IOException {
        return Files.walk(dir)
            .filter(Files::isRegularFile)
            .flatMap(file -> {
                try {
                    return Files.lines(file);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
    }
}
