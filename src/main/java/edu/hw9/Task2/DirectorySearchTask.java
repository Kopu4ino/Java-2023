package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DirectorySearchTask extends RecursiveTask<List<Path>> {
    private final static Logger LOGGER = LogManager.getLogger();
    private final Path directory;
    private final int fileThreshold;

    public DirectorySearchTask(Path directory, int fileThreshold) {
        this.directory = directory;
        this.fileThreshold = fileThreshold;
    }

    @Override
    protected List<Path> compute() {
        List<DirectorySearchTask> tasks = new ArrayList<>();
        List<Path> directories = new ArrayList<>();

        LOGGER.info("Работаем в потоке: " + Thread.currentThread().getName());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    DirectorySearchTask task = new DirectorySearchTask(entry, fileThreshold);
                    task.fork();
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Ошибка при чтении директории: " + directory, e);
        }

        long filesCount = countFilesInDirectory(directory);
        if (filesCount > fileThreshold) {
            directories.add(directory);
        }

        for (DirectorySearchTask task : tasks) {
            directories.addAll(task.join());
        }

        return directories;
    }

    private long countFilesInDirectory(Path directory) {
        try {
            return Files.list(directory).filter(Files::isRegularFile).count();
        } catch (IOException e) {
            LOGGER.error("Ошибка при подсчете файлов в директории: " + directory, e);
            return 0;
        }
    }
}
