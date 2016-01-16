package gr.uoi.cs.cs122250.models.functions;

import gr.uoi.cs.cs122250.models.DataFunction;
import gr.uoi.cs.cs122250.models.FunctionArgument;
import gr.uoi.cs.cs122250.enums.FunctionType;

public class MultFunction extends DataFunction {
    MultFunction(FunctionArgument[] arguments){
        super(FunctionType.MultFunctionType, arguments);
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
