package com.awesheet.models.functions;

import com.awesheet.models.DataFunction;
import com.awesheet.models.FunctionArgument;
import com.awesheet.enums.FunctionType;

public class TrimFunction extends DataFunction {
    public static String getName() {
        return "trim";
    }

    TrimFunction(){
        super(FunctionType.TRIM_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() != 1) {
            return false;
        }

        internalValue = arguments.get(0).getValue().trim();

        return true;
    }
}
