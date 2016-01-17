package com.awesheet.models.functions;

import com.awesheet.models.DataFunction;
import com.awesheet.models.FunctionArgument;
import com.awesheet.enums.FunctionType;

public class MeanFunction extends DataFunction {
    public static String getName() {
        return "mean";
    }

    MeanFunction(){
        super(FunctionType.MEAN_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() == 0) {
            return false;
        }

        double sum = 0.0;

        for (FunctionArgument argument : arguments) {
            try {
                sum += Double.parseDouble(argument.getValue().trim());
            } catch (NumberFormatException e) {
                return false;
            }
        }

        internalValue = Double.toString(sum / (double) arguments.size());

        return true;
    }
}
