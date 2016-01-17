package com.awesheet.actions;

import com.awesheet.enums.UIActionType;

public class SetMaxColumnAction extends UIAction {
    protected int sheet;
    protected int maxColumn;

    public SetMaxColumnAction(int sheet, int maxColumn) {
        super(UIActionType.SET_MAX_COLUMN);

        this.sheet = sheet;
        this.maxColumn = maxColumn;
    }
}
