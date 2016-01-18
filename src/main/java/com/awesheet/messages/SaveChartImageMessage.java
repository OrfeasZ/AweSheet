package com.awesheet.messages;

import com.awesheet.enums.UIMessageType;

public class SaveChartImageMessage extends UIMessage {
    protected String imageData;

    public SaveChartImageMessage() {
        super(UIMessageType.SAVE_CHART_IMAGE);
    }

    public String getImageData() {
        return imageData;
    }
}
