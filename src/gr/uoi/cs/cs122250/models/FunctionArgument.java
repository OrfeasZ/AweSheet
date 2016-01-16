package gr.uoi.cs.cs122250.models;

import gr.uoi.cs.cs122250.enums.FunctionArgumentType;

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
