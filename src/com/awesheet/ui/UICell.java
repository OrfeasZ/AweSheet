package com.awesheet.ui;

public class UICell extends UIModel {
    protected int x;
    protected int y;
    protected int type;
    protected String value;
    protected String displayValue;

    public UICell(int x, int y, int type, String value, String displayValue) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.value = value;
        this.displayValue = displayValue;
    }

    public String getID() {
        return x + "x" + y;
    }
}
