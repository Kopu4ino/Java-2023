package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    Maze maze = new Maze(3, 3);

    @Test
    void testGetCell() {
        assertThrows(IndexOutOfBoundsException.class, () -> maze.getCell(-1, -1));
    }

    @Test
    void testSetCell() {
        assertThrows(IndexOutOfBoundsException.class, () -> maze.setCell(
            -1, -1, new Cell(0, 0, Cell.Type.PASSAGE
            )));
    }

}
