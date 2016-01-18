package com.awesheet.messages;

import com.awesheet.enums.UIMessageType;

public class CreateLineChartMessage extends CreateChartMessage {
    public CreateLineChartMessage() {
        super(UIMessageType.CREATE_LINE_CHART);
    }
}
