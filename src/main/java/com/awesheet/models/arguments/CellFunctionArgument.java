package com.awesheet.models.arguments;

import com.awesheet.enums.FunctionArgumentType;
import com.awesheet.models.Cell;
import com.awesheet.models.FunctionArgument;
import com.awesheet.models.Sheet;
import com.awesheet.util.Utils;

import java.awt.*;

public class CellFunctionArgument extends FunctionArgument {
    protected Cell cell;
    protected boolean valid;

    public CellFunctionArgument(String value, Sheet sheet) {
        super(FunctionArgumentType.CELL_FUNCTION_ARGUMENT_TYPE, value);

        valid = false;
        Point cellPoint = Utils.parseCell(value);

        if (cellPoint == null) {
            return;
        }

        valid = true;
        cell = sheet.getCell(cellPoint.x, cellPoint.y);
    }

    @Override
    public String getValue() {
        return cell != null ? cell.getDisplayValue() : "";
    }

    @Override
    public boolean isValid() {
        return valid;
    }
}