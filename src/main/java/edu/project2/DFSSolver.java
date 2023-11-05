package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.intellij.lang.annotations.MagicConstant;

public class DFSSolver implements Solver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Stack<Coordinate> stack = new Stack<>();
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
        stack.push(start);
        visited[start.row()][start.col()] = true;

        while (!stack.isEmpty()) {
            Coordinate current = stack.peek();

            // Если достигнута целевая клетка
            if (current.equals(end)) {
                return constructPath(stack);
            }

            boolean hasUnvisitedNeighbours = false;
            for (Coordinate neighbour : getNeighbours(maze, current)) {
                var debag = neighbour;
                if (!visited[neighbour.row()][neighbour.col()] && maze.isPassage(neighbour.row(), neighbour.col())) {
                    stack.push(neighbour);
                    visited[neighbour.row()][neighbour.col()] = true;
                    hasUnvisitedNeighbours = true;
                    break;
                }
            }

            if (!hasUnvisitedNeighbours) {
                stack.pop();
            }
        }

        return new ArrayList<>();
    }

    @MagicConstant
    public static List<Coordinate> getNeighbours(Maze maze, Coordinate cell) {
        List<Coordinate> neighbours = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] d : directions) {
            int newRow = cell.row() + d[0];
            int newCol = cell.col() + d[1];
            if (newRow >= 0 && newRow < maze.getHeight() && newCol >= 0 && newCol < maze.getWidth()) {
                neighbours.add(new Coordinate(newRow, newCol));
            }
        }

        return neighbours;
    }

    private List<Coordinate> constructPath(Stack<Coordinate> stack) {
        return new ArrayList<>(stack);
    }
}
