package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DFSMazeGenerator implements Generator {

    @Override
    public Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException();
        }

        Maze maze = new Maze(height, width);
        boolean[][] visited = new boolean[height * 2 + 1][width * 2 + 1];
        Stack<Coordinate> stack = new Stack<>();

        Coordinate start = new Coordinate(1, 1);
        stack.push(start);
        visited[start.row()][start.col()] = true;

        while (!stack.isEmpty()) {
            Coordinate current = stack.pop();
            List<Coordinate> neighbours = getUnvisitedNeighbours(current, visited, height, width);

            if (!neighbours.isEmpty()) {
                stack.push(current);

                Collections.shuffle(neighbours);
                Coordinate neighbour = neighbours.get(0);

                removeWall(maze, current, neighbour);

                visited[neighbour.row()][neighbour.col()] = true;
                stack.push(neighbour);
            }
        }

        maze.setCell(0, 0, new Cell(0, 0, Cell.Type.PASSAGE));
        maze.setCell(maze.getHeight() - 1, maze.getWidth() - 1, new Cell(maze.getHeight() - 1,
            maze.getWidth() - 1, Cell.Type.PASSAGE
        ));
        return maze;
    }

    private List<Coordinate> getUnvisitedNeighbours(Coordinate cell, boolean[][] visited, int height, int width) {
        List<Coordinate> neighbours = new ArrayList<>();
        final int UP_OFFSET = -2;
        final int DOWN_OFFSET = 2;
        final int LEFT_OFFSET = -2;
        final int RIGHT_OFFSET = 2;
        final int NO_OFFSET = 0;

        int[] rowOffsets = {UP_OFFSET, DOWN_OFFSET, NO_OFFSET, NO_OFFSET};
        int[] colOffsets = {NO_OFFSET, NO_OFFSET, LEFT_OFFSET, RIGHT_OFFSET};

        for (int i = 0; i < rowOffsets.length; i++) {
            int newRow = cell.row() + rowOffsets[i];
            int newCol = cell.col() + colOffsets[i];

            if (newRow > 0 && newRow < height * 2 && newCol > 0 && newCol < width * 2 && !visited[newRow][newCol]) {
                neighbours.add(new Coordinate(newRow, newCol));
            }
        }

        return neighbours;
    }

    private void removeWall(Maze maze, Coordinate from, Coordinate to) {
        int rowDifference = to.row() - from.row();
        int colDifference = to.col() - from.col();

        int removeRow = from.row() + rowDifference / 2;
        int removeCol = from.col() + colDifference / 2;

        maze.setCell(removeRow, removeCol, new Cell(removeRow, removeCol, Cell.Type.PASSAGE));
    }
}

