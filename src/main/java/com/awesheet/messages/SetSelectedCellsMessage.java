package com.awesheet.messages;

public class SetSelectedCellsMessage extends UIMessage {
    protected int sheet;
    protected int cells[][];

    public int[][] getCells() {
        return cells;
    }

    public int getSheet() {
        return sheet;
    }
}
