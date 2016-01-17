package com.awesheet.models;

import com.awesheet.enums.FunctionType;

public abstract class DataFunction {
    protected FunctionType type;
    protected FunctionArgument[] arguments;

    protected DataFunction(FunctionType type, FunctionArgument[] arguments){
        this.type = type;
        this.arguments = arguments;
    }

    public FunctionType getType() {
        return type;
    }

    public FunctionArgument[] getArguments() {
        return arguments;
    }

    public FunctionArgument getArgument(int index) {
        return arguments[index];
    }

    public abstract String getDisplayValue();

    public abstract String getValue();

    public abstract boolean isValid();
}
