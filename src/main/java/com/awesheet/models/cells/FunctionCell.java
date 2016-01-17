package com.awesheet.models.cells;

import com.awesheet.enums.CellType;
import com.awesheet.managers.FunctionManager;
import com.awesheet.models.Cell;
import com.awesheet.models.DataFunction;
import com.awesheet.models.Sheet;

public class FunctionCell extends Cell {
    protected DataFunction internalFunction;

    public FunctionCell(int x, int y, String value, Sheet sheet) {
        super(CellType.FUNCTION_CELL_TYPE, x, y, value, sheet);
        internalFunction = FunctionManager.getInstance().parseFunction(value, sheet);
    }

    @Override
    public String getDisplayValue() {
        if (internalFunction == null || !internalFunction.parse()) {
            return "#ERR?";
        }

        return internalFunction.getDisplayValue();
    }
}
