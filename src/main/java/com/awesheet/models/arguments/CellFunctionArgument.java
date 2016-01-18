/*
 * AweSheet - Simple Open-Source Spreadsheet Editor
 * Copyright (c) 2015 - 2016, Orfeas - Ioannis Zafeiris, Nikolaos Fylakis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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