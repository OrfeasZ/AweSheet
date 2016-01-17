package com.awesheet.actions;

import com.awesheet.enums.UIActionType;

public class SetMaxRowAction extends UIAction {
    protected int sheet;
    protected int maxRow;

    public SetMaxRowAction(int sheet, int maxRow) {
        super(UIActionType.SET_MAX_ROW);

        this.sheet = sheet;
        this.maxRow = maxRow;
    }
}
