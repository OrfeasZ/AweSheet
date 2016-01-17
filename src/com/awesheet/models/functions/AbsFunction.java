package com.awesheet.models.functions;

import com.awesheet.enums.FunctionType;
import com.awesheet.models.DataFunction;

public class AbsFunction extends DataFunction {
    public static String getName() {
        return "abs";
    }

    public AbsFunction(){
        super(FunctionType.ABS_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() != 1) {
            return false;
        }

        double parsedValue;

        try {
            parsedValue = Double.parseDouble(arguments.get(0).getValue().trim());
        } catch (NumberFormatException e) {
            return false;
        }

        internalValue = Double.toString(Math.abs(parsedValue));

        return true;
    }
}
