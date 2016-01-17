package com.awesheet.models.arguments;

import com.awesheet.enums.FunctionArgumentType;
import com.awesheet.models.FunctionArgument;

public class CellFunctionArgument extends FunctionArgument {
    CellFunctionArgument(String value) {
        super(FunctionArgumentType.CELL_FUNCTION_ARGUMENT_TYPE, value);
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