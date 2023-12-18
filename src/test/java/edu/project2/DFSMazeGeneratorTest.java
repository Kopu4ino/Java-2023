package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DFSMazeGeneratorTest {
    @Test
    void generateTest() {
        DFSMazeGenerator generator = new DFSMazeGenerator();
        assertThrows(IllegalArgumentException.class, () -> generator.generate(-1, -1));
    }
}
