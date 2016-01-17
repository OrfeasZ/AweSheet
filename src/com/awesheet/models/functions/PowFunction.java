package com.awesheet.models.functions;

import com.awesheet.enums.FunctionType;
import com.awesheet.models.DataFunction;

public class PowFunction extends DataFunction {
    public static String getName() {
        return "pow";
    }

    PowFunction(){
        super(FunctionType.POW_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() != 2) {
            return false;
        }

        double parsedValue1;
        double parsedValue2;

        try {
            parsedValue1 = Double.parseDouble(arguments.get(0).getValue().trim());
        } catch (NumberFormatException e) {
            return false;
        }

        try {
            parsedValue2 = Double.parseDouble(arguments.get(1).getValue().trim());
        } catch (NumberFormatException e) {
            return false;
        }

        internalValue = Double.toString(Math.pow(parsedValue1, parsedValue2));

        return true;
    }
}
