package com.awesheet.models.functions;

import com.awesheet.models.FunctionArgument;
import com.awesheet.models.DataFunction;
import com.awesheet.enums.FunctionType;
import com.awesheet.util.Utils;

public class NotFunction extends DataFunction {
    public static String getName() {
        return "not";
    }

    NotFunction(){
        super(FunctionType.NOT_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() != 1) {
            return false;
        }

        boolean parsedValue;

        try {
            parsedValue = Utils.parseBool(arguments.get(0).getValue());
        } catch (NumberFormatException e) {
            return false;
        }

        internalValue = (parsedValue) ? "false" : "true";

        return true;
    }
}
