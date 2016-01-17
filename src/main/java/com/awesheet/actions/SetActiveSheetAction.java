package com.awesheet.actions;

import com.awesheet.enums.UIActionType;

public class SetActiveSheetAction extends UIAction {
    protected int sheet;

    public SetActiveSheetAction(int id) {
        super(UIActionType.SET_ACTIVE_SHEET);
        sheet = id;
    }
}
