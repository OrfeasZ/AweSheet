package com.awesheet.models.functions;

import com.awesheet.models.DataFunction;
import com.awesheet.models.FunctionArgument;
import com.awesheet.enums.FunctionType;

public class MultFunction extends DataFunction<Double> {
    public static String getName() {
        return "mult";
    }

    public MultFunction(){
        super(FunctionType.MULT_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() == 0) {
            return false;
        }

        double mult = 1.0;

        for (FunctionArgument argument : arguments) {
            double parsedValue;

            try {
                parsedValue = Double.parseDouble(argument.getValue().trim());
            } catch (NumberFormatException e) {
                return false;
            }

            mult *= parsedValue;
        }

        internalValue = mult;
        displayValue = Double.toString(internalValue);

        return true;
    }
}
