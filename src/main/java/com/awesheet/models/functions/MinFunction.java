package com.awesheet.models.functions;

import com.awesheet.models.FunctionArgument;
import com.awesheet.models.DataFunction;
import com.awesheet.enums.FunctionType;

public class MinFunction extends DataFunction<Double> {
    public static String getName() {
        return "min";
    }

    public MinFunction(){
        super(FunctionType.MIN_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() == 0) {
            return false;
        }

        double min = 0.0;
        boolean hasMin = false;

        for (FunctionArgument argument : arguments) {
            double parsedValue;

            try {
                parsedValue = Double.parseDouble(argument.getValue().trim());
            } catch (NumberFormatException e) {
                return false;
            }

            if (!hasMin) {
                hasMin = true;
                min = parsedValue;
            }

            if (parsedValue < min) {
                min = parsedValue;
            }
        }

        internalValue = min;
        displayValue = Double.toString(internalValue);

        return true;
    }
}
