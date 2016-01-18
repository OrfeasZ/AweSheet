package com.awesheet.models;

import java.util.ArrayList;

public abstract class DataFunction<T> {
    protected int type;
    protected ArrayList<FunctionArgument> arguments;
    protected String displayValue;
    protected T internalValue;

    protected DataFunction(int type){
        this.type = type;
        arguments = new ArrayList<FunctionArgument>();
        displayValue = "";
    }

    public int getType() {
        return type;
    }

    public ArrayList<FunctionArgument> getArguments() {
        return arguments;
    }

    public FunctionArgument getArgument(int index) {
        return arguments.get(index);
    }

    public void addArgument(FunctionArgument argument) {
        arguments.add(argument);
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public T getInternalValue() {
        return internalValue;
    }

    public abstract boolean parse();
}
