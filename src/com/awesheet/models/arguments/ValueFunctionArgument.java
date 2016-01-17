package com.awesheet.models.arguments;

import com.awesheet.enums.FunctionArgumentType;
import com.awesheet.models.FunctionArgument;

public class ValueFunctionArgument extends FunctionArgument {
    public ValueFunctionArgument(String value) {
        super(FunctionArgumentType.VALUE_FUNCTION_ARGUMENT_TYPE, value);
    }

    @Override
    public String getValue() {
        return value;
    }
}