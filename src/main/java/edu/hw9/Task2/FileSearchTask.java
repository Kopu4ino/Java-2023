package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileSearchTask extends RecursiveTask<List<Path>> {
    private final Path directory;
    private final Predicate<Path> filePredicate;
    private final static Logger LOGGER = LogManager.getLogger(FileSearchTask.class);

    public FileSearchTask(Path directory, Predicate<Path> filePredicate) {
        this.directory = directory;
        this.filePredicate = filePredicate;
    }

    @Override
    protected List<Path> compute() {
        List<FileSearchTask> tasks = new ArrayList<>();
        List<Path> matchedFiles = new ArrayList<>();

        LOGGER.info("Работаем в потоке: " + Thread.currentThread().getName());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    FileSearchTask task = new FileSearchTask(entry, filePredicate);
                    task.fork();
                    tasks.add(task);
                } else if (filePredicate.test(entry)) {
                    matchedFiles.add(entry);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Ошибка при обработке директории: " + directory, e);
        }

        for (FileSearchTask task : tasks) {
            matchedFiles.addAll(task.join());
        }

        return matchedFiles;
    }
}
