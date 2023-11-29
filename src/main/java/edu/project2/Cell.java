package edu.project2;

public final class Cell {
    private final int row;
    private final int col;
    private Cell.Type type;

    public Cell(int row, int col, Cell.Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type { WALL, PASSAGE }
}
