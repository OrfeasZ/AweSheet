package com.awesheet.messages;

import com.awesheet.enums.UIMessageType;

public class SelectSheetMessage extends UIMessage {
    protected int sheet;

    public SelectSheetMessage() {
        super(UIMessageType.SELECT_SHEET);
    }

    public int getSheet() {
        return sheet;
    }
}
