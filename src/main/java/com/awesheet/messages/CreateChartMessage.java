package com.awesheet.messages;

public abstract class CreateChartMessage extends UIMessage {
    protected String title;
    protected String xaxis;
    protected String yaxis;

    protected CreateChartMessage(int type) {
        super(type);
    }

    public String getTitle() {
        return title;
    }

    public String getXaxis() {
        return xaxis;
    }

    public String getYaxis() {
        return yaxis;
    }
}
