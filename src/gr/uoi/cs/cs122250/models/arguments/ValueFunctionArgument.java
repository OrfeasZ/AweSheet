package gr.uoi.cs.cs122250.models.arguments;

import gr.uoi.cs.cs122250.enums.FunctionArgumentType;
import gr.uoi.cs.cs122250.models.FunctionArgument;

public class ValueFunctionArgument extends FunctionArgument {
    ValueFunctionArgument(String value) {
        super(FunctionArgumentType.VALUE_FUNCTION_ARGUMENT_TYPE, value);
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