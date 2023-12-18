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
        //Arrange
        tempFile = Files.createTempFile("diskMapTest", ".tmp");
        diskMap = new DiskMap(tempFile.toString());
    }

    @Test
    void testWriteToFile() throws IOException {
        //Act
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        List<String> lines = Files.readAllLines(tempFile);

        //Assert
        assertThat(lines).containsExactlyInAnyOrder("key1:value1", "key2:value2");
    }

    @Test
    void testPut() throws IOException {
        //Act
        diskMap.put("key1", "value1");

        //Assert
        assertThat(diskMap.get("key1")).isEqualTo("value1");
    }

    @Test
    void testRemove() throws IOException {
        //Act
        diskMap.put("key2", "value2");
        diskMap.remove("key2");

        //Assert
        assertThat(diskMap.containsKey("key2")).isFalse();
    }

    @Test
    void testSize() throws IOException {
        //Act
        diskMap.put("key3", "value3");
        diskMap.put("key4", "value4");

        //Assert
        assertThat(diskMap.size()).isEqualTo(2);
    }

}
