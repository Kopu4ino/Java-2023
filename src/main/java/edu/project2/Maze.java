package edu.project2;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        this.height = height * 2 + 1;
        this.width = width * 2 + 1;
        this.grid = new Cell[this.height][this.width];

        initializeGrid();
    }

    private void initializeGrid() {
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if ((row % 2 != 0 && col % 2 != 0) && (row < height - 1 && col < width - 1)) {
                    grid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
                } else {
                    grid[row][col] = new Cell(row, col, Cell.Type.WALL);
                }
            }
        }
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || col < 0 || row >= height || col >= width) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    public void setCell(int row, int col, Cell cell) {
        if (row >= 0 && row < height && col >= 0 && col < width) {
            grid[row][col] = cell;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setCellType(int row, int col, Cell.Type type) {
        getCell(row, col).setType(type);
    }

    public boolean isWall(int row, int col) {
        return getCell(row, col).getType() == Cell.Type.WALL;
    }

    public boolean isPassage(int row, int col) {
        return getCell(row, col).getType() == Cell.Type.PASSAGE;
    }
}
