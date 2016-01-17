package com.awesheet.models.functions;

import com.awesheet.enums.FunctionType;
import com.awesheet.models.DataFunction;
import com.awesheet.models.FunctionArgument;

public class AndFunction extends DataFunction {
    AndFunction(FunctionArgument[] arguments){
        super(FunctionType.AND_FUNCTION_TYPE, arguments);
    }

    @Override
    public String getDisplayValue() {
        return null;
    }

    @Override
    public String getValue() {return null;}

    @Override
    public boolean isValid() {
        return false;
    }
}
