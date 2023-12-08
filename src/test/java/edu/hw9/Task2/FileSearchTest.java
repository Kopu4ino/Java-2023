package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FileSearchTest {

    @TempDir
    Path tempDir;

    @Test
    public void testFileSearchTasks() throws IOException {
        createTestFiles(tempDir);

        ForkJoinPool forkJoinPool = new ForkJoinPool(3);

        // Тестирование DirectorySearchTask
        DirectorySearchTask dirSearchTask = new DirectorySearchTask(tempDir, 2);
        List<Path> directories = forkJoinPool.invoke(dirSearchTask);
        Assertions.assertThat(directories).containsExactly(tempDir);


        // Тестирование FileSearchTask
        FileSearchTask fileSearchTask = new FileSearchTask(tempDir, FilePredicates.extensionPredicate(".txt"));
        List<Path> txtFiles = forkJoinPool.invoke(fileSearchTask);
        Assertions.assertThat(txtFiles).hasSize(2).allMatch(path -> path.toString().endsWith(".txt"));
    }

    private void createTestFiles(Path dir) throws IOException {
        Files.createFile(dir.resolve("test1.txt"));
        Files.createFile(dir.resolve("test10.mp4"));
        Files.createFile(dir.resolve("test100.png"));

        Files.createDirectory(dir.resolve("subdir1"));
        Files.createDirectory(dir.resolve("subdir2"));

        Files.createFile(dir.resolve("subdir1/test2.txt"));
        Files.createFile(dir.resolve("subdir2/test3.mp3"));
    }
}
