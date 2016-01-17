package com.awesheet.models.functions;

import com.awesheet.models.FunctionArgument;
import com.awesheet.models.DataFunction;
import com.awesheet.enums.FunctionType;

public class RemoveFunction extends DataFunction {
    public static String getName() {
        return "remove";
    }

    RemoveFunction(){
        super(FunctionType.REMOVE_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() != 2) {
            return false;
        }

        String argument1Value = arguments.get(0).getValue();
        String argument2Value = arguments.get(1).getValue();

        if (argument1Value.length() == 0 || argument2Value.length() == 0) {
            return false;
        }

        internalValue = argument1Value.replace(argument2Value, "");

        return true;
    }
}
