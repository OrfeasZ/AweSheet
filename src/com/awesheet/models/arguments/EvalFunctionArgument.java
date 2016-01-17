package com.awesheet.models.arguments;

import com.awesheet.enums.FunctionArgumentType;
import com.awesheet.models.DataFunction;
import com.awesheet.models.FunctionArgument;

public class EvalFunctionArgument extends FunctionArgument {
    protected DataFunction innerFunction;

    public EvalFunctionArgument(String value) {
        super(FunctionArgumentType.EVAL_FUNCTION_ARGUMENT_TYPE, value);
    }

    public void setInnerFunction(DataFunction function) {
        innerFunction = function;
    }

    @Override
    public String getValue() {
        return null;
    }
}