package com.awesheet.ui;

public class UIFunction extends UIModel {
    protected String description;
    protected String arguments[];

    public UIFunction(String description, String arguments[]) {
        this.description = description;
        this.arguments = arguments;
    }
}
