package com.awesheet.models.functions;

import com.awesheet.enums.FunctionType;
import com.awesheet.models.DataFunction;

public class TanFunction extends DataFunction {
    public static String getName() {
        return "tan";
    }

    TanFunction(){
        super(FunctionType.TAN_FUNCTION_TYPE);
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

        if (parsedValue < 0 || parsedValue > 360) {
            return false;
        }

        internalValue = Double.toString(Math.tan(parsedValue));

        return true;
    }
}
