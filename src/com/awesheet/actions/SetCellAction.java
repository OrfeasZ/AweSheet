package com.awesheet.actions;

import com.awesheet.enums.UIActionType;
import com.awesheet.ui.UICell;

public class SetCellAction extends UIAction {
    protected UICell cell;

    public SetCellAction(UICell cell) {
        super(UIActionType.SET_CELL);

        this.cell = cell;
    }
}
