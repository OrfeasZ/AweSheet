package com.awesheet.ui;

import java.util.HashMap;

public class UISheet extends UIModel {
    protected String name;
    protected HashMap<String, UICell> cells;
    protected int id;
    protected int maxColumn;
    protected int maxRow;

    public UISheet(String name, int id, int maxColumn, int maxRow) {
        this.name = name;
        this.id = id;
        this.maxColumn = maxColumn;
        this.maxRow = maxRow;

        cells = new HashMap<String, UICell>();
    }

    public void addCell(UICell cell) {
        cells.put(cell.getID(), cell);
    }
}
