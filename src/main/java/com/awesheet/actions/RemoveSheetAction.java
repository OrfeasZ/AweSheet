package com.awesheet.actions;

import com.awesheet.enums.UIActionType;

public class RemoveSheetAction extends UIAction {
    protected int sheet;

    public RemoveSheetAction(int id) {
        super(UIActionType.REMOVE_SHEET);
        sheet = id;
    }
}
