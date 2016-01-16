package gr.uoi.cs.cs122250.models;

import java.util.HashSet;

public class Sheet {
    protected String name;
    protected HashSet<Cell> cells;
    protected int id;
    protected int maxColumn;
    protected int maxRow;

    public Sheet(String name) {
        this.name = name;
        this.cells = new HashSet<Cell>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cell getCell(int x, int y) {
        return null;
    }

    public void setCellValue(int x, int y, String value) {

    }

    public int getID() {
        return this.id;
    }

    public int getMaxColumnt() {
        return this.maxColumn;
    }

    public int getMaxRow() {
        return this.maxRow;
    }
}
