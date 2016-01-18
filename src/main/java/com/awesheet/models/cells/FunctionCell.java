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
