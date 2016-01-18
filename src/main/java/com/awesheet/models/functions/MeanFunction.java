package com.awesheet.models.functions;

import com.awesheet.models.DataFunction;
import com.awesheet.models.FunctionArgument;
import com.awesheet.enums.FunctionType;

public class MeanFunction extends DataFunction<Double> {
    public static String getName() {
        return "mean";
    }

    public static String getDescription() {
        return "Returns the mean (average) value of the specified numbers.";
    }

    public static String[] getArgumentNames() {
        return new String[] {};
    }

    public MeanFunction(){
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

        internalValue = sum / (double) arguments.size();
        displayValue = Double.toString(internalValue);

        return true;
    }
}
