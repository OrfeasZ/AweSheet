package com.awesheet.models.functions;

import com.awesheet.enums.FunctionType;
import com.awesheet.models.DataFunction;

public class Log10Function extends DataFunction<Double> {
    public static String getName() {
        return "log10";
    }

    public Log10Function(){
        super(FunctionType.LOG_10_FUNCTION_TYPE);
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

        internalValue = Math.log10(parsedValue);
        displayValue = Double.toString(internalValue);

        return true;
    }
}
