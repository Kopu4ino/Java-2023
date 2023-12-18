package edu.project2;

import java.util.List;

public class ConsoleRenderer implements Renderer {

    private static final String VERTICAL_WALL_CHAR = "|";
    private static final String PASSAGE_CHAR = " ";
    private static final String PATH_CHAR = "*";

    @Override
    public String render(Maze maze) {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                if (col < maze.getWidth() - 1) {
                    builder.append(maze.isWall(row, col) ? VERTICAL_WALL_CHAR : PASSAGE_CHAR);
                    builder.append(
                        maze.isWall(row, col) && maze.isWall(row, col + 1) ? VERTICAL_WALL_CHAR : PASSAGE_CHAR);
                }
            }
            builder.append(VERTICAL_WALL_CHAR).append('\n');
        }
        return builder.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder builder = new StringBuilder();

        String[][] display = new String[maze.getHeight()][maze.getWidth()];

        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                display[row][col] = maze.isWall(row, col) ? VERTICAL_WALL_CHAR : PASSAGE_CHAR;
            }
        }

        for (Coordinate c : path) {
            if (c.row() >= 0 && c.row() < maze.getHeight() && c.col() >= 0 && c.col() < maze.getWidth()) {
                display[c.row()][c.col()] = PATH_CHAR;
            }
        }

        for (int row = 0; row < display.length; row++) {
            for (int col = 0; col < display[row].length; col++) {
                builder.append(display[row][col]);
                if (col < display[row].length - 1) {
                    builder.append(
                        maze.isWall(row, col) && maze.isWall(row, col + 1) ? VERTICAL_WALL_CHAR : PASSAGE_CHAR);
                }
            }
            builder.append('\n');
        }

        return builder.toString();
    }
}

