package com.awesheet.models.functions;

import com.awesheet.enums.FunctionType;
import com.awesheet.models.DataFunction;

public class TrimFunction extends DataFunction<String> {
    public static String getName() {
        return "trim";
    }

    public static String getDescription() {
        return "Returns the specified argument with leading and trailing whitespace trimmed.";
    }

    public static String[] getArgumentNames() {
        return new String[] { "argument" };
    }

    public TrimFunction() {
        super(FunctionType.TRIM_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() != 1) {
            return false;
        }

        internalValue = arguments.get(0).getValue().trim();
        displayValue = internalValue;

        return true;
    }
}
