package com.awesheet.models.functions;

import com.awesheet.models.DataFunction;
import com.awesheet.models.FunctionArgument;
import com.awesheet.enums.FunctionType;
import com.awesheet.util.Utils;

public class OrFunction extends DataFunction {
    public static String getName() {
        return "or";
    }

    OrFunction(){
        super(FunctionType.OR_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() != 2) {
            return false;
        }

        boolean parsedValue1;
        boolean parsedValue2;

        try {
            parsedValue1 = Utils.parseBool(arguments.get(0).getValue());
        } catch (NumberFormatException e) {
            return false;
        }

        try {
            parsedValue2 = Utils.parseBool(arguments.get(1).getValue());
        } catch (NumberFormatException e) {
            return false;
        }

        internalValue = (parsedValue1 | parsedValue2) ? "true" : "false";

        return true;
    }
}
