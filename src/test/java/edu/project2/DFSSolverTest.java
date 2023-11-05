package edu.project2;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class DFSSolverTest {

    @Test
    public void testPathFound() {
        Maze maze = new Maze(3, 3);
        DFSSolver solver = new DFSSolver();

        maze.setCellType(1, 2, Cell.Type.PASSAGE);
        maze.setCellType(2, 2, Cell.Type.PASSAGE);
        maze.setCellType(3, 2, Cell.Type.PASSAGE);

        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);

        List<Coordinate> path = solver.solve(maze, start, end);

        assertThat(path).isEqualTo(
            List.of(
                new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(2, 2),
                new Coordinate(3, 2),
                new Coordinate(3, 3)
            )
        );
    }

    @Test
    public void testPathNotFound() {
        Maze maze = new Maze(3, 3);
        DFSSolver solver = new DFSSolver();

        // Лабиринт без выхода, все стены
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);

        List<Coordinate> path = solver.solve(maze, start, end);

        assertThat(path).isEmpty();
    }
}
