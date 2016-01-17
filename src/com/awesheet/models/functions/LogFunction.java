package com.awesheet.models.functions;

import com.awesheet.enums.FunctionType;
import com.awesheet.models.DataFunction;

public class LogFunction extends DataFunction {
    public static String getName() {
        return "log";
    }

    LogFunction(){
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

        internalValue = Double.toString(Math.log(parsedValue));

        return true;
    }
}
