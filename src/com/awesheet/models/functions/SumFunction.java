package com.awesheet.models.functions;

import com.awesheet.models.DataFunction;
import com.awesheet.models.FunctionArgument;
import com.awesheet.enums.FunctionType;

public class SumFunction extends DataFunction {
    public static String getName() {
        return "sum";
    }

    SumFunction(){
        super(FunctionType.SUM_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() == 0) {
            return false;
        }

        double sum = 0.0;

        for (FunctionArgument argument : arguments) {
            double parsedValue;

            try {
                parsedValue = Double.parseDouble(argument.getValue().trim());
            } catch (NumberFormatException e) {
                return false;
            }

            sum += parsedValue;
        }

        internalValue = Double.toString(sum);

        return true;
    }
}
