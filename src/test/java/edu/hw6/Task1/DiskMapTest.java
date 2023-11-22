package edu.hw6.Task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DiskMapTest {
    private Path tempFile;
    private DiskMap diskMap;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("diskMapTest", ".tmp");
        diskMap = new DiskMap(tempFile.toString());
    }

    @Test
    void testWriteToFile() throws IOException {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        List<String> lines = Files.readAllLines(tempFile);

        assertThat(lines).containsExactlyInAnyOrder("key1:value1", "key2:value2");
    }

    @Test
    void testPut() throws IOException {
        diskMap.put("key1", "value1");
        assertThat(diskMap.get("key1")).isEqualTo("value1");
    }

    @Test
    void testRemove() throws IOException {
        diskMap.put("key2", "value2");
        diskMap.remove("key2");
        assertThat(diskMap.containsKey("key2")).isFalse();
    }

    @Test
    void testSize() throws IOException {
        diskMap.put("key3", "value3");
        diskMap.put("key4", "value4");
        assertThat(diskMap.size()).isEqualTo(2);
    }

}
