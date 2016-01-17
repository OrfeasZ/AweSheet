package com.awesheet.messages;

public class SetCellValueMessage extends UIMessage {
    protected int sheet;
    protected int cellX;
    protected int cellY;
    protected String value;

    public int getSheet() {
        return sheet;
    }

    public int cellX() {
        return cellX;
    }

    public int cellY() {
        return cellY;
    }

    public String getValue() {
        return value;
    }
}
