package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;

public class OutputStreamChain {
    public void writeToFile(String filePath, String data) throws IOException {
        Path path = Path.of(filePath);
        Checksum checksum = new Adler32();

        try (
            OutputStream fileOutputStream = Files.newOutputStream(path);
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, checksum);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
            PrintWriter printWriter = new PrintWriter(outputStreamWriter)
        ) {
            printWriter.println(data);
        }

    }
}
