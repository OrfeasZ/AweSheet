package com.awesheet.actions;

import com.awesheet.enums.UIActionType;

public class RemoveCellAction extends UIAction {
    protected int x;
    protected int y;
    protected int sheet;

    public RemoveCellAction(int sheet, int x, int y) {
        super(UIActionType.REMOVE_CELL);

        this.sheet = sheet;
        this.x = x;
        this.y = y;
    }
}
