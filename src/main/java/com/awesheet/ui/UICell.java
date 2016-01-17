package com.awesheet.ui;

import com.awesheet.models.Sheet;

public class UICell extends UIModel {
    protected int x;
    protected int y;
    protected int type;
    protected int sheet;
    protected String value;
    protected String displayValue;

    public UICell(int x, int y, int type, String value, String displayValue, Sheet sheet) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.value = value;
        this.displayValue = displayValue;
        this.sheet = sheet.getID();
    }

    public String getID() {
        return x + "x" + y;
    }
}
