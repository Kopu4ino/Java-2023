package edu.hw6.Task3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class FileFiltersTest {

    @TempDir
    Path tempDir;

    @Test
    public void testRegularFileFilter() throws IOException {
        Path file = tempDir.resolve("testfile.txt");
        Files.createFile(file);

        Assertions.assertThat(FileFilters.regularFile.accept(file)).isTrue();
        Assertions.assertThat(FileFilters.regularFile.accept(tempDir)).isFalse();
    }

    @Test
    public void testReadableFilter() throws IOException {
        Path file = tempDir.resolve("testfile.txt");
        Files.createFile(file);

        Assertions.assertThat(FileFilters.readable.accept(file)).isTrue();
    }

    @Test
    public void testWritableFilter() throws IOException {
        Path file = tempDir.resolve("testfile.txt");
        Files.createFile(file);

        Assertions.assertThat(FileFilters.writable.accept(file)).isTrue();
    }

    @Test
    public void testLargerThanFilter() throws IOException {
        Path file = tempDir.resolve("largefile.txt");
        byte[] data = new byte[1024]; // 1KB
        Files.write(file, data);

        Assertions.assertThat(FileFilters.largerThan(500).accept(file)).isTrue();
        Assertions.assertThat(FileFilters.largerThan(1500).accept(file)).isFalse();
    }

    @Test
    public void testExtensionFilter() throws IOException {
        Path file = tempDir.resolve("testfile.txt");
        Files.createFile(file);

        Assertions.assertThat(FileFilters.extension(".txt").accept(file)).isTrue();
        Assertions.assertThat(FileFilters.extension(".md").accept(file)).isFalse();
    }

    @Test
    public void testRegexMatchesFilter() throws IOException {
        Path file = tempDir.resolve("testfile-123.txt");
        Files.createFile(file);

        Assertions.assertThat(FileFilters.regexMatches(".*-\\d+\\.txt").accept(file)).isTrue();
        Assertions.assertThat(FileFilters.regexMatches(".*\\.md").accept(file)).isFalse();
    }

    @Test
    public void testMagicNumberFilter() throws IOException {
        byte[] pngMagic = {(byte) 0x89, 'P', 'N', 'G', 0x0D, 0x0A, 0x1A, 0x0A};
        Path pngFile = tempDir.resolve("testimage.png");
        Files.write(pngFile, pngMagic);

        Path otherFile = tempDir.resolve("testfile.txt");
        Files.createFile(otherFile);

        AbstractFilter pngFilter = FileFilters.magicNumber(pngMagic);

        Assertions.assertThat(pngFilter.accept(pngFile)).isTrue();
        Assertions.assertThat(pngFilter.accept(otherFile)).isFalse();
    }

    @Test
    public void testFiltersChain() throws IOException {
        Path file = tempDir.resolve("testfile-100.txt");
        byte[] data = new byte[100];
        Files.write(file, data);

        Path otherFile = tempDir.resolve("otherfile.md");
        Files.createFile(otherFile);

        AbstractFilter chain = FileFilters.regularFile
            .and(FileFilters.readable)
            .and(FileFilters.writable)
            .and(FileFilters.largerThan(50))
            .and(FileFilters.extension(".txt"))
            .and(FileFilters.regexMatches("testfile-.*\\.txt"));

        Assertions.assertThat(chain.accept(file)).isTrue();

        Assertions.assertThat(chain.accept(otherFile)).isFalse();
    }
}
