package com.awesheet.models.cells;

import com.awesheet.enums.CellType;
import com.awesheet.models.Cell;

public class ValueCell extends Cell {
    public ValueCell(int x, int y, String value) {
        super(CellType.VALUE_CELL_TYPE, x, y, value);
    }

    @Override
    public  String getDisplayValue() {
        return null;
    }
}