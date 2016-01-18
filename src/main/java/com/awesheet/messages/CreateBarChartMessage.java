package com.awesheet.messages;

import com.awesheet.enums.UIMessageType;

public class CreateBarChartMessage extends CreateChartMessage {
    public CreateBarChartMessage() {
        super(UIMessageType.CREATE_BAR_CHART);
    }
}
