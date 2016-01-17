package com.awesheet.actions;

import com.awesheet.enums.UIActionType;
import com.awesheet.ui.UISheet;

public class SetSheetAction extends UIAction {
    protected UISheet sheet;

    public SetSheetAction(UISheet sheet) {
        super(UIActionType.SET_SHEET);
        this.sheet = sheet;
    }
}
