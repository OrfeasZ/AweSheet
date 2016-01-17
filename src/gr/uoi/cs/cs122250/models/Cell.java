package gr.uoi.cs.cs122250.models;

import gr.uoi.cs.cs122250.enums.CellType;

public abstract class Cell {
    protected int x;
    protected int y;
    protected CellType type;
    protected String value;

    protected Cell(CellType type, int x, int y, String value) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public abstract String getDisplayValue();
}
