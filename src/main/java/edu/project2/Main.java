package edu.project2;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        final int width = 15;
        final int height = 3;

        DFSMazeGenerator generator2 = new DFSMazeGenerator();
        Maze maze = generator2.generate(height, width);

        Solver solver = new DFSSolver();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(maze.getHeight() - 2, maze.getWidth() - 2);
        List<Coordinate> path = solver.solve(maze, start, end);

        ConsoleRenderer render = new ConsoleRenderer();

        LOGGER.info("\n" + render.render(maze));
        LOGGER.info("\n" + render.render(maze, path));
    }
}
