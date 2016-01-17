package com.awesheet.models;

public abstract class FunctionArgument {
    protected int type;
    protected String value;

    protected FunctionArgument(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public abstract String getValue();

    public abstract boolean isValid();
}
