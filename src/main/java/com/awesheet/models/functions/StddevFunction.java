package com.awesheet.models.functions;

import com.awesheet.models.DataFunction;
import com.awesheet.enums.FunctionType;
import com.awesheet.util.Utils;

import java.util.List;

public class StddevFunction extends DataFunction<Double> {
    public static String getName() {
        return "stddev";
    }

    public StddevFunction(){
        super(FunctionType.STDDEV_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() == 0) {
            return false;
        }

        List<Double> parsedValues = Utils.parseDoubleArguments(arguments);

        if (parsedValues == null) {
            return false;
        }

        double sum = 0.0;

        for (Double value : parsedValues) {
            sum += value;
        }

        double mean = sum / arguments.size();

        double stddev = 0.0;

        for (Double value : parsedValues) {
            stddev += Math.pow(value - mean, 2);
        }

        stddev /= (double) (arguments.size() - 1);
        stddev = Math.sqrt(stddev);

        internalValue = stddev;
        displayValue = Double.toString(internalValue);

        return true;
    }
}
