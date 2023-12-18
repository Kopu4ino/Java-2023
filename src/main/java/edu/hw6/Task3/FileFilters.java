package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;

public class FileFilters {
    private FileFilters() {
    }

    @SuppressWarnings("ConstantName")
    public static final AbstractFilter regularFile = Files::isRegularFile;
    @SuppressWarnings("ConstantName")
    public static final AbstractFilter readable = Files::isReadable;
    @SuppressWarnings("ConstantName")
    public static final AbstractFilter writable = Files::isWritable;

    public static AbstractFilter largerThan(long size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static AbstractFilter extension(String ext) {
        return path -> path.toString().endsWith(ext);
    }

    public static AbstractFilter regexMatches(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return path -> pattern.matcher(path.getFileName().toString()).matches();
    }

    public static AbstractFilter magicNumber(byte[] magicBytes) {
        return path -> {
            try {
                byte[] fileBytes = Files.readAllBytes(path);
                if (fileBytes.length < magicBytes.length) {
                    return false;
                }
                for (int i = 0; i < magicBytes.length; i++) {
                    if (fileBytes[i] != magicBytes[i]) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }

}
