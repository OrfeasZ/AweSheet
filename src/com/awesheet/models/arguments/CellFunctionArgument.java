package com.awesheet.models.arguments;

import com.awesheet.enums.FunctionArgumentType;
import com.awesheet.models.FunctionArgument;

public class CellFunctionArgument extends FunctionArgument {
    public CellFunctionArgument(String value) {
        super(FunctionArgumentType.CELL_FUNCTION_ARGUMENT_TYPE, value);
    }

    @Override
    public String getValue() {
        return null;
    }
}