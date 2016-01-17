package com.awesheet.models.functions;

import com.awesheet.models.DataFunction;
import com.awesheet.models.FunctionArgument;
import com.awesheet.enums.FunctionType;

import java.util.List;

public class MaxFunction extends DataFunction {
    public static String getName() {
        return "max";
    }

    public MaxFunction(){
        super(FunctionType.MAX_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() == 0) {
            return false;
        }

        double max = 0.0;
        boolean hasMax = false;

        for (FunctionArgument argument : arguments) {
            double parsedValue;

            try {
                parsedValue = Double.parseDouble(argument.getValue().trim());
            } catch (NumberFormatException e) {
                return false;
            }

            if (!hasMax) {
                hasMax = true;
                max = parsedValue;
            }

            if (parsedValue > max) {
                max = parsedValue;
            }
        }

        internalValue = Double.toString(max);

        return true;
    }
}
