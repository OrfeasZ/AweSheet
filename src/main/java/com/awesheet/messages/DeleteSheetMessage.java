package com.awesheet.messages;

import com.awesheet.enums.UIMessageType;

public class DeleteSheetMessage extends UIMessage {
    protected int sheet;

    public DeleteSheetMessage() {
        super(UIMessageType.DELETE_SHEET);
    }

    public int getSheet() {
        return sheet;
    }
}
