package com.awesheet.models.functions;

import com.awesheet.enums.FunctionType;
import com.awesheet.models.DataFunction;

public class LogFunction extends DataFunction<Double> {
    public static String getName() {
        return "log";
    }

    public static String getDescription() {
        return "Calculates the natural logarithm of the specified number.";
    }

    public static String[] getArgumentNames() {
        return new String[] { "number" };
    }

    public LogFunction(){
        super(FunctionType.LOG_FUNCTION_TYPE);
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

        if (parsedValue <= 0) {
            return false;
        }

        internalValue = Math.log(parsedValue);
        displayValue = Double.toString(internalValue);

        return true;
    }
}
