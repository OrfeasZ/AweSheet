package com.awesheet.models.functions;

import com.awesheet.models.FunctionArgument;
import com.awesheet.models.DataFunction;
import com.awesheet.enums.FunctionType;
import com.awesheet.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StddevFunction extends DataFunction {
    public static String getName() {
        return "stddev";
    }

    StddevFunction(){
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

        double mean = sum / 2.0;

        double stddev = 0.0;

        for (Double value : parsedValues) {
            stddev += Math.pow(value - mean, 2);
        }

        internalValue = Double.toString(stddev);

        return true;
    }
}
