package com.awesheet.models.functions;

import com.awesheet.models.DataFunction;
import com.awesheet.enums.FunctionType;
import com.awesheet.util.Utils;

public class NotFunction extends DataFunction<Boolean> {
    public static String getName() {
        return "not";
    }

    public static String getDescription() {
        return "Returns the negated logical value of the specified value.";
    }

    public static String[] getArgumentNames() {
        return new String[] { "logical" };
    }

    public NotFunction(){
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

        internalValue = !parsedValue;
        displayValue = internalValue ? "true" : "false";

        return true;
    }
}
