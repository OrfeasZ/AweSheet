package gr.uoi.cs.cs122250.models.cells;

import gr.uoi.cs.cs122250.models.DataFunction;
import gr.uoi.cs.cs122250.enums.CellType;
import gr.uoi.cs.cs122250.models.Cell;

public class FunctionCell extends Cell {
    protected DataFunction internalFunction;

    public FunctionCell(int x, int y, String value) {
        super(CellType.FunctionCellType, x, y, value);

        // TODO: Create internalFunction.
    }

    @Override
    public String getDisplayValue() {
        return this.internalFunction.getDisplayValue();
    }
}
