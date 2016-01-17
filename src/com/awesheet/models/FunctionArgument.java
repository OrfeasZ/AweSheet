package com.awesheet.models;

import com.awesheet.enums.FunctionArgumentType;

public abstract class FunctionArgument {
    protected FunctionArgumentType type;
    protected String value;

    protected FunctionArgument(FunctionArgumentType type, String value) {
        this.type = type;
        this.value = value;
    }

    public abstract String getDisplayValue();
    public abstract String getValue();
}
