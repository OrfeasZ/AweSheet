package com.awesheet.models.functions;

import com.awesheet.enums.FunctionType;
import com.awesheet.models.DataFunction;

public class AbsFunction extends DataFunction<Double> {
    public static String getName() {
        return "abs";
    }

    public static String getDescription() {
        return "Calculates the absolute value of the specified number.";
    }

    public static String[] getArgumentNames() {
        return new String[] { "number" };
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

        internalValue = Math.abs(parsedValue);
        displayValue = Double.toString(internalValue);

        return true;
    }
}
