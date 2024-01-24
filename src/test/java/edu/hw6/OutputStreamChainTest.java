package edu.hw6;

import edu.hw6.Task4.OutputStreamChain;
import java.io.File;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OutputStreamChainTest {
    @Test
    void testWriteToFile(@TempDir Path tempDir) throws Exception {
        //Arrange
        OutputStreamChain writer = new OutputStreamChain();
        Path testFile = tempDir.resolve("test.txt");
        String testContent = "Programming is learned by writing programs. ― Brian Kernighan";

        //Act
        writer.writeToFile(testFile.toString(), testContent);
        File file = testFile.toFile();

        //Assert
        assertThat(file).exists();
        assertThat(file).hasContent(testContent);
    }

}
