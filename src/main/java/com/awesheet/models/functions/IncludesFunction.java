package com.awesheet.models.functions;

import com.awesheet.models.DataFunction;
import com.awesheet.enums.FunctionType;

public class IncludesFunction extends DataFunction<Boolean> {
    public static String getName() {
        return "includes";
    }

    public IncludesFunction(){
        super(FunctionType.INCLUDES_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() != 2) {
            return false;
        }

        String argument1Value = arguments.get(0).getValue();
        String argument2Value = arguments.get(1).getValue();

        if (argument1Value.length() == 0 || argument2Value.length() == 0) {
            return false;
        }

        internalValue = argument1Value.contains(argument2Value);
        displayValue = internalValue ? "true" : "false";

        return true;
    }
}
