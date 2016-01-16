package gr.uoi.cs.cs122250.models.arguments;

import gr.uoi.cs.cs122250.enums.FunctionArgumentType;
import gr.uoi.cs.cs122250.models.FunctionArgument;

public class CellFunctionArgument extends FunctionArgument {
    CellFunctionArgument(String value) {
        super(FunctionArgumentType.CellFunctionArgumentType, value);
    }

    @Override
    public String getDisplayValue() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }
}