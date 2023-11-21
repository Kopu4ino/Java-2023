package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Task2 {

    private Task2() {
    }

    public static void cloneFile(Path path) throws IOException {

        if (!Files.exists(path)) {
            throw new IOException("Файл не существует: " + path);
        }

        String fileName = path.getFileName().toString();

        int dotIndex = fileName.lastIndexOf('.');
        String baseFileName = fileName.substring(0, dotIndex);
        String extension = fileName.substring(dotIndex);

        Path directory = path.getParent();
        Path newPath;
        int counter = 1;

        do {
            String copyMarkString = " — копия";
            String newName;

            if (counter == 1) {
                newName = baseFileName + copyMarkString + extension;
            } else {
                newName = baseFileName + copyMarkString + " (" + counter + ")" + extension;
            }
            newPath = directory.resolve(newName);
            counter++;
        } while (Files.exists(newPath));

        Files.copy(path, newPath, StandardCopyOption.REPLACE_EXISTING);
    }
}
