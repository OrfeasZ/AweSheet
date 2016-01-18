package com.awesheet.messages;

import com.awesheet.enums.UIMessageType;

public class CreateSheetMessage extends UIMessage {
    public CreateSheetMessage() {
        super(UIMessageType.CREATE_SHEET);
    }
}
