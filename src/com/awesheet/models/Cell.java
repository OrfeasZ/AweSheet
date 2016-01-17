package com.awesheet.models;

import com.awesheet.interfaces.IUIBindable;
import com.awesheet.ui.UICell;
import com.awesheet.ui.UIModel;

public abstract class Cell implements IUIBindable {
    protected int x;
    protected int y;
    protected int type;
    protected String value;

    protected Cell(int type, int x, int y, String value) {
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

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public abstract String getDisplayValue();

    @Override
    public UIModel bind() {
        return new UICell(x, y, type, getValue(), getDisplayValue());
    }
}
