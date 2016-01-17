package com.awesheet.models.cells;

import com.awesheet.enums.CellType;
import com.awesheet.models.Cell;
import com.awesheet.models.Sheet;

public class ValueCell extends Cell {
    public ValueCell(int x, int y, String value, Sheet sheet) {
        super(CellType.VALUE_CELL_TYPE, x, y, value, sheet);
    }

    @Override
    public  String getDisplayValue() {
        return null;
    }
}