package gr.uoi.cs.cs122250.models.cells;

import gr.uoi.cs.cs122250.enums.CellType;
import gr.uoi.cs.cs122250.models.Cell;

public class ValueCell extends Cell {
    public ValueCell(int x, int y, String value) {
        super(CellType.VALUE_CELL_TYPE, x, y, value);
    }

    @Override
    public  String getDisplayValue() {
        return null;
    }
}