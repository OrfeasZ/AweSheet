package com.awesheet.models;

import com.awesheet.enums.FunctionArgumentType;

public abstract class FunctionArgument {
    protected int type;
    protected String value;

    protected FunctionArgument(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public abstract String getDisplayValue();
    public abstract String getValue();
}
