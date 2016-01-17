package com.awesheet.models.cells;

import com.awesheet.enums.CellType;
import com.awesheet.models.Cell;
import com.awesheet.models.DataFunction;
import com.awesheet.models.Sheet;

public class FunctionCell extends Cell {
    protected DataFunction internalFunction;

    public FunctionCell(int x, int y, String value, Sheet sheet) {
        super(CellType.FUNCTION_CELL_TYPE, x, y, value, sheet);

        // TODO: Create internalFunction.
    }

    @Override
    public String getDisplayValue() {
        return internalFunction.getDisplayValue();
    }
}
