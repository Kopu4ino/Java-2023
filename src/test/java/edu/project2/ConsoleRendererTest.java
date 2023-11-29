package edu.project2;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class ConsoleRendererTest {

    @Test
    public void testRenderKnownMaze() {
        // Создаем заранее известный лабиринт
        Maze maze = new Maze(3, 3);
        maze.setCellType(1, 2, Cell.Type.PASSAGE);
        maze.setCellType(2, 2, Cell.Type.PASSAGE);

        String expectedRendering =
            """
                |||||||||||||
                |       |   |
                |||   |||||||
                |   |   |   |
                |||||||||||||
                |   |   |   |
                |||||||||||||
                    """;

        ConsoleRenderer renderer = new ConsoleRenderer();
        String actualRendering = renderer.render(maze);

        assertThat(actualRendering).isEqualTo(expectedRendering);
    }

    @Test
    public void testRenderMazeWithPath() {
        // Создаем заранее известный лабиринт и путь
        Maze maze = new Maze(3, 3);
        maze.setCellType(1, 2, Cell.Type.PASSAGE);
        maze.setCellType(2, 2, Cell.Type.PASSAGE);

        List<Coordinate> path = List.of(
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(2, 2),
            new Coordinate(3, 2)
        );

        String expectedRenderingWithoutColor =
            """
                |||||||||||||
                | * *   |   |
                ||| * |||||||
                |   *   |   |
                |||||||||||||
                |   |   |   |
                |||||||||||||
                    """;

        ConsoleRenderer renderer = new ConsoleRenderer();
        String actualRendering = renderer.render(maze, path);

        assertThat(actualRendering).isEqualTo(expectedRenderingWithoutColor);
    }
}
